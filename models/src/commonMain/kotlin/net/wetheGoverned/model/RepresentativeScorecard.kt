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

//@Serializable(with = ScorecardCategorySerializer::class)
data class ScorecardCategory(
    val name: String,
    val officialValue: String,
    val residentReportedValue: String? = null,
    val score: Int = 0
)

object ScorecardCategorySerializer : KSerializer<ScorecardCategory> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ScorecardCategory")

    override fun serialize(encoder: Encoder, value: ScorecardCategory) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("name", value.name)
            put("officialValue", value.officialValue)
            put("residentReportedValue", value.residentReportedValue)
            put("score", value.score)
        })
    }

    override fun deserialize(decoder: Decoder): ScorecardCategory {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return ScorecardCategory(
            name = json["name"]?.jsonPrimitive?.content ?: "",
            officialValue = json["officialValue"]?.jsonPrimitive?.content ?: "",
            residentReportedValue = json["residentReportedValue"]?.jsonPrimitive?.contentOrNull,
            score = json["score"]?.jsonPrimitive?.int ?: 0
        )
    }
}

//@Serializable(with = RepresentativeScorecardSerializer::class)
data class RepresentativeScorecard(
    val representativePubKey: String,
    val districtId: String,
    val scope: PollScope = PollScope.STATE,
    val name: String,
    val party: String,
    val overallScore: Int,
    val categories: List<ScorecardCategory>,
    val lastUpdated: Long,
)

object RepresentativeScorecardSerializer : KSerializer<RepresentativeScorecard> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RepresentativeScorecard")

    override fun serialize(encoder: Encoder, value: RepresentativeScorecard) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("representativePubKey", value.representativePubKey)
            put("districtId", value.districtId)
            put("scope", value.scope.name)
            put("name", value.name)
            put("party", value.party)
            put("overallScore", value.overallScore)
            put("categories", buildJsonArray {
                for (c in value.categories) {
                    add(output.json.encodeToJsonElement(ScorecardCategorySerializer, c))
                }
            })
            put("lastUpdated", value.lastUpdated)
        })
    }

    override fun deserialize(decoder: Decoder): RepresentativeScorecard {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return RepresentativeScorecard(
            representativePubKey = json["representativePubKey"]?.jsonPrimitive?.content ?: "",
            districtId = json["districtId"]?.jsonPrimitive?.content ?: "",
            scope = PollScope.valueOf(json["scope"]?.jsonPrimitive?.content ?: "STATE"),
            name = json["name"]?.jsonPrimitive?.content ?: "",
            party = json["party"]?.jsonPrimitive?.content ?: "",
            overallScore = json["overallScore"]?.jsonPrimitive?.int ?: 0,
            categories = json["categories"]?.jsonArray?.let { arr ->
                val list = mutableListOf<ScorecardCategory>()
                for (element in arr) {
                    list.add(input.json.decodeFromJsonElement(ScorecardCategorySerializer, element))
                }
                list
            } ?: emptyList(),
            lastUpdated = json["lastUpdated"]?.jsonPrimitive?.long ?: 0L
        )
    }
}
