package net.wetheGoverned.core

import kotlin.test.Test
import kotlin.test.assertEquals

class NostrSigningTest {

    @Test
    fun test3GOnCurve() {
        val p3 = Secp256k1.multiply(Secp256k1.G, CivicBigInt.fromLong(3))
        val x = p3.x
        val y = p3.y
        val P = Secp256k1.P
        
        val left = y.multiply(y).mod(P)
        val right = x.multiply(x).multiply(x).add(CivicBigInt.fromLong(7)).mod(P)
        
        println("3G.x: ${x.toHex()}")
        println("3G.y: ${y.toHex()}")
        println("3G.y^2 mod P: ${left.toHex()}")
        println("3G.x^3+7 mod P: ${right.toHex()}")
        assertEquals(left.toHex(), right.toHex())
    }

    @Test
    fun testSha256PureABC() {
        val input = "abc".encodeToByteArray()
        val expected = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"
        val actual = sha256Pure(input).toHex()
        println("sha256Pure('abc'): $actual")
        assertEquals(expected, actual)
    }

    @Test
    fun testNip01Simple() {
        val pubkey = "79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798"
        val id = buildNip01EventId(pubkey, 1L, 1, emptyList(), "test")
        // String: [0,"79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798",1,1,[],"test"]
        val expected = "07f1319d4acac801f50c456f4780ee7b8c03f88b29b3c7109d9627fad8568445"
        println("Simple NIP-01 ID: $id")
        assertEquals(expected, id)
    }

    @Test
    fun testBip340Vector0() {
        val seckey = "0000000000000000000000000000000000000000000000000000000000000003"
        val msg = "0000000000000000000000000000000000000000000000000000000000000000"
        val auxRand = "0000000000000000000000000000000000000000000000000000000000000000"
        val expectedSig = "E907831F80848D1069A5371B402410364BDF1C5F8307B0084C55F1CE2DCA821525F66A4A85EA8B71E482A74F382D2CE5EBEEE8FDB2172F477DF4900D310536C0"

        val sig = Secp256k1KeyManager.sign(msg, seckey, auxRand)
        println("Generated Sig: ${sig.lowercase()}")
        println("Expected Sig:  ${expectedSig.lowercase()}")
        assertEquals(expectedSig.lowercase(), sig.lowercase())
    }

    @Test
    fun testNip01EventIdComputation() {
        val pubkey = "79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798"
        val createdAt = 1722294567L
        val kind = 1
        val tags = listOf(listOf("t", "nostr"))
        val content = "Hello Nostr world!"
        
        val expectedId = "bc09c505b5d86cd502afc2ffd4f3db0811142f64aae9974bf26e8bd33f9b3dad"
        val id = buildNip01EventId(pubkey, createdAt, kind, tags, content)
        
        println("Generated ID: $id")
        println("Expected ID: $expectedId")
        assertEquals(expectedId, id)
    }
}
