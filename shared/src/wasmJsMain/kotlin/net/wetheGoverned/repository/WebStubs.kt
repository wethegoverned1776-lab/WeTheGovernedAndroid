package net.wetheGoverned.repository

import kotlinx.coroutines.flow.*
import net.wetheGoverned.model.*
import io.ktor.client.*
import net.wetheGoverned.remote.api.*

class WebWtgBackendApi(private val httpClient: HttpClient) : WtgBackendApi(baseUrl = "https://sim.wetheGoverned.net", httpClient = httpClient) {
    override suspend fun sendVerificationEmail(pubKey: String, email: String, districtId: String): SendEmailResponse = SendEmailResponse(true, "Simulated")
    override suspend fun confirmEmailToken(pubKey: String, token: String): ProofTokenResponse = ProofTokenResponse(proofToken = "sim_proof_${pubKey.take(8)}", tier = 1)
}

class WebCivicApi(private val httpClient: HttpClient) : CivicApi {
    override suspend fun fetchPolls(districtId: String, limit: Int, before: Long?): List<CivicPoll> = emptyList()
    override suspend fun fetchPoll(pollId: String): CivicPoll = throw Exception("Not implemented")
    override suspend fun createPoll(districtId: String, question: String, options: List<String>, closesAt: Long?, scope: PollScope, authorPubKey: String, localId: String?): CivicPoll = throw Exception("Not implemented")
    override suspend fun getRepresentativeVote(legislationId: String): String? = null
    override suspend fun fetchScorecard(districtId: String): RepresentativeScorecard = throw Exception("Not implemented")
    override suspend fun fetchMetrics(districtId: String): List<DistrictMetric> = emptyList()
    override suspend fun fetchManifestos(districtId: String): List<CandidateManifesto> = emptyList()
    override suspend fun fetchManifesto(manifestoId: String): CandidateManifesto = throw Exception("Not implemented")
    override suspend fun fetchProfile(pubKey: String): ResidentProfile = throw Exception("Not implemented")
    override suspend fun upgradeTier(pubKey: String, proofToken: String, targetTier: VerificationTier): ResidentProfile = throw Exception("Not implemented")
    override suspend fun fetchDistrict(districtId: String): District = District(
        id = districtId, level = DistrictLevel.FEDERAL_HOUSE, state = "US", districtNumber = 0, name = "District $districtId", displayName = "District $districtId"
    )
    override suspend fun detectDistrict(latitude: Double, longitude: Double): District = fetchDistrict("us-wa-07")
    override suspend fun refreshDistrictRegistry() {}
    override suspend fun getDistrictFromAddress(address: String): District? = resolveAddress(address).federalDistrict
    override suspend fun verifyVoterRolls(firstName: String, lastName: String, address: String, districtId: String): Boolean = true
    override suspend fun resolveAddress(address: String): AddressResolution = AddressResolution(
        address = address,
        federalDistrict = District(id = "us-fl-06", level = DistrictLevel.FEDERAL_HOUSE, state = "FL", districtNumber = 6, name = "Florida's 6th District", displayName = "FL-06"),
        stateUpperDistrict = District(id = "us-fl-senate-07", level = DistrictLevel.STATE_SENATE, state = "FL", districtNumber = 7, name = "Florida Senate District 7", displayName = "FL Senate-07"),
        stateLowerDistrict = District(id = "us-fl-house-19", level = DistrictLevel.STATE_HOUSE, state = "FL", districtNumber = 19, name = "Florida House District 19", displayName = "FL House-19"),
        localJurisdiction = "Palm Coast, Flagler County"
    )
}

class LocationHelper {
    suspend fun getCurrentLocation(): Pair<Double, Double> = 29.3751 to -81.2995
}
