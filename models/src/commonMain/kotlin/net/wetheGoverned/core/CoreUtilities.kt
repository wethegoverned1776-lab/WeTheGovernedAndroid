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
