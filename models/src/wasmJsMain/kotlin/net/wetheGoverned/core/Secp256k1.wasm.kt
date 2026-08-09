package net.wetheGoverned.core

@JsName("BigInt")
private external fun jsBigInt(s: String): JsAny

actual class CivicBigInt(val value: JsAny) {
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

private fun jsAdd(a: JsAny, b: JsAny): JsAny = js("a + b")
private fun jsSubtract(a: JsAny, b: JsAny): JsAny = js("a - b")
private fun jsMultiply(a: JsAny, b: JsAny): JsAny = js("a * b")
private fun jsMod(a: JsAny, b: JsAny): JsAny = js("(a % b + b) % b")

// Standard Extended Euclidean Algorithm for modular inverse
private fun jsModInverse(a: JsAny, m: JsAny): JsAny = js("{\n    let a_val = (a % m + m) % m;\n    let m_val = m;\n    let m0 = m_val;\n    let y = 0n, x = 1n;\n    if (m_val === 1n) return 0n;\n    while (a_val > 1n) {\n        if (m_val === 0n) throw new Error('Division by zero');\n        let q = a_val / m_val;\n        let t = m_val;\n        m_val = a_val % m_val;\n        a_val = t;\n        t = y;\n        y = x - q * y;\n        x = t;\n    }\n    if (x < 0n) x += m0;\n    return x;\n}")

// Standard modular exponentiation
private fun jsModPow(base: JsAny, exp: JsAny, m: JsAny): JsAny = js("{\n    if (m === 1n) return 0n;\n    let res = 1n;\n    let b = base % m;\n    let e = exp;\n    while (e > 0n) {\n        if (e % 2n === 1n) res = (res * b) % m;\n        e = e / 2n;\n        b = (b * b) % m;\n    }\n    return res;\n}")

private fun jsToHex(a: JsAny): String = js("a.toString(16)")
private fun jsIsEven(a: JsAny): Boolean = js("a % 2n === 0n")
private fun jsCompare(a: JsAny, b: JsAny): Int = js("a < b ? -1 : (a > b ? 1 : 0)")

actual fun CivicBigInt.divideByTwo(): CivicBigInt = CivicBigInt(jsDivideByTwo(this.value))
private fun jsDivideByTwo(a: JsAny): JsAny = js("a / 2n")
