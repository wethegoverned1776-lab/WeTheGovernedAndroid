package net.wetheGoverned.zk

data class ZkProofResult(
    val proof: List<String>,
    val publicSignals: List<String>
)

interface ZkProver {
    suspend fun generateProof(circuitName: String, inputs: Map<String, Any>): ZkProofResult
    suspend fun verifyProof(proof: ZkProofResult, circuitName: String): Boolean
}

class NoOpZkProver : ZkProver {
    override suspend fun generateProof(circuitName: String, inputs: Map<String, Any>): ZkProofResult {
        return ZkProofResult(emptyList<String>(), emptyList<String>())
    }
    override suspend fun verifyProof(proof: ZkProofResult, circuitName: String): Boolean = true
}
