package net.wetheGoverned.model

import kotlinx.serialization.Serializable


import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.serializer

@Serializable(with = ResidentProfileSerializer::class)
data class ResidentProfile(
    val pubKey: String,
    val displayName: String,
    val federalHouseId: String? = null,
    val federalSenateId: String? = null,
    val stateSenateId: String? = null,
    val stateHouseId: String? = null,
    val countyId: String? = null,
    val cityId: String? = null,
    val schoolBoardId: String? = null,
    val tier: VerificationTier,
    val avatarUrl: String? = null,
    val joinedAt: Long,
    val addressFingerprint: String? = null,
    val verifiedByPubKey: String? = null,
    val address: String? = null,
    val isVerified: Boolean = false,
    
    // Legacy support
    val districtId: String? = null,
    val localId: String? = null
)

object ResidentProfileSerializer : KSerializer<ResidentProfile> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ResidentProfile")

    override fun serialize(encoder: Encoder, value: ResidentProfile) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("pubKey", value.pubKey)
            put("displayName", value.displayName)
            put("federalHouseId", value.federalHouseId)
            put("federalSenateId", value.federalSenateId)
            put("stateSenateId", value.stateSenateId)
            put("stateHouseId", value.stateHouseId)
            put("countyId", value.countyId)
            put("cityId", value.cityId)
            put("schoolBoardId", value.schoolBoardId)
            put("tier", value.tier.name)
            put("avatarUrl", value.avatarUrl)
            put("joinedAt", value.joinedAt)
            put("addressFingerprint", value.addressFingerprint)
            put("verifiedByPubKey", value.verifiedByPubKey)
            put("address", value.address)
            put("isVerified", value.isVerified)
            put("districtId", value.districtId)
            put("localId", value.localId)
        })
    }

    override fun deserialize(decoder: Decoder): ResidentProfile {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return ResidentProfile(
            pubKey = json["pubKey"]?.jsonPrimitive?.content ?: "",
            displayName = json["displayName"]?.jsonPrimitive?.content ?: "",
            federalHouseId = json["federalHouseId"]?.jsonPrimitive?.contentOrNull,
            federalSenateId = json["federalSenateId"]?.jsonPrimitive?.contentOrNull,
            stateSenateId = json["stateSenateId"]?.jsonPrimitive?.contentOrNull,
            stateHouseId = json["stateHouseId"]?.jsonPrimitive?.contentOrNull,
            countyId = json["countyId"]?.jsonPrimitive?.contentOrNull,
            cityId = json["cityId"]?.jsonPrimitive?.contentOrNull,
            schoolBoardId = json["schoolBoardId"]?.jsonPrimitive?.contentOrNull,
            tier = VerificationTier.valueOf(json["tier"]?.jsonPrimitive?.content ?: "UNVERIFIED"),
            avatarUrl = json["avatarUrl"]?.jsonPrimitive?.contentOrNull,
            joinedAt = json["joinedAt"]?.jsonPrimitive?.long ?: 0L,
            addressFingerprint = json["addressFingerprint"]?.jsonPrimitive?.contentOrNull,
            verifiedByPubKey = json["verifiedByPubKey"]?.jsonPrimitive?.contentOrNull,
            address = json["address"]?.jsonPrimitive?.contentOrNull,
            isVerified = json["isVerified"]?.jsonPrimitive?.boolean ?: false,
            districtId = json["districtId"]?.jsonPrimitive?.contentOrNull,
            localId = json["localId"]?.jsonPrimitive?.contentOrNull
        )
    }
}
