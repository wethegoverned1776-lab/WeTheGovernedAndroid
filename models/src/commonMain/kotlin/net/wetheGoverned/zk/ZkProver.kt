package net.wetheGoverned.zk

interface ZkProver {
    suspend fun generateProof(identitySecret: String, districtId: String): String
    suspend fun verifyProof(proof: String, publicKey: String, districtId: String): Boolean
}

class NoOpZkProver : ZkProver {
    override suspend fun generateProof(identitySecret: String, districtId: String): String = "noop_proof"
    override suspend fun verifyProof(proof: String, publicKey: String, districtId: String): Boolean = true
}
