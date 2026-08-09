package net.wetheGoverned.model

import kotlinx.serialization.Serializable

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.serializer

//@Serializable(with = DistrictMetricSerializer::class)
data class DistrictMetric(
    val id: String,
    val districtId: String,
    val category: String,
    val name: String,
    val officialValue: String,
    val residentValue: String? = null,
    val unit: String,
    val source: MetricSource = MetricSource.OFFICIAL,
    val reportedAt: Long,
    val reporterPubKey: String? = null,
)

object DistrictMetricSerializer : KSerializer<DistrictMetric> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DistrictMetric") {
        element<String>("id")
        element<String>("districtId")
        element<String>("category")
        element<String>("name")
        element<String>("officialValue")
        element<String?>("residentValue")
        element<String>("unit")
        element<String>("source")
        element<Long>("reportedAt")
        element<String?>("reporterPubKey")
    }

    override fun serialize(encoder: Encoder, value: DistrictMetric) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeStringElement(descriptor, 0, value.id)
        composite.encodeStringElement(descriptor, 1, value.districtId)
        composite.encodeStringElement(descriptor, 2, value.category)
        composite.encodeStringElement(descriptor, 3, value.name)
        composite.encodeStringElement(descriptor, 4, value.officialValue)
        composite.encodeNullableSerializableElement(descriptor, 5, String.serializer(), value.residentValue)
        composite.encodeStringElement(descriptor, 6, value.unit)
        composite.encodeStringElement(descriptor, 7, value.source.name)
        composite.encodeLongElement(descriptor, 8, value.reportedAt)
        composite.encodeNullableSerializableElement(descriptor, 9, String.serializer(), value.reporterPubKey)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): DistrictMetric {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return DistrictMetric(
            id = json["id"]?.jsonPrimitive?.content ?: "",
            districtId = json["districtId"]?.jsonPrimitive?.content ?: "",
            category = json["category"]?.jsonPrimitive?.content ?: "",
            name = json["name"]?.jsonPrimitive?.content ?: "",
            officialValue = json["officialValue"]?.jsonPrimitive?.content ?: "",
            residentValue = json["residentValue"]?.jsonPrimitive?.contentOrNull,
            unit = json["unit"]?.jsonPrimitive?.content ?: "",
            source = MetricSource.valueOf(json["source"]?.jsonPrimitive?.content ?: "OFFICIAL"),
            reportedAt = json["reportedAt"]?.jsonPrimitive?.long ?: 0L,
            reporterPubKey = json["reporterPubKey"]?.jsonPrimitive?.contentOrNull
        )
    }
}
