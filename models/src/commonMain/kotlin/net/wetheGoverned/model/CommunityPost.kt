package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

//@Serializable(with = CommunityPostSerializer::class)
data class CommunityPost(
    val id: String,
    val authorPubKey: String,
    val districtId: String,
    val kind: CommunityPostKind,
    val title: String,
    val description: String,
    val price: Double? = null,
    val location: String? = null,
    val contactInfo: String? = null,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val expiresAt: Long? = null,
    val tags: List<String> = emptyList()
)

object CommunityPostSerializer : KSerializer<CommunityPost> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CommunityPost")

    override fun serialize(encoder: Encoder, value: CommunityPost) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("id", value.id)
            put("authorPubKey", value.authorPubKey)
            put("districtId", value.districtId)
            put("kind", value.kind.name)
            put("title", value.title)
            put("description", value.description)
            put("price", value.price)
            put("location", value.location)
            put("contactInfo", value.contactInfo)
            put("createdAt", value.createdAt)
            put("expiresAt", value.expiresAt)
            put("tags", buildJsonArray { value.tags.forEach { add(it) } })
        })
    }

    override fun deserialize(decoder: Decoder): CommunityPost {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return CommunityPost(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            authorPubKey = json["authorPubKey"]?.jsonPrimitive?.content ?: "",
            districtId = json["districtId"]?.jsonPrimitive?.content ?: "",
            kind = CommunityPostKind.valueOf(json["kind"]?.jsonPrimitive?.content ?: "DISCUSSION"),
            title = json["title"]?.jsonPrimitive?.content ?: "",
            description = json["description"]?.jsonPrimitive?.content ?: "",
            price = json["price"]?.jsonPrimitive?.doubleOrNull,
            location = json["location"]?.jsonPrimitive?.contentOrNull,
            contactInfo = json["contactInfo"]?.jsonPrimitive?.contentOrNull,
            createdAt = json["createdAt"]?.jsonPrimitive?.long ?: 0L,
            expiresAt = json["expiresAt"]?.jsonPrimitive?.longOrNull,
            tags = json["tags"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
        )
    }
}
