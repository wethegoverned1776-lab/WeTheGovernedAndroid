package net.wetheGoverned.core

/**
 * Platform-independent SHA-256 interface.
 */
expect fun sha256Native(bytes: ByteArray): ByteArray

fun computeSha256(input: String): String {
    return sha256Native(input.encodeToByteArray()).toHex()
}

fun computeSha256(input: ByteArray): ByteArray {
    return sha256Native(input)
}

fun taggedHash(tag: String, msg: ByteArray): ByteArray {
    val tagHash = sha256Native(tag.encodeToByteArray())
    val combined = ByteArray(64 + msg.size)
    tagHash.copyInto(combined, 0)
    tagHash.copyInto(combined, 32)
    msg.copyInto(combined, 64)
    return sha256Native(combined)
}

@OptIn(ExperimentalUnsignedTypes::class)
private val H_INITIAL = uintArrayOf(
    0x6a09e667u, 0xbb67ae85u, 0x3c6ef372u, 0xa54ff53au,
    0x510e527fu, 0x9b05688cu, 0x1f83d9abu, 0x5be0cd19u
)

@OptIn(ExperimentalUnsignedTypes::class)
private val K_CONSTANTS = uintArrayOf(
    0x428a2f98u, 0x71374491u, 0xb5c0fbcfu, 0xe9b5dba5u, 0x3956c25bu, 0x59f111f1u, 0x923f82a4u, 0xab1c5ed5u,
    0xd807aa98u, 0x12835b01u, 0x243185beu, 0x550c7dc3u, 0x72be5d74u, 0x80deb1feu, 0x9bdc06a7u, 0xc19bf174u,
    0xe49b69c1u, 0xefbe4786u, 0x0fc19dc6u, 0x240ca1ccu, 0x2de92c6fu, 0x4a7484aau, 0x5cb0a9dcu, 0x76f988dau,
    0x983e5152u, 0xa831c66du, 0xb00327c8u, 0xbf597fc7u, 0xc6e00bf3u, 0xd5a79147u, 0x06ca6351u, 0x14292967u,
    0x27b70a85u, 0x2e1b2138u, 0x4d2c6dfcu, 0x53380d13u, 0x650a7354u, 0x766a0abbu, 0x81c2c92eu, 0x92722c85u,
    0xa2bfe8a1u, 0xa81a664bu, 0xc24b8b70u, 0xc76c51a3u, 0xd192e819u, 0xd6990624u, 0xf40e3585u, 0x106aa070u,
    0x19a4c116u, 0x1e376c08u, 0x2748774cu, 0x34b0bcb5u, 0x391c0cb3u, 0x4ed8aa4au, 0x5b9cca4fu, 0x682e6ff3u,
    0x748f82eeu, 0x78a5636fu, 0x84c87814u, 0x8cc70208u, 0x90befffau, 0xa4506cebu, 0xbef9a3f7u, 0xc67178f2u
)

@OptIn(ExperimentalUnsignedTypes::class)
fun sha256Pure(msg: ByteArray): ByteArray {
    val h = H_INITIAL.copyOf()
    val k = K_CONSTANTS
    val msgBits = msg.size.toLong() * 8
    val paddingLen = if (msg.size % 64 < 56) 64 - (msg.size % 64) else 128 - (msg.size % 64)
    val padded = ByteArray(msg.size + paddingLen)
    msg.copyInto(padded)
    padded[msg.size] = 0x80.toByte()
    for (i in 0 until 8) padded[padded.size - 8 + i] = (msgBits shr (56 - i * 8)).toByte()
    val w = UIntArray(64)
    for (chunkOffset in 0 until padded.size step 64) {
        for (j in 0 until 16) {
            val o = chunkOffset + j * 4
            w[j] = ((padded[o].toUInt() and 0xffu) shl 24) or
                   ((padded[o + 1].toUInt() and 0xffu) shl 16) or
                   ((padded[o + 2].toUInt() and 0xffu) shl 8) or
                   (padded[o + 3].toUInt() and 0xffu)
        }
        for (j in 16 until 64) {
            val s0 = (w[j - 15] rotateRight 7) xor (w[j - 15] rotateRight 18) xor (w[j - 15] shr 3)
            val s1 = (w[j - 2] rotateRight 17) xor (w[j - 2] rotateRight 19) xor (w[j - 2] shr 10)
            w[j] = s1 + w[j - 7] + s0 + w[j - 16]
        }
        var a = h[0]; var b = h[1]; var c = h[2]; var d = h[3]
        var e = h[4]; var f = h[5]; var g = h[6]; var h_var = h[7]
        for (j in 0 until 64) {
            val S1 = (e rotateRight 6) xor (e rotateRight 11) xor (e rotateRight 25)
            val ch = (e and f) xor (e.inv() and g)
            val t1 = h_var + S1 + ch + k[j] + w[j]
            val S0 = (a rotateRight 2) xor (a rotateRight 13) xor (a rotateRight 22)
            val maj = (a and b) xor (a and c) xor (b and c)
            val t2 = S0 + maj
            h_var = g; g = f; f = e; e = d + t1; d = c; c = b; b = a; a = t1 + t2
        }
        h[0] += a; h[1] += b; h[2] += c; h[3] += d
        h[4] += e; h[5] += f; h[6] += g; h[7] += h_var
    }
    val res = ByteArray(32)
    for (i in 0 until 8) {
        res[i * 4] = (h[i] shr 24).toByte()
        res[i * 4 + 1] = (h[i] shr 16).toByte()
        res[i * 4 + 2] = (h[i] shr 8).toByte()
        res[i * 4 + 3] = h[i].toByte()
    }
    return res
}

@OptIn(ExperimentalUnsignedTypes::class)
private infix fun UInt.rotateRight(n: Int): UInt = (this shr n) or (this shl (32 - n))

fun ByteArray.toHex(): String = joinToString("") { (it.toInt() and 0xff).toString(16).padStart(2, '0') }

fun String.hexToBytes(): ByteArray {
    val res = ByteArray(length / 2)
    for (i in 0 until length step 2) {
        res[i / 2] = substring(i, i + 2).toInt(16).toByte()
    }
    return res
}
