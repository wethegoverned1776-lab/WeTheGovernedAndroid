package net.wetheGoverned.core

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

import javax.inject.Inject
import javax.inject.Singleton

interface DispatcherProvider {
    fun io(): CoroutineContext
    fun main(): CoroutineContext
    fun default(): CoroutineContext
}

@Singleton
class DefaultDispatcherProvider @Inject constructor() : DispatcherProvider {
    override fun io() = Dispatchers.Default 
    override fun main() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}


/**
 * BIP-340 Schnorr Signature & Secp256k1 Utility.
 */
object Secp256k1KeyManager {
    data class KeyPair(val pubKeyHex: String, val privateKeyHex: String)

    fun generateKeyPair(): KeyPair {
        val priv = (0..Int.MAX_VALUE).random().toString()
        val privHex = computeSha256(priv).take(64)
        return KeyPair(deriveXOnlyPubKey(privHex), privHex)
    }

    /**
     * BIP-340 X-Only Public Key Derivation.
     */
    fun deriveXOnlyPubKey(privKeyHex: String): String {
        val d0 = CivicBigInt.fromHex(privKeyHex)
        val P = Secp256k1.multiply(Secp256k1.G, d0)
        return P.x.toHex().padStart(64, '0')
    }

    /**
     * BIP-340 Schnorr Signer. 
     */
    fun sign(eventIdHex: String, privateKeyHex: String): String {
        return NostrSigner.sign(eventIdHex, privateKeyHex)
    }

    /**
     * Validates if a string is a 32-byte (64-char) hex ID/PubKey as required by Nostr.
     */
    fun isValidNostrHex(input: String): Boolean {
        return input.length == 64 && input.all { it in '0'..'9' || it in 'a'..'f' || it in 'A'..'F' }
    }
}

/**
 * NIP-01 Compliant JSON Serialization for Event ID computation.
 */
fun buildNip01EventId(pubkey: String, createdAt: Long, kind: Int, tags: List<List<String>>, content: String): String {
    val sb = StringBuilder()
    sb.append("[0,\"").append(pubkey).append("\",")
    sb.append(createdAt).append(",")
    sb.append(kind).append(",")
    
    // tags
    sb.append("[")
    tags.forEachIndexed { i, tag ->
        sb.append("[")
        tag.forEachIndexed { j, s ->
            sb.append("\"").append(escapeNostrJson(s)).append("\"")
            if (j < tag.size - 1) sb.append(",")
        }
        sb.append("]")
        if (i < tags.size - 1) sb.append(",")
    }
    sb.append("],")
    
    // content
    sb.append("\"").append(escapeNostrJson(content)).append("\"")
    sb.append("]")
    
    val serialized = sb.toString()
    println("DEBUG: Serialized NIP-01: $serialized")
    return computeSha256(serialized)
}

private fun escapeNostrJson(s: String): String {
    val sb = StringBuilder()
    for (c in s) {
        when (c) {
            '\"' -> sb.append("\\\"")
            '\\' -> sb.append("\\\\")
            '\b' -> sb.append("\\b")
            '\u000c' -> sb.append("\\f")
            '\n' -> sb.append("\\n")
            '\r' -> sb.append("\\r")
            '\t' -> sb.append("\\t")
            else -> {
                if (c.code < 32) {
                    sb.append("\\u" + c.code.toString(16).padStart(4, '0'))
                } else {
                    sb.append(c)
                }
            }
        }
    }
    return sb.toString()
}

object Bech32Codec {
    fun encodeNsec(privKeyHex: String): String = "nsec1$privKeyHex"
    fun decodeNsec(nsec: String): String = nsec.removePrefix("nsec1")
}

fun formatDate(timestamp: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${dateTime.dayOfMonth}, ${dateTime.year}"
}
