package net.wetheGoverned.core

/**
 * A pure Kotlin implementation of SHA-256.
 */
fun sha256Pure(input: ByteArray): ByteArray {
    val h = intArrayOf(
        0x6a09e667, -0x4498517b, 0x3c6ef372, -0x5ab00ac5,
        0x510e527f, -0x64fa9773, 0x1f83d9ab, 0x5be0cd19
    )

    val k = intArrayOf(
        0x428a2f98, 0x71374491, -0x4a3f049, -0x164a245b, 0x3956c25b, 0x59f111f1, -0x6dc07d5c, -0x54e3a12b,
        -0x27f85568, 0x122aa88e, 0x34b8c450, 0x43815349, 0x4c66220a, 0x5be0cd19, 0x6c44198c, 0x7bc65dee,
        -0x7b578127, -0x716f1b0b, -0x664684a3, -0x5b38dcdb, -0x518a00bc, -0x427bed93, -0x283d44d6, -0x1df1ad61,
        -0x14f04c0b, -0x6fa5a44, 0x4ea6c11, 0x8686a7e, 0x0d175228, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa,
        0x5cb0a9dc, 0x76f988da, -0x67c1aeae, -0x57ce3993, -0x4ffcd834, -0x40a68039, -0x391ff40d, -0x2a586d09,
        0x06ca6351, 0x14292967, 0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb,
        -0x79f029e9, -0x704f0ff3, -0x64fa9773, -0x5a005c24, -0x4ef2ac4e, -0x406c803a, -0x384f5f52, -0x2c054ed3,
        -0x23307524, 0x243185be, 0x550c7dc3, 0x72be5d74, -0x7f216a6d, -0x71c6fe61, -0x59c36f0d, -0x454d840f
    )

    val messageLen = input.size
    val bitLen = messageLen.toLong() * 8
    val padLen = if (messageLen % 64 < 56) 56 - messageLen % 64 else 120 - messageLen % 64
    val totalLen = messageLen + padLen + 8
    val padded = ByteArray(totalLen)
    input.copyInto(padded)
    padded[messageLen] = 0x80.toByte()
    for (i in 0..7) padded[totalLen - 1 - i] = (bitLen ushr (i * 8)).toByte()

    val w = IntArray(64)
    for (v in 0 until totalLen step 64) {
        for (i in 0..15) {
            w[i] = (padded[v + i * 4].toInt() and 0xff shl 24) or
                    (padded[v + i * 4 + 1].toInt() and 0xff shl 16) or
                    (padded[v + i * 4 + 2].toInt() and 0xff shl 8) or
                    (padded[v + i * 4 + 3].toInt() and 0xff)
        }
        for (i in 16..63) {
            val s0 = (w[i - 15] ushr 7 or (w[i - 15] shl 25)) xor
                    (w[i - 15] ushr 18 or (w[i - 15] shl 14)) xor
                    (w[i - 15] ushr 3)
            val s1 = (w[i - 2] ushr 17 or (w[i - 2] shl 15)) xor
                    (w[i - 2] ushr 19 or (w[i - 2] shl 13)) xor
                    (w[i - 2] ushr 10)
            w[i] = w[i - 16] + s0 + w[i - 7] + s1
        }

        var a = h[0]
        var b = h[1]
        var c = h[2]
        var d = h[3]
        var e = h[4]
        var f = h[5]
        var g = h[6]
        var l = h[7]

        for (i in 0..63) {
            val s1 = (e ushr 6 or (e shl 26)) xor
                    (e ushr 11 or (e shl 21)) xor
                    (e ushr 25 or (e shl 7))
            val ch = (e and f) xor (e.inv() and g)
            val temp1 = l + s1 + ch + k[i] + w[i]
            val s0 = (a ushr 2 or (a shl 30)) xor
                    (a ushr 13 or (a shl 19)) xor
                    (a ushr 22 or (a shl 10))
            val maj = (a and b) xor (a and c) xor (b and c)
            val temp2 = s0 + maj

            l = g
            g = f
            f = e
            e = d + temp1
            d = c
            c = b
            b = a
            a = temp1 + temp2
        }

        h[0] += a
        h[1] += b
        h[2] += c
        h[3] += d
        h[4] += e
        h[5] += f
        h[6] += g
        h[7] += l
    }

    val result = ByteArray(32)
    for (i in 0..7) {
        result[i * 4] = (h[i] ushr 24).toByte()
        result[i * 4 + 1] = (h[i] ushr 16).toByte()
        result[i * 4 + 2] = (h[i] ushr 8).toByte()
        result[i * 4 + 3] = h[i].toByte()
    }
    return result
}
