package net.wetheGoverned.di

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import net.wetheGoverned.BuildConfig
import net.wetheGoverned.core.*
import net.wetheGoverned.data.local.AppDatabase
import net.wetheGoverned.data.local.AppDatabaseConstructor
import net.wetheGoverned.data.local.getRoomDatabase
import net.wetheGoverned.data.MeshDiscoveryManager
import net.wetheGoverned.remote.api.CivicApi
import net.wetheGoverned.remote.api.WtgBackendApi
import net.wetheGoverned.data.CivicApiImpl
import net.wetheGoverned.repository.*
import net.wetheGoverned.session.SessionManager
import net.wetheGoverned.session.CredentialsManager
import net.wetheGoverned.session.PendingEventQueue
import net.wetheGoverned.session.AndroidPendingEventQueue
import net.wetheGoverned.zk.ZkProver
import net.wetheGoverned.zk.NativeZkProver
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CivicDatabaseModule {
    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val dbFile = context.getDatabasePath("wetheGoverned.db")
        val builder = Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        ).fallbackToDestructiveMigration()
        return getRoomDatabase(builder)
    }

    @Provides @Singleton fun provideDistrictDao(db: AppDatabase)  = db.districtDao()
    @Provides @Singleton fun provideProfileDao(db: AppDatabase)   = db.residentProfileDao()
    @Provides @Singleton fun providePollDao(db: AppDatabase)      = db.pollDao()
    @Provides @Singleton fun providePollPostDao(db: AppDatabase)  = db.pollPostDao()
    @Provides @Singleton fun provideVoteDao(db: AppDatabase)      = db.voteDao()
    @Provides @Singleton fun provideScorecardDao(db: AppDatabase) = db.scorecardDao()
    @Provides @Singleton fun provideManifestoDao(db: AppDatabase) = db.manifestoDao()
    @Provides @Singleton fun provideMetricDao(db: AppDatabase)    = db.metricDao()
    @Provides @Singleton fun providePendingDao(db: AppDatabase)   = db.pendingEventDao()
    @Provides @Singleton fun provideAccountDao(db: AppDatabase)   = db.accountDao()
    @Provides @Singleton fun provideCommunityDao(db: AppDatabase) = db.communityPostDao()
    @Provides @Singleton fun provideVerificationRequestDao(db: AppDatabase) = db.verificationRequestDao()
}

@Module
@InstallIn(SingletonComponent::class)
object CivicNetworkModule {

    @Provides @Singleton
    fun provideRelayUrls(): List<String> = listOf(
        BuildConfig.RELAY_URL,
        "wss://relay.damus.io",
        "wss://nos.lol",
        "wss://relay.primal.net",
    )

    @Provides @Singleton
    fun provideCivicApi(): CivicApi = CivicApiImpl()

    @Provides @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true; isLenient = true })
        }
    }

    @Provides @Singleton
    fun provideWtgBackendApi(httpClient: HttpClient): WtgBackendApi = WtgBackendApi(httpClient = httpClient)

    @Provides @Singleton
    fun provideSessionManager(storage: CredentialsManager): SessionManager = SessionManager(storage)

    @Provides @Singleton
    fun provideLocationHelper(): net.wetheGoverned.LocationHelper =
        net.wetheGoverned.LocationHelper()

    @Provides @Singleton
    fun provideMeshDiscoveryManager(
        sessionManager: SessionManager,
        publisher: CivicPublisher,
        relayManager: net.wetheGoverned.data.NostrRelayManager
    ): MeshDiscoveryManager = net.wetheGoverned.data.AndroidMeshDiscoveryManager(
        sessionManager, publisher, relayManager
    )

    @Provides @Singleton
    fun provideP2PSyncEngine(
        pollRepository: PollRepository,
        residentRepository: ResidentRepository,
        voteRepository: VoteRepository,
        manifestoRepository: ManifestoRepository,
        communityRepository: CommunityRepository,
        accountRepository: AccountRepository,
        sessionManager: SessionManager,
        relayManager: net.wetheGoverned.data.NostrRelayManager,
        publisher: CivicPublisher
    ): net.wetheGoverned.data.P2PSyncEngine = net.wetheGoverned.data.P2PSyncEngine(
        pollRepository, residentRepository, voteRepository, manifestoRepository, 
        communityRepository, accountRepository, sessionManager, relayManager,
        publisher
    )

    @Provides @Singleton
    fun provideNostrRelayManager(relayUrls: List<String>): net.wetheGoverned.data.NostrRelayManager =
        net.wetheGoverned.data.NostrRelayManager(relayUrls)

    @Provides @Singleton
    fun provideWsCivicPublisher(
        relayManager: net.wetheGoverned.data.NostrRelayManager,
        sessionManager: SessionManager,
        pendingQueue: PendingEventQueue,
        zkProver: ZkProver,
    ): CivicPublisher = net.wetheGoverned.data.WsCivicPublisher(
        relayManager, sessionManager, pendingQueue, zkProver
    )
}

@Module
@InstallIn(SingletonComponent::class)
object CivicRepositoryModule {
    @Provides @Singleton
    fun provideAccountRepository(db: AppDatabase): AccountRepository = RoomAccountRepository(db)

    @Provides @Singleton
    fun providePollRepository(db: AppDatabase, publisher: CivicPublisher): PollRepository = RoomPollRepository(db, publisher)

    @Provides @Singleton
    fun provideResidentRepository(db: AppDatabase, publisher: CivicPublisher): ResidentRepository = RoomResidentRepository(db, publisher)

    @Provides @Singleton
    fun provideVoteRepository(db: AppDatabase): VoteRepository = RoomVoteRepository(db)

    @Provides @Singleton
    fun provideCommunityRepository(db: AppDatabase, publisher: CivicPublisher): CommunityRepository = RoomCommunityRepository(db, publisher)

    @Provides @Singleton
    fun provideVerificationRequestRepository(db: AppDatabase): VerificationRequestRepository = RoomVerificationRequestRepository(db)

    @Provides @Singleton
    fun provideScorecardRepository(db: AppDatabase): ScorecardRepository = RoomScorecardRepository(db)

    @Provides @Singleton
    fun provideManifestoRepository(db: AppDatabase): ManifestoRepository = RoomManifestoRepository(db)

    @Provides @Singleton
    fun provideDistrictRepository(db: AppDatabase): DistrictRepository = RoomDistrictRepository(db)
}


@Module
@InstallIn(SingletonComponent::class)
interface CivicBindingModule {
    @Binds @Singleton fun bindZkProver(impl: NativeZkProver): ZkProver
    @Binds @Singleton fun bindPendingEventQueue(impl: AndroidPendingEventQueue): PendingEventQueue

    companion object {
        @Provides @Singleton fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
    }
}


