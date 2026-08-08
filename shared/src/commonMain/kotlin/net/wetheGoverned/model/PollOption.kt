package net.wetheGoverned.model

import kotlinx.serialization.Serializable

@Serializable
data class PollOption(
    val id: String,
    val label: String,
    val voteCount: Int = 0,
    val percentageOfTotal: Float = 0f
)
