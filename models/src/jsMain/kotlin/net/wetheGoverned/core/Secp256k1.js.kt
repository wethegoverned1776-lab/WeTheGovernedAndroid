package net.wetheGoverned.core

@JsName("BigInt")
private external fun jsBigInt(s: String): dynamic

actual class CivicBigInt(val value: dynamic) {
    actual companion object {
        actual val ZERO = fromLong(0)
        actual val ONE = fromLong(1)
        actual val TWO = fromLong(2)
        actual val THREE = fromLong(3)
        actual val SECP256K1_N = fromHex("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141")
        actual val SECP256K1_P = fromHex("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F")
        actual fun fromHex(hex: String) = CivicBigInt(jsBigInt("0x$hex"))
        actual fun fromLong(long: Long) = CivicBigInt(jsBigInt(long.toString()))
        actual fun fromByteArray(bytes: ByteArray): CivicBigInt {
            val hex = bytes.joinToString("") { (it.toInt() and 0xFF).toString(16).padStart(2, '0') }
            return fromHex(hex)
        }
    }
    actual fun add(other: CivicBigInt) = CivicBigInt(jsAdd(value, other.value))
    actual fun subtract(other: CivicBigInt) = CivicBigInt(jsSubtract(value, other.value))
    actual fun multiply(other: CivicBigInt) = CivicBigInt(jsMultiply(value, other.value))
    actual fun mod(m: CivicBigInt) = CivicBigInt(jsMod(value, m.value))
    actual fun modInverse(m: CivicBigInt) = CivicBigInt(jsModInverse(value, m.value))
    actual fun modPow(exponent: CivicBigInt, m: CivicBigInt) = CivicBigInt(jsModPow(value, exponent.value, m.value))
    actual fun toHex() = jsToHex(value).padStart(64, '0')
    actual fun toByteArray(length: Int): ByteArray {
        val hex = jsToHex(value)
        val normalizedHex = hex.padStart(length * 2, '0')
        val res = ByteArray(length)
        // If hex is longer than requested length, take the last length*2 characters
        val start = if (normalizedHex.length > length * 2) normalizedHex.length - length * 2 else 0
        val actualHex = normalizedHex.substring(start)
        
        for (i in 0 until length) {
            val s = actualHex.substring(i * 2, i * 2 + 2)
            res[i] = s.toInt(16).toByte()
        }
        return res
    }
    actual fun isEven() = jsIsEven(value)
    actual fun compareTo(other: CivicBigInt) = jsCompare(value, other.value)
    actual override fun equals(other: Any?): Boolean {
        if (other !is CivicBigInt) return false
        return jsCompare(value, other.value) == 0
    }
    override fun hashCode() = toHex().hashCode()
}

private fun jsAdd(a: dynamic, b: dynamic): dynamic = js("a + b")
private fun jsSubtract(a: dynamic, b: dynamic): dynamic = js("a - b")
private fun jsMultiply(a: dynamic, b: dynamic): dynamic = js("a * b")
private fun jsMod(a: dynamic, b: dynamic): dynamic = js("(a % b + b) % b")

// Standard Extended Euclidean Algorithm for modular inverse
private fun jsModInverse(a: dynamic, m: dynamic): dynamic = js("{\n    var a_val = (a % m + m) % m;\n    var m_val = m;\n    var m0 = m_val;\n    var y = BigInt(0);\n    var x = BigInt(1);\n    if (m_val === BigInt(1)) return BigInt(0);\n    while (a_val > BigInt(1)) {\n        if (m_val === BigInt(0)) throw new Error('Division by zero');\n        var q = a_val / m_val;\n        var t = m_val;\n        m_val = a_val % m_val;\n        a_val = t;\n        t = y;\n        y = x - q * y;\n        x = t;\n    }\n    if (x < BigInt(0)) x += m0;\n    return x;\n}")

// Standard modular exponentiation
private fun jsModPow(base: dynamic, exp: dynamic, m: dynamic): dynamic = js("{\n    if (m === BigInt(1)) return BigInt(0);\n    var res = BigInt(1);\n    var b = base % m;\n    var e = exp;\n    while (e > BigInt(0)) {\n        if (e % BigInt(2) === BigInt(1)) res = (res * b) % m;\n        e = e / BigInt(2);\n        b = (b * b) % m;\n    }\n    return res;\n}")

private fun jsToHex(a: dynamic): String = js("a.toString(16)")
private fun jsIsEven(a: dynamic): Boolean = js("a % BigInt(2) === BigInt(0)")
private fun jsCompare(a: dynamic, b: dynamic): Int = js("a < b ? -1 : (a > b ? 1 : 0)")

actual fun CivicBigInt.divideByTwo(): CivicBigInt = CivicBigInt(jsDivideByTwo(this.value))
private fun jsDivideByTwo(a: dynamic): dynamic = js("a / BigInt(2)")
