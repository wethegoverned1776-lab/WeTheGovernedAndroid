package net.wetheGoverned.core

import java.security.MessageDigest
import java.util.UUID

actual fun randomUUID(): String = UUID.randomUUID().toString()

actual fun sha256(input: String): String = sha256Native(input.encodeToByteArray()).toHex()
