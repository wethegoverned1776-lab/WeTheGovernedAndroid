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
        val hex = toHex()
        val res = ByteArray(length)
        val start = if (hex.length > length * 2) hex.length - length * 2 else 0
        for (i in 0 until (hex.length - start) / 2) {
            val s = hex.substring(start + i * 2, start + i * 2 + 2)
            res[length - ((hex.length - start) / 2) + i] = s.toInt(16).toByte()
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
private fun jsModInverse(a: JsAny, m: JsAny): JsAny = js("{\n    let m0 = m;\n    let y = 0n, x = 1n;\n    if (m === 1n) return 0n;\n    let aa = a;\n    let mm = m;\n    while (aa > 1n) {\n        let q = aa / mm;\n        let t = mm;\n        mm = aa % mm;\n        aa = t;\n        t = y;\n        y = x - q * y;\n        x = t;\n    }\n    if (x < 0n) x += m0;\n    return x;\n}")
private fun jsModPow(base: JsAny, exp: JsAny, m: JsAny): JsAny = js("{\n    if (m === 1n) return 0n;\n    let res = 1n;\n    let b = base % m;\n    let e = exp;\n    while (e > 0n) {\n        if (e % 2n === 1n) res = (res * b) % m;\n        e = e / 2n;\n        b = (b * b) % m;\n    }\n    return res;\n}")
private fun jsToHex(a: JsAny): String = js("a.toString(16)")
private fun jsIsEven(a: JsAny): Boolean = js("a % 2n === 0n")
private fun jsCompare(a: JsAny, b: JsAny): Int = js("a \u003c b ? -1 : (a \u003e b ? 1 : 0)")

actual fun CivicBigInt.divideByTwo(): CivicBigInt = CivicBigInt(jsDivideByTwo(this.value))
private fun jsDivideByTwo(a: JsAny): JsAny = js("a / 2n")
