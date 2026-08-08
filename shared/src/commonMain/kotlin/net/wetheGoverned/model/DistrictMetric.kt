package net.wetheGoverned.model

import kotlinx.serialization.Serializable

@Serializable
data class DistrictMetric(
    val id: String,
    val districtId: String,
    val category: String,
    val name: String,
    val officialValue: String,
    val residentValue: String? = null,
    val unit: String,
    val source: MetricSource = MetricSource.OFFICIAL,
    val reportedAt: Long,
    val reporterPubKey: String? = null,
)
