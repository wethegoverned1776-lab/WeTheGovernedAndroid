package net.wetheGoverned.core

import kotlin.js.Promise

/**
 * External wrappers for nostr-tools and nostr-wasm
 */

@JsName("NostrTools")
external object NostrTools {
    @JsName("getPublicKey")
    fun getPublicKey(privateKeyHex: String): String
    
    @JsName("generateSecretKey")
    fun generateSecretKey(): JsAny
    
    @JsName("nip19")
    val nip19: Nip19
}

external interface Nip19 {
    fun nsecEncode(hex: String): String
    fun decode(nsec: String): JsAny
}

@JsName("nostrWasm")
external object NostrWasmInstance {
    @JsName("sign")
    fun sign(eventIdHex: String, privateKeyHex: String): String
    
    @JsName("verify")
    fun verify(eventIdHex: String, signatureHex: String, publicKeyHex: String): Boolean
}

// Browser SubtleCrypto for fast SHA-256
external object crypto {
    val subtle: SubtleCrypto
}

external interface SubtleCrypto {
    fun digest(algorithm: String, data: JsAny): Promise<JsAny>
}
