package net.wetheGoverned.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.*
import net.wetheGoverned.core.CivicPublisher
import net.wetheGoverned.session.SessionManager
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException

/**
 * Desktop implementation of MeshDiscoveryManager using UDP Broadcast + Nostr Fallback.
 * Simple and effective for local mesh discovery on PC.
 */
class DesktopMeshDiscoveryManager(
    private val sessionManager: SessionManager? = null,
    private val publisher: CivicPublisher? = null,
    private val relayManager: NostrRelayManager? = null
) : MeshDiscoveryManager {
    private val _discoveredPeers = MutableStateFlow<Set<String>>(emptySet())
    override val discoveredPeers: StateFlow<Set<String>> = _discoveredPeers.asStateFlow()
    
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var socket: DatagramSocket? = null
    private val discoveryPort = 8888
    private val broadcastInterval = 5000L
    private val nostrAnnounceInterval = 60000L

    override fun registerService(port: Int, districtId: String) {
        // 1. UDP Broadcast
        scope.launch {
            try {
                val socket = DatagramSocket().apply { broadcast = true }
                val message = "WTG_NODE:$districtId:$port"
                val data = message.toByteArray()
                val address = InetAddress.getByName("255.255.255.255")
                
                while (isActive) {
                    val packet = DatagramPacket(data, data.size, address, discoveryPort)
                    socket.send(packet)
                    delay(broadcastInterval)
                }
            } catch (ignore: Exception) {}
        }

        // 2. Nostr Presence
        scope.launch {
            while (isActive) {
                val pubKey = sessionManager?.currentSession?.pubKey ?: "guest"
                publisher?.signPublishImportCivicEvent(
                    kind = 30311,
                    tags = listOf(
                        listOf("d", "wtg_presence_$pubKey"), 
                        listOf("g", districtId), 
                        listOf("port", port.toString()),
                        listOf("type", "desktop")
                    ),
                    content = "ONLINE",
                    pubKey = pubKey
                )
                delay(nostrAnnounceInterval)
            }
        }
    }

    override fun discoverPeers() {
        // 1. UDP Listener
        scope.launch {
            try {
                socket = DatagramSocket(discoveryPort).apply { soTimeout = 10000 }
                val buffer = ByteArray(1024)
                while (isActive) {
                    val packet = DatagramPacket(buffer, buffer.size)
                    try {
                        socket?.receive(packet)
                        val message = String(packet.data, 0, packet.length)
                        if (message.startsWith("WTG_NODE:")) {
                            packet.address.hostAddress?.let { _discoveredPeers.value += it }
                        }
                    } catch (ignore: Exception) {}
                }
            } catch (e: SocketException) {}
        }

        // 2. Nostr Listener
        scope.launch {
            relayManager?.events?.collect { event ->
                if (event.kind == 30311) {
                    val isWtg = event.tags.any { it.size >= 2 && it[0] == "d" && it[1].startsWith("wtg_presence_") }
                    if (isWtg) {
                        _discoveredPeers.value += event.pubKey
                    }
                }
            }
        }
        
        scope.launch {
            relayManager?.subscribe(
                "discovery_desktop_${Clock.System.now().toEpochMilliseconds()}",
                buildJsonObject {
                    put("kinds", buildJsonArray { add(JsonPrimitive(30311)) })
                }
            )
        }
    }

    override fun stopDiscovery() {
        scope.cancel()
        socket?.close()
    }
}
