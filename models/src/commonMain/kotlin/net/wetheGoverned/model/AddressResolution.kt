package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock

//@Serializable
data class AddressResolution(
    val address: String,
    val federalDistrict: District? = null,
    val stateUpperDistrict: District? = null,
    val stateLowerDistrict: District? = null,
    val localJurisdiction: String? = null,
    val sources: List<String> = emptyList(),
    val timestamp: Long = Clock.System.now().toEpochMilliseconds()
)
