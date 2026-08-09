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

@Serializable(with = CivicPollSerializer::class)
data class CivicPoll(
    val id: String,
    val scope: PollScope = PollScope.DISTRICT,
    val districtId: String,
    val localId: String? = null,
    val authorPubKey: String,
    val question: String,
    val options: List<PollOption>,
    val status: PollStatus = PollStatus.ACTIVE,
    val createdAt: Long,
    val closesAt: Long? = null,
    val totalVotes: Int = 0,
    val importanceScore: Int = 0,
    val userImportanceVote: Int = 0,
    val residentVoteOption: String? = null,
    val linkedLegislationId: String? = null,
    val districtBreakdown: Map<String, Int>? = null
)

object CivicPollSerializer : KSerializer<CivicPoll> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CivicPoll")

    override fun serialize(encoder: Encoder, value: CivicPoll) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        val json = buildJsonObject {
            put("id", value.id)
            put("scope", value.scope.name)
            put("districtId", value.districtId)
            put("localId", value.localId)
            put("authorPubKey", value.authorPubKey)
            put("question", value.question)
            put("options", buildJsonArray {
                value.options.forEach { add(output.json.encodeToJsonElement(PollOptionSerializer, it)) }
            })
            put("status", value.status.name)
            put("createdAt", value.createdAt)
            put("closesAt", value.closesAt)
            put("totalVotes", value.totalVotes)
            put("importanceScore", value.importanceScore)
            put("userImportanceVote", value.userImportanceVote)
            put("residentVoteOption", value.residentVoteOption)
            put("linkedLegislationId", value.linkedLegislationId)
            value.districtBreakdown?.let { breakdown ->
                put("districtBreakdown", buildJsonObject {
                    breakdown.forEach { (k, v) -> put(k, v) }
                })
            }
        }
        output.encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): CivicPoll {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return CivicPoll(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            scope = PollScope.valueOf(json["scope"]?.jsonPrimitive?.content ?: "DISTRICT"),
            districtId = json["districtId"]?.jsonPrimitive?.content ?: "",
            localId = json["localId"]?.jsonPrimitive?.contentOrNull,
            authorPubKey = json["authorPubKey"]?.jsonPrimitive?.content ?: "",
            question = json["question"]?.jsonPrimitive?.content ?: "",
            options = json["options"]?.jsonArray?.map { input.json.decodeFromJsonElement(PollOptionSerializer, it) } ?: emptyList(),
            status = PollStatus.valueOf(json["status"]?.jsonPrimitive?.content ?: "ACTIVE"),
            createdAt = json["createdAt"]?.jsonPrimitive?.long ?: 0L,
            closesAt = json["closesAt"]?.jsonPrimitive?.longOrNull,
            totalVotes = json["totalVotes"]?.jsonPrimitive?.int ?: 0,
            importanceScore = json["importanceScore"]?.jsonPrimitive?.int ?: 0,
            userImportanceVote = json["userImportanceVote"]?.jsonPrimitive?.int ?: 0,
            residentVoteOption = json["residentVoteOption"]?.jsonPrimitive?.contentOrNull,
            linkedLegislationId = json["linkedLegislationId"]?.jsonPrimitive?.contentOrNull,
            districtBreakdown = json["districtBreakdown"]?.jsonObject?.let { 
                it.mapValues { (_, v) -> v.jsonPrimitive.int }
            }
        )
    }
}
