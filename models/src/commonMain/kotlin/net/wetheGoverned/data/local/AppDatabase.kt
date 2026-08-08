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

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .fallbackToDestructiveMigration(true)
        .setQueryCoroutineContext(Dispatchers.Default)
        .build()
}
