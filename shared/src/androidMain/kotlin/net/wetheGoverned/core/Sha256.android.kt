package net.wetheGoverned.core

import java.security.MessageDigest

actual fun sha256Native(bytes: ByteArray): ByteArray {
    return MessageDigest.getInstance("SHA-256").digest(bytes)
}
