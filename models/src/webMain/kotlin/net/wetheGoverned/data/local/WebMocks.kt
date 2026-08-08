package net.wetheGoverned.data.local

actual abstract class AppDatabase {
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

actual fun getDatabaseBuilder(): Any = error("Not supported on Web")
