package net.wetheGoverned.core

import java.math.BigInteger

actual class CivicBigInt(val value: BigInteger) {
    actual companion object {
        actual val ZERO = CivicBigInt(BigInteger.ZERO)
        actual val ONE = CivicBigInt(BigInteger.ONE)
        actual val TWO = CivicBigInt(BigInteger.valueOf(2))
        actual val THREE = CivicBigInt(BigInteger.valueOf(3))
        actual val SECP256K1_N = CivicBigInt(BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16))
        actual val SECP256K1_P = CivicBigInt(BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16))
        actual fun fromHex(hex: String) = CivicBigInt(BigInteger(hex, 16))
        actual fun fromLong(long: Long) = CivicBigInt(BigInteger.valueOf(long))
        actual fun fromByteArray(bytes: ByteArray) = CivicBigInt(BigInteger(1, bytes))
    }
    actual fun add(other: CivicBigInt) = CivicBigInt(value.add(other.value))
    actual fun subtract(other: CivicBigInt) = CivicBigInt(value.subtract(other.value))
    actual fun multiply(other: CivicBigInt) = CivicBigInt(value.multiply(other.value))
    actual fun mod(m: CivicBigInt) = CivicBigInt(value.remainder(m.value).add(m.value).remainder(m.value))
    actual fun modInverse(m: CivicBigInt) = CivicBigInt(value.modInverse(m.value))
    actual fun modPow(exponent: CivicBigInt, m: CivicBigInt) = CivicBigInt(value.modPow(exponent.value, m.value))
    actual fun toHex() = value.toString(16).padStart(64, '0')
    actual fun toByteArray(length: Int): ByteArray {
        val bytes = value.toByteArray()
        if (bytes.size == length) return bytes
        if (bytes.size > length) return bytes.copyOfRange(bytes.size - length, bytes.size)
        val res = ByteArray(length)
        bytes.copyInto(res, length - bytes.size)
        return res
    }
    actual fun isEven() = !value.testBit(0)
    actual fun compareTo(other: CivicBigInt) = value.compareTo(other.value)
    actual override fun equals(other: Any?) = (other as? CivicBigInt)?.value == value
    override fun hashCode() = value.hashCode()
}

actual fun CivicBigInt.divideByTwo() = CivicBigInt(this.value.shiftRight(1))
