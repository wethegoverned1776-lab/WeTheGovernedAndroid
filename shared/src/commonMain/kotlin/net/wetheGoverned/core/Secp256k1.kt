package net.wetheGoverned.core

/**
 * Common interface for platform-native BigInt math required for BIP-340 Schnorr.
 */
expect class CivicBigInt {
    companion object {
        val ZERO: CivicBigInt
        val ONE: CivicBigInt
        val TWO: CivicBigInt
        val THREE: CivicBigInt
        val SECP256K1_N: CivicBigInt
        val SECP256K1_P: CivicBigInt
        fun fromHex(hex: String): CivicBigInt
        fun fromLong(long: Long): CivicBigInt
        fun fromByteArray(bytes: ByteArray): CivicBigInt
    }
    fun add(other: CivicBigInt): CivicBigInt
    fun subtract(other: CivicBigInt): CivicBigInt
    fun multiply(other: CivicBigInt): CivicBigInt
    fun mod(m: CivicBigInt): CivicBigInt
    fun modInverse(m: CivicBigInt): CivicBigInt
    fun modPow(exponent: CivicBigInt, m: CivicBigInt): CivicBigInt
    fun toHex(): String
    fun toByteArray(length: Int = 32): ByteArray
    fun isEven(): Boolean
    fun compareTo(other: CivicBigInt): Int
    override fun equals(other: Any?): Boolean
}

data class ECPoint(val x: CivicBigInt, val y: CivicBigInt) {
    companion object {
        val INFINITY = ECPoint(CivicBigInt.ZERO, CivicBigInt.ZERO)
    }
    fun isInfinity() = this == INFINITY
}

object Secp256k1 {
    val P = CivicBigInt.SECP256K1_P
    val N = CivicBigInt.SECP256K1_N
    val G = ECPoint(
        CivicBigInt.fromHex("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798"),
        CivicBigInt.fromHex("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8")
    )

    fun add(p1: ECPoint, p2: ECPoint): ECPoint {
        if (p1.isInfinity()) return p2
        if (p2.isInfinity()) return p1
        if (p1.x == p2.x && p1.y != p2.y) return ECPoint.INFINITY

        val lam = if (p1 == p2) {
            val num = CivicBigInt.THREE.multiply(p1.x).multiply(p1.x).mod(P)
            val den = CivicBigInt.TWO.multiply(p1.y).modInverse(P)
            num.multiply(den).mod(P)
        } else {
            val num = p2.y.subtract(p1.y).mod(P)
            val den = p2.x.subtract(p1.x).modInverse(P)
            num.multiply(den).mod(P)
        }

        val x3 = lam.multiply(lam).subtract(p1.x).subtract(p2.x).mod(P)
        val y3 = lam.multiply(p1.x.subtract(x3)).subtract(p1.y).mod(P)
        return ECPoint(x3, y3)
    }

    fun multiply(p: ECPoint, k: CivicBigInt): ECPoint {
        var res = ECPoint.INFINITY
        var temp = p
        var scalar = k.mod(N)
        while (scalar.compareTo(CivicBigInt.ZERO) > 0) {
            if (!scalar.isEven()) {
                res = add(res, temp)
            }
            temp = add(temp, temp)
            scalar = scalar.divideByTwo() 
        }
        return res
    }
}

expect fun CivicBigInt.divideByTwo(): CivicBigInt

object NostrSigner {
    /**
     * Requirement: Real BIP-340 Schnorr Signatures.
     */
    fun sign(eventIdHex: String, privateKeyHex: String, auxRandHex: String? = null): String {
        val d0 = CivicBigInt.fromHex(privateKeyHex)
        if (d0 == CivicBigInt.ZERO || d0.compareTo(Secp256k1.N) >= 0) throw Exception("Invalid private key")

        // 1. P = d0 * G
        val P = Secp256k1.multiply(Secp256k1.G, d0)
        
        // 2. d = d0 if has_even_y(P) else n - d0
        val d = if (P.y.isEven()) d0 else Secp256k1.N.subtract(d0)
        val px = P.x.toByteArray(32)
        val msg = eventIdHex.hexToBytes()
        val auxRand = if (auxRandHex != null) auxRandHex.hexToBytes() else ByteArray(32)
        
        // 3. t = d ^ tagged_hash("BIP0340/aux", auxRand)
        val hAux = taggedHash("BIP0340/aux", auxRand)
        val dBytes = d.toByteArray(32)
        val t = ByteArray(32)
        for (i in 0 until 32) t[i] = (dBytes[i].toInt() xor hAux[i].toInt()).toByte()
        
        // 4. k = int(tagged_hash("BIP0340/nonce", t || P || m)) mod n
        val k0 = taggedHash("BIP0340/nonce", t + px + msg)
        val kInt = CivicBigInt.fromByteArray(k0).mod(Secp256k1.N)
        if (kInt == CivicBigInt.ZERO) throw Exception("k is zero")

        // 5. R = k * G
        val R = Secp256k1.multiply(Secp256k1.G, kInt)
        
        // 6. final_k = k if has_even_y(R) else n - k
        val finalK = if (R.y.isEven()) kInt else Secp256k1.N.subtract(kInt)
        
        // 7. e = int(tagged_hash("BIP0340/challenge", R.x || P.x || msg)) mod n
        val e0 = taggedHash("BIP0340/challenge", R.x.toByteArray(32) + px + msg)
        val e = CivicBigInt.fromByteArray(e0).mod(Secp256k1.N)

        // 8. s = (final_k + e*d) mod n
        val s = finalK.add(e.multiply(d)).mod(Secp256k1.N)
        
        return R.x.toHex().padStart(64, '0') + s.toHex().padStart(64, '0')
    }
}
