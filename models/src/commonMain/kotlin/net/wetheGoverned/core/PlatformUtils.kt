package net.wetheGoverned.core

expect fun randomUUID(): String

expect fun sha256(input: String): String

expect fun sha256Native(bytes: ByteArray): ByteArray

expect fun platformSign(eventIdHex: String, privateKeyHex: String): String

expect fun platformDerivePubKey(privateKeyHex: String): String
