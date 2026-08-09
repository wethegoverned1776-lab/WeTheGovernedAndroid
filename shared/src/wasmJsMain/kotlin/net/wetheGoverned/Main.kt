package net.wetheGoverned

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import net.wetheGoverned.App
import net.wetheGoverned.repository.*
import net.wetheGoverned.session.*
import net.wetheGoverned.data.*
// import net.wetheGoverned.data.local.* // Disabled due to Wasm compiler crash
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // V2-SYNC-ACTIVE-12-RELAYS
    println("Initializing WeTheGoverned Web Core...")
    ComposeViewport(document.getElementById("compose-target")!!) {
        val sessionStorage = remember { WebSessionStorage() }
        val sessionManager = remember { SessionManager(sessionStorage) }
        
        val httpClient = remember {
            HttpClient {
                install(ContentNegotiation) {
                    json(Json { ignoreUnknownKeys = true })
                }
            }
        }
        val civicApi = remember { WebCivicApi(httpClient) }
        val backendApi = remember { WebWtgBackendApi(httpClient) }
        val locationHelper = remember { LocationHelper() }
        
        val relayUrls = listOf(
            "wss://nos.lol", 
            "wss://relay.damus.io", 
            "wss://relay.snort.social",
            "wss://offchain.pub",
            "wss://relay.primal.net",
            "wss://nostr.mom",
            "wss://bitcoiner.social",
            "wss://purplepag.es",
            "wss://no.str.cr"
        )
        val relayManager = remember { NostrRelayManager(relayUrls) }
        
        val publisher = remember {
            WsCivicPublisher(
                relayManager, sessionManager,
                object : net.wetheGoverned.session.PendingEventQueue {
                    override suspend fun enqueue(kind: Int, contentJson: String, sig: String) {}
                    override suspend fun getAllPending(): List<net.wetheGoverned.session.PendingEvent> = emptyList()
                    override suspend fun dequeue(eventId: String) {}
                },
                object : net.wetheGoverned.zk.ZkProver {
                    override suspend fun generateProof(circuitName: String, inputs: Map<String, Any>): net.wetheGoverned.zk.ZkProofResult {
                        return net.wetheGoverned.zk.ZkProofResult(emptyList(), emptyList())
                    }
                    override suspend fun verifyProof(proof: net.wetheGoverned.zk.ZkProofResult, circuitName: String): Boolean = true
                }
            )
        }

        // Core Repositories (Web-specific LocalStorage based to bypass Room compiler issues for now)
        val voteRepository: VoteRepository = remember { WebVoteRepository(publisher) }
        val pollRepository: PollRepository = remember { WebPollRepository(publisher) }
        val residentRepository: ResidentRepository = remember { WebResidentRepository(publisher) }
        val communityRepository: CommunityRepository = remember { WebCommunityRepository(publisher) }
        val accountRepository: AccountRepository = remember { WebAccountRepository() }
        val requestRepository: VerificationRequestRepository = remember { WebVerificationRequestRepository() }
        val scorecardRepository: ScorecardRepository = remember { WebScorecardRepository() }
        val manifestoRepository: ManifestoRepository = remember { WebManifestoRepository() }
        val districtRepository: DistrictRepository = remember { WebDistrictRepository() }
        
        val meshDiscoveryManager = remember { WasmMeshDiscoveryManager(sessionManager, publisher, relayManager) }

        val syncEngine = remember {
            P2PSyncEngine(
                pollRepository, residentRepository, voteRepository,
                manifestoRepository, communityRepository, accountRepository, sessionManager,
                relayManager, publisher
            )
        }

        LaunchedEffect(Unit) {
            syncEngine.start()
            meshDiscoveryManager.discoverPeers()
            sessionManager.currentSession?.districtId?.let { districtId ->
                meshDiscoveryManager.registerService(8888, districtId)
            }
        }

        App(
            pollRepository = pollRepository,
            accountRepository = accountRepository,
            residentRepository = residentRepository,
            manifestoRepository = manifestoRepository,
            scorecardRepository = scorecardRepository,
            districtRepository = districtRepository,
            communityRepository = communityRepository,
            requestRepository = requestRepository,
            sessionManager = sessionManager,
            civicApi = civicApi,
            backendApi = backendApi,
            locationHelper = locationHelper,
            relayManager = relayManager
        )
    }
}
