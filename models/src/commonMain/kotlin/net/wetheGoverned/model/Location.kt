package net.wetheGoverned.model

import kotlinx.serialization.Serializable

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

//@Serializable(with = CivicLocationSerializer::class)
data class Location(
    val latitude: Double,
    val longitude: Double
)

object CivicLocationSerializer : KSerializer<Location> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Location") {
        element<Double>("latitude")
        element<Double>("longitude")
    }

    override fun serialize(encoder: Encoder, value: Location) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeDoubleElement(descriptor, 0, value.latitude)
        composite.encodeDoubleElement(descriptor, 1, value.longitude)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): Location {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return Location(
            latitude = json["latitude"]?.jsonPrimitive?.double ?: 0.0,
            longitude = json["longitude"]?.jsonPrimitive?.double ?: 0.0
        )
    }
}
