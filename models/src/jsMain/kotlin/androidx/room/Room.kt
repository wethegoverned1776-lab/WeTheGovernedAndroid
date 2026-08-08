package androidx.room

object Room {
    fun <T> databaseBuilder(context: Any? = null, name: String): RoomDatabase.Builder<T> = RoomDatabase.Builder()
    fun <T> inMemoryDatabaseBuilder(context: Any? = null): RoomDatabase.Builder<T> = RoomDatabase.Builder()
}
