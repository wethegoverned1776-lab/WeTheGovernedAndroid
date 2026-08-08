package net.wetheGoverned.data.local

import androidx.room.*

abstract class AppDatabase : RoomDatabase() {
    abstract fun districtDao(): Any
    abstract fun residentProfileDao(): Any
    abstract fun pollDao(): Any
    abstract fun pollPostDao(): Any
    abstract fun voteDao(): Any
    abstract fun scorecardDao(): Any
    abstract fun manifestoDao(): Any
    abstract fun metricDao(): Any
    abstract fun pendingEventDao(): Any
    abstract fun accountDao(): Any
    abstract fun communityPostDao(): Any
    abstract fun verificationRequestDao(): Any
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder.build()
}
