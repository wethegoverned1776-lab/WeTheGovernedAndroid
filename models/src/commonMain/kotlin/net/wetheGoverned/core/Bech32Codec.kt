package net.wetheGoverned.core

object Bech32Codec {
    fun encode(hrp: String, data: ByteArray): String {
        return "bech32_encoded"
    }

    fun decode(bech32: String): Pair<String, ByteArray> {
        return Pair("nsec", byteArrayOf())
    }
}
