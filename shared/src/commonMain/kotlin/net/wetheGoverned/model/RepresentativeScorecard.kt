package net.wetheGoverned.model

import kotlinx.serialization.Serializable

@Serializable
data class ScorecardCategory(
    val name: String,
    val officialValue: String,
    val residentReportedValue: String? = null,
    val score: Int = 0
)

@Serializable
data class RepresentativeScorecard(
    val representativePubKey: String,
    val districtId: String,
    val scope: PollScope = PollScope.STATE,
    val name: String,
    val party: String,
    val overallScore: Int,
    val categories: List<ScorecardCategory>,
    val lastUpdated: Long,
)
