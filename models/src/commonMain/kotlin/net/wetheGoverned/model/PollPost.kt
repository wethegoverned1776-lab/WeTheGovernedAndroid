package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock


import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.serializer

//@Serializable(with = PollPostSerializer::class)
data class PollPost(
    val id: String,
    val pollId: String,
    val optionId: String,
    val parentPostId: String? = null,
    val headline: String? = null,
    val authorName: String,
    val content: String,
    val score: Int = 0,
    val userVote: Int = 0,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds()
)

object PollPostSerializer : KSerializer<PollPost> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("PollPost") {
        element<String>("id")
        element<String>("pollId")
        element<String>("optionId")
        element<String?>("parentPostId")
        element<String?>("headline")
        element<String>("authorName")
        element<String>("content")
        element<Int>("score")
        element<Int>("userVote")
        element<Long>("createdAt")
    }

    override fun serialize(encoder: Encoder, value: PollPost) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeStringElement(descriptor, 0, value.id)
        composite.encodeStringElement(descriptor, 1, value.pollId)
        composite.encodeStringElement(descriptor, 2, value.optionId)
        composite.encodeNullableSerializableElement(descriptor, 3, String.serializer(), value.parentPostId)
        composite.encodeNullableSerializableElement(descriptor, 4, String.serializer(), value.headline)
        composite.encodeStringElement(descriptor, 5, value.authorName)
        composite.encodeStringElement(descriptor, 6, value.content)
        composite.encodeIntElement(descriptor, 7, value.score)
        composite.encodeIntElement(descriptor, 8, value.userVote)
        composite.encodeLongElement(descriptor, 9, value.createdAt)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): PollPost {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return PollPost(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            pollId = json["pollId"]?.jsonPrimitive?.content ?: "",
            optionId = json["optionId"]?.jsonPrimitive?.content ?: "",
            parentPostId = json["parentPostId"]?.jsonPrimitive?.contentOrNull,
            headline = json["headline"]?.jsonPrimitive?.contentOrNull,
            authorName = json["authorName"]?.jsonPrimitive?.content ?: "",
            content = json["content"]?.jsonPrimitive?.content ?: "",
            score = json["score"]?.jsonPrimitive?.int ?: 0,
            userVote = json["userVote"]?.jsonPrimitive?.int ?: 0,
            createdAt = json["createdAt"]?.jsonPrimitive?.long ?: 0L
        )
    }
}
