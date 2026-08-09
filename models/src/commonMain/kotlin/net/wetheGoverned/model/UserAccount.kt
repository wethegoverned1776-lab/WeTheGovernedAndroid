package net.wetheGoverned.model

import kotlinx.serialization.Serializable


import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.serializer

//@Serializable(with = UserAccountSerializer::class)
data class UserAccount(
    val username: String,
    val password: String,
    val pubKey: String,
    val privateKey: String,
    val districtId: String? = null,
    // Loop 75 Fix: Post-Quantum Identity Extension
    val quantumPubKey: String? = null,
    val quantumPrivateKey: String? = null,
    val requiresPasswordChange: Boolean = false
)

object UserAccountSerializer : KSerializer<UserAccount> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("UserAccount") {
        element<String>("username")
        element<String>("password")
        element<String>("pubKey")
        element<String>("privateKey")
        element<String?>("districtId")
        element<String?>("quantumPubKey")
        element<String?>("quantumPrivateKey")
        element<Boolean>("requiresPasswordChange")
    }

    override fun serialize(encoder: Encoder, value: UserAccount) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeStringElement(descriptor, 0, value.username)
        composite.encodeStringElement(descriptor, 1, value.password)
        composite.encodeStringElement(descriptor, 2, value.pubKey)
        composite.encodeStringElement(descriptor, 3, value.privateKey)
        composite.encodeNullableSerializableElement(descriptor, 4, String.serializer(), value.districtId)
        composite.encodeNullableSerializableElement(descriptor, 5, String.serializer(), value.quantumPubKey)
        composite.encodeNullableSerializableElement(descriptor, 6, String.serializer(), value.quantumPrivateKey)
        composite.encodeBooleanElement(descriptor, 7, value.requiresPasswordChange)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): UserAccount {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return UserAccount(
            username = json["username"]?.jsonPrimitive?.content ?: "",
            password = json["password"]?.jsonPrimitive?.content ?: "",
            pubKey = json["pubKey"]?.jsonPrimitive?.content ?: "",
            privateKey = json["privateKey"]?.jsonPrimitive?.content ?: "",
            districtId = json["districtId"]?.jsonPrimitive?.contentOrNull,
            quantumPubKey = json["quantumPubKey"]?.jsonPrimitive?.contentOrNull,
            quantumPrivateKey = json["quantumPrivateKey"]?.jsonPrimitive?.contentOrNull,
            requiresPasswordChange = json["requiresPasswordChange"]?.jsonPrimitive?.boolean ?: false
        )
    }
}
