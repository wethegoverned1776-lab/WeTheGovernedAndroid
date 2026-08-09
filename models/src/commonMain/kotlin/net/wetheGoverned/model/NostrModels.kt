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

/**
 * NIP-11: Relay Information Document
 */
//@Serializable(with = RelayInfoSerializer::class)
data class RelayInfo(
    val name: String? = null,
    val description: String? = null,
    val pubkey: String? = null,
    val contact: String? = null,
    val supported_nips: List<Int>? = null,
    val software: String? = null,
    val version: String? = null,
    val limitation: RelayLimitation? = null,
    val payments_url: String? = null,
    val fees: RelayFees? = null
)

object RelayInfoSerializer : KSerializer<RelayInfo> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RelayInfo")

    override fun serialize(encoder: Encoder, value: RelayInfo) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("name", value.name)
            put("description", value.description)
            put("pubkey", value.pubkey)
            put("contact", value.contact)
            value.supported_nips?.let { nips ->
                put("supported_nips", buildJsonArray { nips.forEach { add(it) } })
            }
            put("software", value.software)
            put("version", value.version)
            value.limitation?.let { put("limitation", output.json.encodeToJsonElement(RelayLimitationSerializer, it)) }
            put("payments_url", value.payments_url)
            value.fees?.let { put("fees", output.json.encodeToJsonElement(RelayFeesSerializer, it)) }
        })
    }

    override fun deserialize(decoder: Decoder): RelayInfo {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return RelayInfo(
            name = json["name"]?.jsonPrimitive?.contentOrNull,
            description = json["description"]?.jsonPrimitive?.contentOrNull,
            pubkey = json["pubkey"]?.jsonPrimitive?.contentOrNull,
            contact = json["contact"]?.jsonPrimitive?.contentOrNull,
            supported_nips = json["supported_nips"]?.jsonArray?.map { it.jsonPrimitive.int },
            software = json["software"]?.jsonPrimitive?.contentOrNull,
            version = json["version"]?.jsonPrimitive?.contentOrNull,
            limitation = json["limitation"]?.let { input.json.decodeFromJsonElement(RelayLimitationSerializer, it) },
            payments_url = json["payments_url"]?.jsonPrimitive?.contentOrNull,
            fees = json["fees"]?.let { input.json.decodeFromJsonElement(RelayFeesSerializer, it) }
        )
    }
}

//@Serializable(with = RelayLimitationSerializer::class)
data class RelayLimitation(
    val max_message_length: Int? = null,
    val max_subscriptions: Int? = null,
    val max_filters: Int? = null,
    val max_limit: Int? = null,
    val max_subid_length: Int? = null,
    val min_prefix: Int? = null,
    val max_event_tags: Int? = null,
    val max_content_length: Int? = null,
    val auth_required: Boolean? = false,
    val payment_required: Boolean? = false
)

object RelayLimitationSerializer : KSerializer<RelayLimitation> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RelayLimitation")

    override fun serialize(encoder: Encoder, value: RelayLimitation) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("max_message_length", value.max_message_length)
            put("max_subscriptions", value.max_subscriptions)
            put("max_filters", value.max_filters)
            put("max_limit", value.max_limit)
            put("max_subid_length", value.max_subid_length)
            put("min_prefix", value.min_prefix)
            put("max_event_tags", value.max_event_tags)
            put("max_content_length", value.max_content_length)
            put("auth_required", value.auth_required)
            put("payment_required", value.payment_required)
        })
    }

    override fun deserialize(decoder: Decoder): RelayLimitation {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return RelayLimitation(
            max_message_length = json["max_message_length"]?.jsonPrimitive?.intOrNull,
            max_subscriptions = json["max_subscriptions"]?.jsonPrimitive?.intOrNull,
            max_filters = json["max_filters"]?.jsonPrimitive?.intOrNull,
            max_limit = json["max_limit"]?.jsonPrimitive?.intOrNull,
            max_subid_length = json["max_subid_length"]?.jsonPrimitive?.intOrNull,
            min_prefix = json["min_prefix"]?.jsonPrimitive?.intOrNull,
            max_event_tags = json["max_event_tags"]?.jsonPrimitive?.intOrNull,
            max_content_length = json["max_content_length"]?.jsonPrimitive?.intOrNull,
            auth_required = json["auth_required"]?.jsonPrimitive?.booleanOrNull ?: false,
            payment_required = json["payment_required"]?.jsonPrimitive?.booleanOrNull ?: false
        )
    }
}

//@Serializable(with = RelayFeesSerializer::class)
data class RelayFees(
    val admission: List<RelayFee>? = null,
    val subscription: List<RelayFee>? = null,
    val publication: List<RelayFee>? = null
)

