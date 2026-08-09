package net.wetheGoverned.model

import kotlinx.serialization.Serializable

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.serializer

//@Serializable(with = DistrictSerializer::class)
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

object DistrictSerializer : KSerializer<District> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("District")

    override fun serialize(encoder: Encoder, value: District) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("id", value.id)
            put("level", value.level.name)
            put("state", value.state)
            put("districtNumber", value.districtNumber)
            put("name", value.name)
            put("displayName", value.displayName)
            put("representativeName", value.representativeName)
            put("representativeParty", value.representativeParty)
            put("geoBoundaries", value.geoBoundaries)
        })
    }

    override fun deserialize(decoder: Decoder): District {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return District(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            level = DistrictLevel.valueOf(json["level"]?.jsonPrimitive?.content ?: "FEDERAL_HOUSE"),
            state = json["state"]?.jsonPrimitive?.content ?: "",
            districtNumber = json["districtNumber"]?.jsonPrimitive?.intOrNull,
            name = json["name"]?.jsonPrimitive?.content ?: "",
            displayName = json["displayName"]?.jsonPrimitive?.content ?: "",
            representativeName = json["representativeName"]?.jsonPrimitive?.contentOrNull,
            representativeParty = json["representativeParty"]?.jsonPrimitive?.contentOrNull,
            geoBoundaries = json["geoBoundaries"]?.jsonPrimitive?.contentOrNull
        )
    }
}
