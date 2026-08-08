package net.wetheGoverned.core

actual fun sha256Native(bytes: ByteArray): ByteArray = sha256Pure(bytes)