object RelayFeesSerializer : KSerializer<RelayFees> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RelayFees")

    override fun serialize(encoder: Encoder, value: RelayFees) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            value.admission?.let { list -> put("admission", buildJsonArray { list.forEach { add(output.json.encodeToJsonElement(RelayFeeSerializer, it)) } }) }
            value.subscription?.let { list -> put("subscription", buildJsonArray { list.forEach { add(output.json.encodeToJsonElement(RelayFeeSerializer, it)) } }) }
            value.publication?.let { list -> put("publication", buildJsonArray { list.forEach { add(output.json.encodeToJsonElement(RelayFeeSerializer, it)) } }) }
        })
    }

    override fun deserialize(decoder: Decoder): RelayFees {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return RelayFees(
            admission = json["admission"]?.jsonArray?.map { input.json.decodeFromJsonElement(RelayFeeSerializer, it) },
            subscription = json["subscription"]?.jsonArray?.map { input.json.decodeFromJsonElement(RelayFeeSerializer, it) },
            publication = json["publication"]?.jsonArray?.map { input.json.decodeFromJsonElement(RelayFeeSerializer, it) }
        )
    }
}

//@Serializable(with = RelayFeeSerializer::class)
data class RelayFee(
    val amount: Long,
    val unit: String
)

object RelayFeeSerializer : KSerializer<RelayFee> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RelayFee")

    override fun serialize(encoder: Encoder, value: RelayFee) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("amount", value.amount)
            put("unit", value.unit)
        })
    }

    override fun deserialize(decoder: Decoder): RelayFee {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return RelayFee(
            amount = json["amount"]?.jsonPrimitive?.long ?: 0L,
            unit = json["unit"]?.jsonPrimitive?.content ?: ""
        )
    }
}

/**
 * NIP-65: Relay List Metadata
 */
//@Serializable(with = UserRelayListSerializer::class)
data class UserRelayList(
    val pubkey: String,
    val relays: List<RelayUsage>
)

object UserRelayListSerializer : KSerializer<UserRelayList> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("UserRelayList")

    override fun serialize(encoder: Encoder, value: UserRelayList) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("pubkey", value.pubkey)
            put("relays", buildJsonArray { value.relays.forEach { add(output.json.encodeToJsonElement(RelayMetadataSerializer, it)) } })
        })
    }

    override fun deserialize(decoder: Decoder): UserRelayList {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return UserRelayList(
            pubkey = json["pubkey"]?.jsonPrimitive?.content ?: "",
            relays = json["relays"]?.jsonArray?.map { input.json.decodeFromJsonElement(RelayMetadataSerializer, it) } ?: emptyList()
        )
    }
}

//@Serializable(with = RelayMetadataSerializer::class)
data class RelayMetadata(
    val url: String,
    val read: Boolean = true,
    val write: Boolean = true
)

object RelayMetadataSerializer : KSerializer<RelayMetadata> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RelayMetadata")

    override fun serialize(encoder: Encoder, value: RelayMetadata) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("url", value.url)
            put("read", value.read)
            put("write", value.write)
        })
    }

    override fun deserialize(decoder: Decoder): RelayMetadata {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return RelayMetadata(
            url = json["url"]?.jsonPrimitive?.content ?: "",
            read = json["read"]?.jsonPrimitive?.boolean ?: true,
            write = json["write"]?.jsonPrimitive?.boolean ?: true
        )
    }
}

/**
 * Relay Quality and Scoring
 */
//@Serializable(with = RelayMetricSerializer::class)
data class RelayMetric(
    val url: String,
    val rtt: Long = -1,
    val lastSeen: Long = 0,
    val isOnline: Boolean = false,
    val isPaid: Boolean = false,
    val info: RelayInfo? = null,
    val score: Int = 0
)

object RelayMetricSerializer : KSerializer<RelayMetric> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RelayMetric")

    override fun serialize(encoder: Encoder, value: RelayMetric) {
        val output = encoder as? JsonEncoder ?: throw Exception("JSON expected")
        output.encodeJsonElement(buildJsonObject {
            put("url", value.url)
            put("rtt", value.rtt)
            put("lastSeen", value.lastSeen)
            put("isOnline", value.isOnline)
            put("isPaid", value.isPaid)
            value.info?.let { put("info", output.json.encodeToJsonElement(RelayInfoSerializer, it)) }
            put("score", value.score)
        })
    }

    override fun deserialize(decoder: Decoder): RelayMetric {
        val input = decoder as? JsonDecoder ?: throw Exception("JSON expected")
        val json = input.decodeJsonElement().jsonObject
        return RelayMetric(
            url = json["url"]?.jsonPrimitive?.content ?: "",
            rtt = json["rtt"]?.jsonPrimitive?.long ?: -1L,
            lastSeen = json["lastSeen"]?.jsonPrimitive?.long ?: 0L,
            isOnline = json["isOnline"]?.jsonPrimitive?.boolean ?: false,
            isPaid = json["isPaid"]?.jsonPrimitive?.boolean ?: false,
            info = json["info"]?.let { input.json.decodeFromJsonElement(RelayInfoSerializer, it) },
            score = json["score"]?.jsonPrimitive?.int ?: 0
        )
    }
}

object NostrConstants {
    const val ADMIN_PUBKEY = "79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798"
    const val ADMIN_PRIVKEY = "0000000000000000000000000000000000000000000000000000000000000001"
}

typealias RelayUsage = RelayMetadata
