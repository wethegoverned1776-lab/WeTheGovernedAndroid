package net.wetheGoverned.data

import kotlinx.serialization.json.*
import net.wetheGoverned.core.*
import net.wetheGoverned.model.*
import net.wetheGoverned.session.PendingEventQueue
import net.wetheGoverned.session.SessionManager
import net.wetheGoverned.zk.ZkProver
import kotlinx.datetime.Clock

class WsCivicPublisher(
    private val relayManager: NostrRelayManager,
    private val sessionManager: SessionManager,
    private val pendingQueue: PendingEventQueue,
    private val zkProver: ZkProver,
) : CivicPublisher {

    private val json = CivicJson

    override suspend fun signPublishImportCivicEvent(
        kind: Int,
        tags: List<List<String>>,
        content: String,
        pubKey: String
    ): String? {
        val normalizedPubKey = pubKey.lowercase()
        val nostrTags = tags.toMutableList()
        
        // Ensure geography tags are lowercase and consistent
        val normalizedTags = nostrTags.map { tag ->
            tag.mapIndexed { index, value -> if (index == 1) value.lowercase() else value }
        }
        
        val createdAt = Clock.System.now().toEpochMilliseconds() / 1000
        
        // Step 1: Compute Canonical ID
        val eventId = computeNostrId(
            pubKey = normalizedPubKey,
            createdAt = createdAt,
            kind = kind,
            tags = normalizedTags,
            content = content
        )

        // Step 2: Protocol-compliant BIP-340 Schnorr signature (128 hex chars)
        val session = sessionManager.currentSession
        val privateKey: String
        if (session != null && session.pubKey.lowercase() == normalizedPubKey) {
            println("🔑 WsCivicPublisher: Using session key for ${session.displayName} (pubKey: ${session.pubKey.take(8)}...)")
            privateKey = session.privateKey ?: NostrConstants.ADMIN_PRIVKEY
        } else if (normalizedPubKey == NostrConstants.ADMIN_PUBKEY) {
            println("🔑 WsCivicPublisher: Using ADMIN fallback key.")
            privateKey = NostrConstants.ADMIN_PRIVKEY
        } else {
            val warning = "⚠️ Key mismatch! pubKey=$normalizedPubKey but session=${session?.pubKey?.lowercase()}. Using ADMIN key."
            println("WsCivicPublisher: $warning")
            net.wetheGoverned.util.GlobalNotification.notify(warning)
            privateKey = NostrConstants.ADMIN_PRIVKEY
        }
        
        val signature = try {
            Secp256k1KeyManager.sign(eventId, privateKey)
        } catch (e: Exception) {
            val errorMsg = "❌ CRYPTO FAILURE: Failed to sign event ${eventId.take(8)}: ${e.message}"
            println("WsCivicPublisher: $errorMsg")
            net.wetheGoverned.util.GlobalNotification.notify(errorMsg)
            throw e
        }
        
        val event = CivicEvent(
            id = eventId,
            pubKey = normalizedPubKey,
            createdAt = createdAt,
            kind = kind,
            tags = normalizedTags,
            content = content,
            sig = signature
        )

        println("📤 Publishing to Mesh [Kind $kind]: ${event.id}")
        println("   - Signature: ${event.sig.take(16)}...${event.sig.takeLast(16)}")
        println("   - Content: ${content.take(100)}")

        // Step 3: Broadcast
        val isCritical = kind in listOf(
            CivicEventKind.FEDERAL_POLL, CivicEventKind.STATE_POLL, 
            CivicEventKind.DISTRICT_POLL, CivicEventKind.LOCAL_POLL, 
            CivicEventKind.POLL_VOTE, CivicEventKind.IMPORTANCE_VOTE
        )

        val preferred = if (!isCritical) relayManager.getPreferredRelays(normalizedPubKey) else null
        relayManager.publish(event, preferred)

        pendingQueue.enqueue(kind, content, event.sig)
        return event.id
    }

    /**
     * NIP-01 Canonical ID computation. 
     */
    private fun computeNostrId(pubKey: String, createdAt: Long, kind: Int, tags: List<List<String>>, content: String): String {
        return buildNip01EventId(pubKey, createdAt, kind, tags, content)
    }
}
