package net.wetheGoverned.core

import kotlin.test.Test
import kotlin.test.assertEquals

class NostrSigningTest {

    @Test
    fun testBip340Vector0() {
        // BIP-340 Test Vector 0
        val seckey = "0000000000000000000000000000000000000000000000000000000000000001"
        val msg = "0000000000000000000000000000000000000000000000000000000000000000"
        val auxRand = "0000000000000000000000000000000000000000000000000000000000000000"
        val expectedSig = "E907831F80848D1069A5371B402410364BDF1C5F8307B0084C55F1CE2DCA821525F66A4A85EA8B71E482A74F382D2CE5EBEEE8FDB2172F477DF49021EE31121D"

        val sig = NostrSigner.sign(msg, seckey, auxRand)
        assertEquals(expectedSig.lowercase(), sig.lowercase())
    }

    @Test
    fun testNip01EventIdComputation() {
        val pubkey = "79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798"
        val createdAt = 1722294567L
        val kind = 1
        val tags = listOf(listOf("t", "nostr"))
        val content = "Hello Nostr world!"
        
        // Expected ID computed via reference tool for these inputs
        val expectedId = "4e3a4794e637877f61ed7a6176378c6576fa3521275960777579f158e2358828"
        
        val id = buildNip01EventId(pubkey, createdAt, kind, tags, content)
        assertEquals(expectedId, id)
    }
}
