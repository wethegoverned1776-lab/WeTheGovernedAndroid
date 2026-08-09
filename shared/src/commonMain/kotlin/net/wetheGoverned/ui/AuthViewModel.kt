package net.wetheGoverned.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import net.wetheGoverned.core.*
import net.wetheGoverned.model.UserAccount
import net.wetheGoverned.model.VerificationTier
import net.wetheGoverned.repository.AccountRepository
import net.wetheGoverned.repository.ResidentRepository
import net.wetheGoverned.session.SessionManager

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAuthenticated: Boolean = false,
    val isRegistered: Boolean = false,
    val selectedDistrictId: String? = null,
    val selectedDistrictName: String = "Select District",
    val isPasswordChanged: Boolean = false,
    val requiresPasswordChange: Boolean = false
)

open class AuthViewModel(
    private val accountRepository: AccountRepository,
    private val sessionManager: SessionManager,
    private val residentRepository: ResidentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onDistrictSelected(id: String, name: String) {
        _uiState.update { it.copy(selectedDistrictId = id, selectedDistrictName = name) }
    }

    fun register(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(error = "Username and password are required") }
            return
        }
        
        // Protocol Guard: Prevent taking the admin name if not admin
        if (username.lowercase() == "admin") {
            _uiState.update { it.copy(error = "Username 'admin' is reserved") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            // ERR_V2_04 FIX: Move key generation to background
            val keyPair = withContext(kotlinx.coroutines.Dispatchers.Default) {
                Secp256k1KeyManager.generateKeyPair()
            }

            val result = accountRepository.register(
                UserAccount(
                    username = username,
                    password = password,
                    pubKey = keyPair.pubKeyHex,
                    privateKey = keyPair.privateKeyHex,
                    districtId = _uiState.value.selectedDistrictId
                )
            )
            result.onSuccess {
                // Create initial Observer profile
                residentRepository.createProfile(
                    net.wetheGoverned.model.ResidentProfile(
                        pubKey = keyPair.pubKeyHex,
                        displayName = username,
                        districtId = _uiState.value.selectedDistrictId,
                        tier = VerificationTier.OBSERVER,
                        joinedAt = Clock.System.now().toEpochMilliseconds()
                    )
                )
                _uiState.update { it.copy(isLoading = false, isRegistered = true) }
                login(username, password)
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            // Requirement: Support 'Real Signatures' via nsec login
            if (password.startsWith("nsec1")) {
                try {
                    val privKeyHex = net.wetheGoverned.core.Bech32Codec.decodeNsec(password)
                    val pubKeyHex = platformDerivePubKey(privKeyHex)
                    
                    sessionManager.login(
                        pubKeyHex = pubKeyHex,
                        privateKeyHex = privKeyHex,
                        districtId = "us-fl-06", // Default for nsec login
                        tier = VerificationTier.VERIFIED,
                        displayName = username.ifBlank { "Nostr User" }
                    )
                    
                    // Create/Sync the resident profile for this nsec login to ensure UI knows we are verified
                    residentRepository.createProfile(
                        net.wetheGoverned.model.ResidentProfile(
                            pubKey = pubKeyHex,
                            displayName = username.ifBlank { "Nostr User" },
                            federalHouseId = "us-fl-06",
                            tier = VerificationTier.VERIFIED,
                            joinedAt = Clock.System.now().toEpochMilliseconds()
                        )
                    )
                    _uiState.update { it.copy(isLoading = false, isAuthenticated = true) }
                    return@launch
                } catch (e: Exception) {
                    _uiState.update { it.copy(isLoading = false, error = "Invalid nsec key: ${e.message}") }
                    return@launch
                }
            }

            accountRepository.login(username, password).onSuccess { account ->
                sessionManager.login(
                    pubKeyHex = account.pubKey,
                    privateKeyHex = account.privateKey,
                    districtId = account.districtId,
                    displayName = account.username,
                    tier = VerificationTier.OBSERVER // Initial tier
                )
                
                // Refresh profile
                residentRepository.getProfile(account.pubKey).onFailure {
                    residentRepository.createProfile(
                        net.wetheGoverned.model.ResidentProfile(
                            pubKey = account.pubKey,
                            displayName = account.username,
                            districtId = account.districtId,
                            tier = VerificationTier.OBSERVER,
                            joinedAt = Clock.System.now().toEpochMilliseconds()
                        )
                    )
                }

                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        isAuthenticated = true,
                        requiresPasswordChange = account.requiresPasswordChange
                    ) 
                }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun changePassword(username: String, newPassword: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            accountRepository.changePassword(username, newPassword).onSuccess {
                _uiState.update { it.copy(isLoading = false, isPasswordChanged = true, requiresPasswordChange = false) }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun loginAsGuest() {
        viewModelScope.launch {
            // Requirement: Guest sessions MUST use a deterministic observer key
            sessionManager.login(
                pubKeyHex = "guest_observer_hex",
                privateKeyHex = "0000000000000000000000000000000000000000000000000000000000000001",
                districtId = "us-fl-06",
                displayName = "Observer",
                tier = VerificationTier.OBSERVER
            )
            _uiState.update { it.copy(isAuthenticated = true) }
        }
    }

    fun clearError() { _uiState.update { it.copy(error = null) } }

    fun reset() {
        _uiState.value = AuthUiState()
    }
}
