package net.wetheGoverned.data.local

import androidx.room.RoomDatabase

class AppDatabase : RoomDatabase() {
    fun districtDao(): Any = error("Not implemented on Web")
    fun residentProfileDao(): Any = error("Not implemented on Web")
    fun pollDao(): Any = error("Not implemented on Web")
    fun pollPostDao(): Any = error("Not implemented on Web")
    fun voteDao(): Any = error("Not implemented on Web")
    fun scorecardDao(): Any = error("Not implemented on Web")
    fun manifestoDao(): Any = error("Not implemented on Web")
    fun metricDao(): Any = error("Not implemented on Web")
    fun pendingEventDao(): Any = error("Not implemented on Web")
    fun accountDao(): Any = error("Not implemented on Web")
    fun communityPostDao(): Any = error("Not implemented on Web")
    fun verificationRequestDao(): Any = error("Not implemented on Web")
}
