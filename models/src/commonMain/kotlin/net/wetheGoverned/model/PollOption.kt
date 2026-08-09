package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

@Serializable(with = PollOptionSerializer::class)
data class PollOption(
    val id: String,
    val label: String,
    @SerialName("voteCount") val voteCount: Int = 0,
    @SerialName("percentageOfTotal") val percentageOfTotal: Float = 0f
)

object PollOptionSerializer : KSerializer<PollOption> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("PollOption")

    override fun serialize(encoder: Encoder, value: PollOption) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("id", value.id)
            put("label", value.label)
            put("voteCount", value.voteCount)
            put("percentageOfTotal", value.percentageOfTotal)
        })
    }

    override fun deserialize(decoder: Decoder): PollOption {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return PollOption(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            label = json["label"]?.jsonPrimitive?.content ?: "",
            voteCount = json["voteCount"]?.jsonPrimitive?.int ?: 0,
            percentageOfTotal = json["percentageOfTotal"]?.jsonPrimitive?.float ?: 0f
        )
    }
}
