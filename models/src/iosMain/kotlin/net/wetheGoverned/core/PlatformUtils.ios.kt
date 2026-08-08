package net.wetheGoverned.core

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()

actual fun sha256(input: String): String = sha256Native(input.encodeToByteArray()).toHex()
