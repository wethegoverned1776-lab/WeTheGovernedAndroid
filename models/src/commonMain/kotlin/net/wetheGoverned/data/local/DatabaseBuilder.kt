package net.wetheGoverned.data.local

import androidx.room.RoomDatabase

expect abstract class AppDatabase : RoomDatabase

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
