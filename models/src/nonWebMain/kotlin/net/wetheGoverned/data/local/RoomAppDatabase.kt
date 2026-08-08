package net.wetheGoverned.data.local

import androidx.room.*
import kotlinx.coroutines.Dispatchers
import net.wetheGoverned.data.local.dao.*
import net.wetheGoverned.data.local.entity.*

@Database(
    entities = [
        DistrictEntity::class,
        ResidentProfileEntity::class,
        DistrictPollEntity::class,
        PollPostEntity::class,
        CivicVoteEntity::class,
        RepresentativeScorecardEntity::class,
        ScorecardCategoryEntity::class,
        CandidateManifestoEntity::class,
        ManifestoQuestionEntity::class,
        DistrictMetricEntity::class,
        PendingCivicEventEntity::class,
        AccountEntity::class,
        CommunityPostEntity::class,
        VerificationRequestEntity::class,
    ],
    version = 11,
    exportSchema = false
)
@TypeConverters(CivicConverters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomAppDatabase : RoomDatabase(), AppDatabase {
    abstract override fun districtDao(): DistrictDao
    abstract override fun residentProfileDao(): ResidentProfileDao
    abstract override fun pollDao(): PollDao
    abstract override fun pollPostDao(): PollPostDao
    abstract override fun voteDao(): VoteDao
    abstract override fun scorecardDao(): ScorecardDao
    abstract override fun manifestoDao(): ManifestoDao
    abstract override fun metricDao(): MetricDao
    abstract override fun pendingEventDao(): PendingEventDao
    abstract override fun accountDao(): AccountDao
    abstract override fun communityPostDao(): CommunityPostDao
    abstract override fun verificationRequestDao(): VerificationRequestDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomAppDatabase>

fun getRoomDatabase(
    builder: RoomDatabase.Builder<RoomAppDatabase>
): RoomAppDatabase {
    return builder
        .fallbackToDestructiveMigration(true)
        .setQueryCoroutineContext(Dispatchers.Default)
        .build()
}
