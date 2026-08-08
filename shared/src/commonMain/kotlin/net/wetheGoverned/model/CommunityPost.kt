package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock

@Serializable
data class CommunityPost(
    val id: String,
    val authorPubKey: String,
    val districtId: String,
    val kind: CommunityPostKind,
    val title: String,
    val description: String,
    val price: Double? = null,
    val location: String? = null,
    val contactInfo: String? = null,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val expiresAt: Long? = null,
    val tags: List<String> = emptyList()
)
