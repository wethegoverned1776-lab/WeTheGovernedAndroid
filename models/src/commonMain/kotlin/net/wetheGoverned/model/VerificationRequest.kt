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

@Serializable(with = VerificationRequestSerializer::class)
data class VerificationRequest(
    val id: String,
    val requesterPubKey: String,
    val requesterDisplayName: String,
    val email: String,
    val districtId: String,
    val stateId: String,
    val address: String,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val status: VerificationRequestStatus = VerificationRequestStatus.PENDING,
    val handledByPubKey: String? = null
)

object VerificationRequestSerializer : KSerializer<VerificationRequest> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("VerificationRequest") {
        element<String>("id")
        element<String>("requesterPubKey")
        element<String>("requesterDisplayName")
        element<String>("email")
        element<String>("districtId")
        element<String>("stateId")
        element<String>("address")
        element<Long>("createdAt")
        element<String>("status")
        element<String?>("handledByPubKey")
    }

    override fun serialize(encoder: Encoder, value: VerificationRequest) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeStringElement(descriptor, 0, value.id)
        composite.encodeStringElement(descriptor, 1, value.requesterPubKey)
        composite.encodeStringElement(descriptor, 2, value.requesterDisplayName)
        composite.encodeStringElement(descriptor, 3, value.email)
        composite.encodeStringElement(descriptor, 4, value.districtId)
        composite.encodeStringElement(descriptor, 5, value.stateId)
        composite.encodeStringElement(descriptor, 6, value.address)
        composite.encodeLongElement(descriptor, 7, value.createdAt)
        composite.encodeStringElement(descriptor, 8, value.status.name)
        composite.encodeNullableSerializableElement(descriptor, 9, String.serializer(), value.handledByPubKey)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): VerificationRequest {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return VerificationRequest(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            requesterPubKey = json["requesterPubKey"]?.jsonPrimitive?.content ?: "",
            requesterDisplayName = json["requesterDisplayName"]?.jsonPrimitive?.content ?: "",
            email = json["email"]?.jsonPrimitive?.content ?: "",
            districtId = json["districtId"]?.jsonPrimitive?.content ?: "",
            stateId = json["stateId"]?.jsonPrimitive?.content ?: "",
            address = json["address"]?.jsonPrimitive?.content ?: "",
            createdAt = json["createdAt"]?.jsonPrimitive?.long ?: 0L,
            status = VerificationRequestStatus.valueOf(json["status"]?.jsonPrimitive?.content ?: "PENDING"),
            handledByPubKey = json["handledByPubKey"]?.jsonPrimitive?.contentOrNull
        )
    }
}
