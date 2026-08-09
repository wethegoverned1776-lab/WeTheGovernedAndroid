package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.builtins.ListSerializer

@Serializable(with = CivicEventSerializer::class)
data class CivicEvent(
    val id: String,
    @SerialName("pubkey") val pubKey: String,
    @SerialName("created_at") val createdAt: Long,
    val kind: Int,
    val tags: List<List<String>>,
    val content: String,
    val sig: String,
)

object CivicEventSerializer : KSerializer<CivicEvent> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CivicEvent")

    override fun serialize(encoder: Encoder, value: CivicEvent) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        val json = buildJsonObject {
            put("id", value.id)
            put("pubkey", value.pubKey)
            put("created_at", value.createdAt)
            put("kind", value.kind)
            put("tags", buildJsonArray {
                value.tags.forEach { tag ->
                    add(buildJsonArray {
                        tag.forEach { add(it) }
                    })
                }
            })
            put("content", value.content)
            put("sig", value.sig)
        }
        output.encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): CivicEvent {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return CivicEvent(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            pubKey = json["pubkey"]?.jsonPrimitive?.content ?: "",
            createdAt = json["created_at"]?.jsonPrimitive?.long ?: 0L,
            kind = json["kind"]?.jsonPrimitive?.int ?: 0,
            tags = json["tags"]?.jsonArray?.map { it.jsonArray.map { e -> e.jsonPrimitive.content } } ?: emptyList(),
            content = json["content"]?.jsonPrimitive?.content ?: "",
            sig = json["sig"]?.jsonPrimitive?.content ?: ""
        )
    }
}
