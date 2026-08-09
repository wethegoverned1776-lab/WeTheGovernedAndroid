package net.wetheGoverned.core

/**
 * Platform-independent SHA-256 interface.
 */
actual fun sha256Native(bytes: ByteArray): ByteArray {
    return sha256Pure(bytes)
}
