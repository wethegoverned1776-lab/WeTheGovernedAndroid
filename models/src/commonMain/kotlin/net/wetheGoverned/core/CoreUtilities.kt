package net.wetheGoverned.core

import kotlinx.serialization.json.*
import net.wetheGoverned.model.CivicEvent

object CivicJson {
    val instance = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }
}

fun ByteArray.toHex(): String = joinToString("") { it.toUByte().toString(16).padStart(2, '0') }

fun String.hexToBytes(): ByteArray {
    check(length % 2 == 0) { "Must have an even length" }
    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}

fun taggedHash(tag: String, msg: ByteArray): ByteArray {
    val tagHash = sha256Native(tag.encodeToByteArray())
    return sha256Native(tagHash + tagHash + msg)
}

fun buildNip01EventId(
    pubkey: String,
    createdAt: Long,
    kind: Int,
    tags: List<List<String>>,
    content: String
): String {
    val jsonArray = buildJsonArray {
        add(0)
        add(pubkey.lowercase())
        add(createdAt)
        add(kind)
        add(buildJsonArray {
            tags.forEach { tag ->
                add(buildJsonArray {
                    tag.forEach { add(it) }
                })
            }
        })
        add(content)
    }
    return sha256(jsonArray.toString())
}

fun formatDate(timestamp: Long): String {
    val instant = kotlinx.datetime.Instant.fromEpochSeconds(timestamp)
    return instant.toString() // Placeholder for actual formatting
}
