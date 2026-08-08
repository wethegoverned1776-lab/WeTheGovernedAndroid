package net.wetheGoverned.model

import kotlinx.serialization.Serializable

@Serializable
data class District(
    val id: String,
    val level: DistrictLevel = DistrictLevel.FEDERAL_HOUSE,
    val state: String,
    val districtNumber: Int? = null,
    val name: String,
    val displayName: String,
    val representativeName: String? = null,
    val representativeParty: String? = null,
    val geoBoundaries: String? = null,
)
