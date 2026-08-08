package net.wetheGoverned.data.local

import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

// Mock Database
abstract class AppDatabase : RoomDatabase() {
    abstract fun districtDao(): DistrictDao
    abstract fun residentProfileDao(): ResidentProfileDao
    abstract fun pollDao(): PollDao
    abstract fun pollPostDao(): PollPostDao
    abstract fun voteDao(): VoteDao
    abstract fun scorecardDao(): ScorecardDao
    abstract fun manifestoDao(): ManifestoDao
    abstract fun metricDao(): MetricDao
    abstract fun pendingEventDao(): PendingEventDao
    abstract fun accountDao(): AccountDao
    abstract fun communityPostDao(): CommunityPostDao
    abstract fun verificationRequestDao(): VerificationRequestDao
}

// Mock DAOs
interface DistrictDao {
    fun observeDistrict(districtId: String): Flow<Any?> = flowOf(null)
    suspend fun getDistrict(districtId: String): Any? = null
    suspend fun upsertDistrict(district: Any) {}
}
interface ResidentProfileDao {
    fun observeProfile(pubKey: String): Flow<Any?> = flowOf(null)
    suspend fun upsertProfile(profile: Any) {}
    suspend fun getProfile(pubKey: String): Any? = null
    fun observeProfilesVerifiedBy(verifierPubKey: String): Flow<List<Any>> = flowOf(emptyList())
}
interface PollDao {
    fun observePolls(districtId: String): Flow<List<Any>> = flowOf(emptyList())
    suspend fun upsertPoll(poll: Any) {}
}
interface PollPostDao {
    fun getAllPostsForPoll(pollId: String): Flow<List<Any>> = flowOf(emptyList())
    suspend fun upsertPost(post: Any) {}
}
interface VoteDao {
    fun observeAllVotes(): Flow<List<Any>> = flowOf(emptyList())
    suspend fun upsertVote(vote: Any) {}
}
interface ScorecardDao {
    fun observeScorecard(districtId: String): Flow<Any?> = flowOf(null)
    suspend fun upsertScorecard(scorecard: Any) {}
}
interface ManifestoDao {
    fun observeManifestos(districtId: String): Flow<List<Any>> = flowOf(emptyList())
    suspend fun upsertManifesto(manifesto: Any) {}
}
interface MetricDao {
    fun observeMetrics(districtId: String): Flow<List<Any>> = flowOf(emptyList())
    suspend fun upsertMetric(metric: Any) {}
}
interface PendingEventDao {
    suspend fun getAllPending(): List<Any> = emptyList()
    suspend fun enqueue(event: Any) {}
    suspend fun dequeue(eventId: String) {}
}
interface AccountDao {
    suspend fun getAccount(username: String): Any? = null
    suspend fun upsertAccount(account: Any) {}
}
interface CommunityPostDao {
    fun observePosts(districtId: String): Flow<List<Any>> = flowOf(emptyList())
    suspend fun upsertPost(post: Any) {}
}
interface VerificationRequestDao {
    fun observeRequestsForDistrict(districtId: String): Flow<List<Any>> = flowOf(emptyList())
    suspend fun upsertRequest(request: Any) {}
}

// Mock Constructor
interface RoomDatabaseConstructor<T>

object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>
