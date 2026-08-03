package net.wetheGoverned

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import net.wetheGoverned.App
import net.wetheGoverned.repository.*
import net.wetheGoverned.session.*
import net.wetheGoverned.data.*
import net.wetheGoverned.data.local.*
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
            "wss://atlas.nostr.land",
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
                    override suspend fun generateProof(circuitName: String, inputs: Map<String, Any>): net.wetheGoverned.zk.ZkProofResult = 
                        net.wetheGoverned.zk.ZkProofResult(emptyList(), emptyList())
                }
            )
        }

        // Initialize Room Database
        val databaseBuilder = remember { getDatabaseBuilder() }
        val database = remember { getRoomDatabase(databaseBuilder) }

        // Core Repositories (Room-based)
        val voteRepository: VoteRepository = remember { RoomVoteRepository(database) }
        val pollRepository: PollRepository = remember { RoomPollRepository(database, publisher) }
        val residentRepository: ResidentRepository = remember { RoomResidentRepository(database, publisher) }
        val communityRepository: CommunityRepository = remember { RoomCommunityRepository(database, publisher) }
        val accountRepository: AccountRepository = remember { RoomAccountRepository(database) }
        val requestRepository: VerificationRequestRepository = remember { RoomVerificationRequestRepository(database) }
        val scorecardRepository: ScorecardRepository = remember { RoomScorecardRepository(database) }
        val manifestoRepository: ManifestoRepository = remember { RoomManifestoRepository(database) }
        val districtRepository: DistrictRepository = remember { RoomDistrictRepository(database) }
        
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
