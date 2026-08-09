package net.wetheGoverned.core

// Stub for iOS. Real implementation would use BigInt on Native.
actual class CivicBigInt(val value: Long) {
    actual companion object {
        actual val ZERO = fromLong(0)
        actual val ONE = fromLong(1)
        actual val TWO = fromLong(2)
        actual val THREE = fromLong(3)
        actual val SECP256K1_N = fromLong(12345)
        actual val SECP256K1_P = fromLong(12345)
        actual fun fromHex(hex: String) = fromLong(hex.hashCode().toLong())
        actual fun fromLong(long: Long) = CivicBigInt(long)
        actual fun fromByteArray(bytes: ByteArray) = fromLong(bytes.size.toLong())
    }
    actual fun add(other: CivicBigInt) = fromLong(value + other.value)
    actual fun subtract(other: CivicBigInt) = fromLong(value - other.value)
    actual fun multiply(other: CivicBigInt) = fromLong(value * other.value)
    actual fun mod(m: CivicBigInt) = fromLong(value % m.value)
    actual fun modInverse(m: CivicBigInt) = fromLong(value)
    actual fun modPow(exponent: CivicBigInt, m: CivicBigInt) = fromLong(value)
    actual fun toHex() = value.toString(16).padStart(64, '0')
    actual fun toByteArray(length: Int) = ByteArray(length)
    actual fun isEven() = value % 2 == 0L
    actual fun compareTo(other: CivicBigInt) = value.compareTo(other.value)
    actual override fun equals(other: Any?) = (other as? CivicBigInt)?.value == value
}

actual fun CivicBigInt.divideByTwo() = CivicBigInt(this.value / 2)
