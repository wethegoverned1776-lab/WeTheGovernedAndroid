package net.wetheGoverned.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.WebWorkerSQLiteDriver
import org.w3c.dom.Worker

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return try {
        Room.databaseBuilder<AppDatabase>(
            name = "wetheGoverned.db"
        ).setDriver(WebWorkerSQLiteDriver(
            worker = Worker("worker.js")
        ))
    } catch (e: Exception) {
        Room.inMemoryDatabaseBuilder<AppDatabase>()
    }
}
