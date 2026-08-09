package net.wetheGoverned.core

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()

actual fun sha256(input: String): String = sha256Native(input.encodeToByteArray()).toHex()

actual fun platformSign(eventIdHex: String, privateKeyHex: String): String = 
    Secp256k1KeyManager.sign(eventIdHex, privateKeyHex)

actual fun platformDerivePubKey(privateKeyHex: String): String = 
    Secp256k1KeyManager.deriveXOnlyPubKey(privateKeyHex)
