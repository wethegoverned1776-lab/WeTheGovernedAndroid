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

@Serializable(with = CivicVoteSerializer::class)
data class CivicVote(
    val id: String,
    val pollId: String,
    val voterPubKey: String,
    val voterName: String,
    val optionId: String,
    val timestamp: Long,
    val nonce: Long,
    val signature: String? = null,
    val isFlagged: Boolean = false,
    val flagReason: String? = null,
    val disputeComment: String? = null,
    val disputeExpiresAt: Long? = null,
    val status: ConflictStatus = ConflictStatus.NONE,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds()
)

object CivicVoteSerializer : KSerializer<CivicVote> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CivicVote")

    override fun serialize(encoder: Encoder, value: CivicVote) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("id", value.id)
            put("pollId", value.pollId)
            put("voterPubKey", value.voterPubKey)
            put("voterName", value.voterName)
            put("optionId", value.optionId)
            put("timestamp", value.timestamp)
            put("nonce", value.nonce)
            put("signature", value.signature)
            put("isFlagged", value.isFlagged)
            put("flagReason", value.flagReason)
            put("disputeComment", value.disputeComment)
            put("disputeExpiresAt", value.disputeExpiresAt)
            put("status", value.status.name)
            put("createdAt", value.createdAt)
        })
    }

    override fun deserialize(decoder: Decoder): CivicVote {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return CivicVote(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            pollId = json["pollId"]?.jsonPrimitive?.content ?: "",
            voterPubKey = json["voterPubKey"]?.jsonPrimitive?.content ?: "",
            voterName = json["voterName"]?.jsonPrimitive?.content ?: "",
            optionId = json["optionId"]?.jsonPrimitive?.content ?: "",
            timestamp = json["timestamp"]?.jsonPrimitive?.long ?: 0L,
            nonce = json["nonce"]?.jsonPrimitive?.long ?: 0L,
            signature = json["signature"]?.jsonPrimitive?.contentOrNull,
            isFlagged = json["isFlagged"]?.jsonPrimitive?.boolean ?: false,
            flagReason = json["flagReason"]?.jsonPrimitive?.contentOrNull,
            disputeComment = json["disputeComment"]?.jsonPrimitive?.contentOrNull,
            disputeExpiresAt = json["disputeExpiresAt"]?.jsonPrimitive?.longOrNull,
            status = ConflictStatus.valueOf(json["status"]?.jsonPrimitive?.content ?: "NONE"),
            createdAt = json["createdAt"]?.jsonPrimitive?.long ?: 0L
        )
    }
}
