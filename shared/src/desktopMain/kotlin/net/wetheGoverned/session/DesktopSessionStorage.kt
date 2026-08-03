package net.wetheGoverned.session

import net.wetheGoverned.model.VerificationTier
import java.util.prefs.Preferences

class DesktopSessionStorage : SessionStorage {
    private val prefs = Preferences.userNodeForPackage(DesktopSessionStorage::class.java)
    override fun saveSession(session: UserSession) {
        prefs.put("pubKey", session.pubKey)
        prefs.put("displayName", session.displayName)
        prefs.put("districtId", session.districtId ?: "")
        prefs.put("tier", session.tier.name)
        prefs.put("privateKey", session.privateKey ?: "")
        prefs.flush()
    }
    override fun getSession(): UserSession? {
        val pk = prefs.get("pubKey", null) ?: return null
        return UserSession(
            pk,
            prefs.get("displayName", ""),
            prefs.get("districtId", "").ifBlank { null },
            tier = VerificationTier.valueOf(prefs.get("tier", VerificationTier.OBSERVER.name)),
            privateKey = prefs.get("privateKey", "")
        )
    }
    override fun clearSession() {
        prefs.clear()
        prefs.flush()
    }
    override fun savePrivateKeySecurely(key: String) { prefs.put("secure_key", key); prefs.flush() }
    override fun getPrivateKeySecurely(): String? = prefs.get("secure_key", null)
}
