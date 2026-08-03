package net.wetheGoverned.data.local

import androidx.room3.Room
import androidx.room3.RoomDatabase

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return Room.databaseBuilder<AppDatabase>(
        name = "wetheGoverned.db"
    )
}
