package net.wetheGoverned.core

actual fun sha256Native(bytes: ByteArray): ByteArray {
    return sha256Pure(bytes)
}
