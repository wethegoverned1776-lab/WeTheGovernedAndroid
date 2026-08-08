package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock

@Serializable
data class VerificationRequest(
    val id: String,
    val requesterPubKey: String,
    val requesterDisplayName: String,
    val email: String,
    val districtId: String,
    val stateId: String,
    val address: String,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val status: VerificationRequestStatus = VerificationRequestStatus.PENDING,
    val handledByPubKey: String? = null
)
