package net.wetheGoverned.data.local

import androidx.room.RoomDatabase

actual typealias AppDatabase = RoomAppDatabase

actual fun getDatabaseBuilder(): Any {
    throw RuntimeException("Use platform specific builder")
}
