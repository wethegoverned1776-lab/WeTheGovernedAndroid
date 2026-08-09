package net.wetheGoverned.model

import kotlinx.serialization.Serializable

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

//@Serializable(with = DistrictIdentifierSerializer::class)
data class DistrictIdentifier(
    val state: String,
    val district: Int,
    val isStateOnly: Boolean = false,
    val isFederal: Boolean = false
) {
    val id: String get() = when {
        isFederal -> "us"
        isStateOnly -> "us-${state.lowercase()}"
        else -> "us-${state.lowercase()}-${district.toString().padStart(2, '0')}"
    }

    val displayName: String get() = when {
        isFederal -> "United States"
        isStateOnly -> state
        else -> "$state District $district"
    }
}

object DistrictIdentifierSerializer : KSerializer<DistrictIdentifier> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DistrictIdentifier")

    override fun serialize(encoder: Encoder, value: DistrictIdentifier) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("state", value.state)
            put("district", value.district)
            put("isStateOnly", value.isStateOnly)
            put("isFederal", value.isFederal)
        })
    }

    override fun deserialize(decoder: Decoder): DistrictIdentifier {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return DistrictIdentifier(
            state = json["state"]?.jsonPrimitive?.content ?: "",
            district = json["district"]?.jsonPrimitive?.int ?: 0,
            isStateOnly = json["isStateOnly"]?.jsonPrimitive?.boolean ?: false,
            isFederal = json["isFederal"]?.jsonPrimitive?.boolean ?: false
        )
    }
}

//@Serializable(with = DistrictRegistryDataSerializer::class)
data class DistrictRegistryData(
    val version: String,
    val districts: List<DistrictIdentifier>,
    val zipMappings: Map<String, String>,
    val stateDefaults: Map<String, String>
)

object DistrictRegistryDataSerializer : KSerializer<DistrictRegistryData> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DistrictRegistryData")

    override fun serialize(encoder: Encoder, value: DistrictRegistryData) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("version", value.version)
            put("districts", buildJsonArray {
                value.districts.forEach { add(output.json.encodeToJsonElement(DistrictIdentifierSerializer, it)) }
            })
            put("zipMappings", buildJsonObject {
                value.zipMappings.forEach { (k, v) -> put(k, v) }
            })
            put("stateDefaults", buildJsonObject {
                value.stateDefaults.forEach { (k, v) -> put(k, v) }
            })
        })
    }

    override fun deserialize(decoder: Decoder): DistrictRegistryData {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return DistrictRegistryData(
            version = json["version"]?.jsonPrimitive?.content ?: "",
            districts = json["districts"]?.jsonArray?.map { input.json.decodeFromJsonElement(DistrictIdentifierSerializer, it) } ?: emptyList(),
            zipMappings = json["zipMappings"]?.jsonObject?.let { obj ->
                val map = mutableMapOf<String, String>()
                obj.forEach { (k, v) -> map[k] = v.jsonPrimitive.content }
                map
            } ?: emptyMap(),
            stateDefaults = json["stateDefaults"]?.jsonObject?.let { obj ->
                val map = mutableMapOf<String, String>()
                obj.forEach { (k, v) -> map[k] = v.jsonPrimitive.content }
                map
            } ?: emptyMap()
        )
    }
}

object DistrictRegistry {
    private var currentData: DistrictRegistryData? = null

    val defaultDistricts = listOf(
        DistrictIdentifier("US", 0, isFederal = true),
        DistrictIdentifier("AL", 1), DistrictIdentifier("AL", 2), DistrictIdentifier("AL", 3),
        DistrictIdentifier("AL", 4), DistrictIdentifier("AL", 5), DistrictIdentifier("AL", 6), DistrictIdentifier("AL", 7),
        DistrictIdentifier("FL", 4), DistrictIdentifier("FL", 6), DistrictIdentifier("FL", 27),
        DistrictIdentifier("WA", 7), DistrictIdentifier("WA", 1),
        DistrictIdentifier("GA", 5), DistrictIdentifier("GA", 6),
        DistrictIdentifier("TX", 1), DistrictIdentifier("TX", 10),
        DistrictIdentifier("NY", 10), DistrictIdentifier("NY", 22),
        DistrictIdentifier("CA", 1), DistrictIdentifier("CA", 36)
    )

    val allDistricts: List<DistrictIdentifier> get() = currentData?.districts ?: defaultDistricts

    fun update(data: DistrictRegistryData) {
        currentData = data
    }

    fun getZipMapping(zip: String): String? = currentData?.zipMappings?.get(zip)
    fun getStateDefault(stateCode: String): String? = currentData?.stateDefaults?.get(stateCode.lowercase())
}
