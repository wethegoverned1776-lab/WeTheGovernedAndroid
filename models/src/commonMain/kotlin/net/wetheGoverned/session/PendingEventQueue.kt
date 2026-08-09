package net.wetheGoverned.session

import kotlinx.serialization.Serializable

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

//@Serializable(with = PendingEventSerializer::class)
data class PendingEvent(
    val eventId: String,
    val kind: Int,
    val contentJson: String,
    val sig: String,
    val createdAt: Long
)

object PendingEventSerializer : KSerializer<PendingEvent> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("PendingEvent") {
        element<String>("eventId")
        element<Int>("kind")
        element<String>("contentJson")
        element<String>("sig")
        element<Long>("createdAt")
    }

    override fun serialize(encoder: Encoder, value: PendingEvent) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeStringElement(descriptor, 0, value.eventId)
        composite.encodeIntElement(descriptor, 1, value.kind)
        composite.encodeStringElement(descriptor, 2, value.contentJson)
        composite.encodeStringElement(descriptor, 3, value.sig)
        composite.encodeLongElement(descriptor, 4, value.createdAt)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): PendingEvent {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return PendingEvent(
            eventId = json["eventId"]?.jsonPrimitive?.content ?: "",
            kind = json["kind"]?.jsonPrimitive?.int ?: 0,
            contentJson = json["contentJson"]?.jsonPrimitive?.content ?: "",
            sig = json["sig"]?.jsonPrimitive?.content ?: "",
            createdAt = json["createdAt"]?.jsonPrimitive?.long ?: 0L
        )
    }
}

interface PendingEventQueue {
    suspend fun enqueue(kind: Int, contentJson: String, sig: String)
    suspend fun getAllPending(): List<PendingEvent>
    suspend fun dequeue(eventId: String)
}
