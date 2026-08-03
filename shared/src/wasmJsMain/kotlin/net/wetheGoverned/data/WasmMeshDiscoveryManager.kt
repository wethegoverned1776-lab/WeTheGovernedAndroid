package net.wetheGoverned.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.*
import net.wetheGoverned.core.CivicPublisher
import net.wetheGoverned.session.SessionManager

/**
 * Wasm implementation of MeshDiscoveryManager.
 * Uses Nostr-based fallback for peer discovery since UDP is not available in browser.
 */
class WasmMeshDiscoveryManager(
    private val sessionManager: SessionManager,
    private val publisher: CivicPublisher? = null,
    private val relayManager: NostrRelayManager? = null
) : MeshDiscoveryManager {
    private val _discoveredPeers = MutableStateFlow<Set<String>>(emptySet())
    override val discoveredPeers: StateFlow<Set<String>> = _discoveredPeers.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val announceInterval = 60000L 

    override fun registerService(port: Int, districtId: String) {
        scope.launch {
            while (isActive) {
                val pubKey = sessionManager.currentSession?.pubKey ?: "guest"
                publisher?.signPublishImportCivicEvent(
                    kind = 30311,
                    tags = listOf(
                        listOf("d", "wtg_presence_$pubKey"), 
                        listOf("g", districtId), 
                        listOf("port", port.toString()),
                        listOf("type", "wasm")
                    ),
                    content = "ONLINE",
                    pubKey = pubKey
                )
                delay(announceInterval)
            }
        }
    }

    override fun discoverPeers() {
        scope.launch {
            relayManager?.events?.collect { event ->
                if (event.kind == 30311) {
                    val isWtg = event.tags.any { it.size >= 2 && it[0] == "d" && it[1].startsWith("wtg_presence_") }
                    if (isWtg) {
                        _discoveredPeers.value += event.pubKey
                    }
                }
            }
        }
        
        // Subscribe to presence events
        scope.launch {
            relayManager?.subscribe(
                "discovery_${Clock.System.now().toEpochMilliseconds()}",
                buildJsonObject {
                    put("kinds", buildJsonArray { add(JsonPrimitive(30311)) })
                }
            )
        }
    }

    override fun stopDiscovery() {
        scope.cancel()
    }
}
