package net.wetheGoverned.session

import kotlinx.browser.window
import org.w3c.dom.Storage
import net.wetheGoverned.model.VerificationTier

private val storage: Storage get() = window.localStorage

private fun localStorageGet(key: String): String? = storage.getItem(key)
private fun localStorageSet(key: String, value: String) = storage.setItem(key, value)
private fun localStorageRemove(key: String) = storage.removeItem(key)

class WebSessionStorage : SessionStorage {
    override fun saveSession(session: UserSession) {
        localStorageSet("pubKey", session.pubKey)
        localStorageSet("displayName", session.displayName)
        session.districtId?.let { localStorageSet("districtId", it) }
        localStorageSet("tier", session.tier.name)
        session.privateKey?.let { localStorageSet("privateKey", it) }
    }
    override fun getSession(): UserSession? {
        val pk = localStorageGet("pubKey") ?: return null
        return UserSession(
            pk,
            localStorageGet("displayName") ?: "",
            localStorageGet("districtId"),
            tier = VerificationTier.valueOf(localStorageGet("tier") ?: "OBSERVER"),
            privateKey = localStorageGet("privateKey")
        )
    }
    override fun clearSession() {
        localStorageRemove("pubKey")
        localStorageRemove("displayName")
        localStorageRemove("districtId")
        localStorageRemove("tier")
        localStorageRemove("privateKey")
    }
    override fun savePrivateKeySecurely(key: String) { localStorageSet("privateKey", key) }
    override fun getPrivateKeySecurely(): String? = localStorageGet("privateKey")
}
