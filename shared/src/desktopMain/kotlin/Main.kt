package net.wetheGoverned

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.*
import net.wetheGoverned.App
import net.wetheGoverned.data.*
import net.wetheGoverned.data.local.*
import net.wetheGoverned.repository.*
import net.wetheGoverned.session.SessionManager
import net.wetheGoverned.session.DesktopSessionStorage
import net.wetheGoverned.remote.api.WtgBackendApi
import java.io.File
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.flow.flowOf

fun main() {
    application {
        var isWindowVisible by remember { mutableStateOf(true) }
        val trayState = rememberTrayState()
        
        val sessionStorage = remember { DesktopSessionStorage() }
        val sessionManager = remember { SessionManager(sessionStorage) }

        val httpClient = remember { 
            HttpClient(CIO) {
                install(ContentNegotiation) { 
                    json(kotlinx.serialization.json.Json { ignoreUnknownKeys = true })
                }
            } 
        }
        val civicApi = remember { DesktopCivicApi(httpClient) }
        val locationHelper = remember { LocationHelper() }
        val backendApi = remember { DesktopWtgBackendApi() }
        
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
        
        val meshDiscoveryManager = remember { DesktopMeshDiscoveryManager(sessionManager, publisher, relayManager) }

        val p2pSyncEngine = remember {
            P2PSyncEngine(
                pollRepository, residentRepository, voteRepository,
                manifestoRepository, communityRepository, accountRepository, sessionManager,
                relayManager, publisher
            )
        }

        LaunchedEffect(Unit) {
            p2pSyncEngine.start()
            meshDiscoveryManager.discoverPeers()
            sessionManager.currentSession?.districtId?.let { districtId ->
                meshDiscoveryManager.registerService(8888, districtId)
            }
        }

        Tray(
            state = trayState,
            icon = painterResource("icon.png"),
            tooltip = "WeTheGoverned Node (Active)",
            onAction = { isWindowVisible = true }, 
            menu = {
                Item("Open Dashboard", onClick = { isWindowVisible = true })
                Separator()
                Item("Exit Fully", onClick = ::exitApplication)
            }
        )

        if (isWindowVisible) {
            Window(
                onCloseRequest = { isWindowVisible = false },
                title = "WeTheGoverned",
                icon = painterResource("icon.png"),
                state = rememberWindowState(placement = WindowPlacement.Maximized)
            ) {
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
    }
}
