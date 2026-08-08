package net.wetheGoverned.model

import kotlinx.serialization.Serializable

@Serializable
data class ManifestoQuestion(
    val id: String,
    val askerPubKey: String,
    val text: String,
    val askedAt: Long,
    val answer: String? = null,
    val answeredAt: Long? = null,
)

@Serializable
data class CandidateManifesto(
    val id: String,
    val candidatePubKey: String,
    val districtId: String,
    val scope: PollScope = PollScope.STATE,
    val title: String,
    val body: String,
    val publishedAt: Long,
    val questions: List<ManifestoQuestion>,
)
