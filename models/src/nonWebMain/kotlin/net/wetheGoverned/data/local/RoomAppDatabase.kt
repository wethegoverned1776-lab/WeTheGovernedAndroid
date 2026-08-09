package net.wetheGoverned.data.local

import androidx.room.*
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import net.wetheGoverned.data.local.dao.*
import net.wetheGoverned.data.local.entity.*
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [
        DistrictEntity::class,
        ResidentProfileEntity::class,
        DistrictPollEntity::class,
        CivicVoteEntity::class,
        PollPostEntity::class,
        RepresentativeScorecardEntity::class,
        ScorecardCategoryEntity::class,
        CandidateManifestoEntity::class,
        ManifestoQuestionEntity::class,
        DistrictMetricEntity::class,
        PendingCivicEventEntity::class,
        AccountEntity::class,
        CommunityPostEntity::class,
        VerificationRequestEntity::class
    ],
    version = 8,
    exportSchema = false
)
@TypeConverters(CivicConverters::class)
abstract class RoomAppDatabase : RoomDatabase() {
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

object AppDatabaseConstructor {
    fun construct(): RoomAppDatabase {
        throw RuntimeException("Use getRoomDatabase with builder")
    }
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<RoomAppDatabase>
): RoomAppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.Default)
        .build()
}
