package net.wetheGoverned.data.local

actual abstract class AppDatabase : androidx.room.RoomDatabase()

actual fun getDatabaseBuilder(): androidx.room.RoomDatabase.Builder<AppDatabase> {
    throw Exception("Room not supported on Web target")
}
