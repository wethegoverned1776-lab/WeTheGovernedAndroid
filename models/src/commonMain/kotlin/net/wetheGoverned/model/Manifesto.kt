package net.wetheGoverned.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

//@Serializable(with = ManifestoQuestionSerializer::class)
data class ManifestoQuestion(
    val id: String,
    val askerPubKey: String,
    val text: String,
    val askedAt: Long,
    val answer: String? = null,
    val answeredAt: Long? = null,
)

object ManifestoQuestionSerializer : KSerializer<ManifestoQuestion> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ManifestoQuestion")

    override fun serialize(encoder: Encoder, value: ManifestoQuestion) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("id", value.id)
            put("askerPubKey", value.askerPubKey)
            put("text", value.text)
            put("askedAt", value.askedAt)
            put("answer", value.answer)
            put("answeredAt", value.answeredAt)
        })
    }

    override fun deserialize(decoder: Decoder): ManifestoQuestion {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return ManifestoQuestion(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            askerPubKey = json["askerPubKey"]?.jsonPrimitive?.content ?: "",
            text = json["text"]?.jsonPrimitive?.content ?: "",
            askedAt = json["askedAt"]?.jsonPrimitive?.long ?: 0L,
            answer = json["answer"]?.jsonPrimitive?.contentOrNull,
            answeredAt = json["answeredAt"]?.jsonPrimitive?.longOrNull
        )
    }
}

//@Serializable(with = CandidateManifestoSerializer::class)
data class CandidateManifesto(
    val id: String,
    val candidatePubKey: String,
    val districtId: String,
    val scope: PollScope = PollScope.STATE,
    val title: String,
    val body: String,
    val publishedAt: Long,
    val questions: List<ManifestoQuestion>,
)

object CandidateManifestoSerializer : KSerializer<CandidateManifesto> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CandidateManifesto")

    override fun serialize(encoder: Encoder, value: CandidateManifesto) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("id", value.id)
            put("candidatePubKey", value.candidatePubKey)
            put("districtId", value.districtId)
            put("scope", value.scope.name)
            put("title", value.title)
            put("body", value.body)
            put("publishedAt", value.publishedAt)
            put("questions", buildJsonArray {
                for (q in value.questions) {
                    add(output.json.encodeToJsonElement(ManifestoQuestionSerializer, q))
                }
            })
        })
    }

    override fun deserialize(decoder: Decoder): CandidateManifesto {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return CandidateManifesto(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            candidatePubKey = json["candidatePubKey"]?.jsonPrimitive?.content ?: "",
            districtId = json["districtId"]?.jsonPrimitive?.content ?: "",
            scope = PollScope.valueOf(json["scope"]?.jsonPrimitive?.content ?: "STATE"),
            title = json["title"]?.jsonPrimitive?.content ?: "",
            body = json["body"]?.jsonPrimitive?.content ?: "",
            publishedAt = json["publishedAt"]?.jsonPrimitive?.long ?: 0L,
            questions = json["questions"]?.jsonArray?.let { arr ->
                val list = mutableListOf<ManifestoQuestion>()
                for (element in arr) {
                    list.add(input.json.decodeFromJsonElement(ManifestoQuestionSerializer, element))
                }
                list
            } ?: emptyList()
        )
    }
}
