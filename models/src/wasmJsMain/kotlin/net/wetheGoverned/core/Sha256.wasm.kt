package net.wetheGoverned.core

/**
 * Optimized SHA-256 for Wasm using browser/JS crypto if available
 */
actual fun sha256Native(bytes: ByteArray): ByteArray {
    // Currently using the pure-Kotlin implementation for best compatibility across Wasm/JS/Room
    // but wrapping it here to allow for future JS/WebCrypto optimization
    return sha256Pure(bytes)
}
