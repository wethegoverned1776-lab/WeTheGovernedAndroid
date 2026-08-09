package net.wetheGoverned.core

import java.security.MessageDigest
import java.util.UUID

actual fun randomUUID(): String = UUID.randomUUID().toString()

actual fun sha256(input: String): String = sha256Native(input.encodeToByteArray()).toHex()

actual fun platformSign(eventIdHex: String, privateKeyHex: String): String = 
    Secp256k1KeyManager.sign(eventIdHex, privateKeyHex)

actual fun platformDerivePubKey(privateKeyHex: String): String = 
    Secp256k1KeyManager.deriveXOnlyPubKey(privateKeyHex)
