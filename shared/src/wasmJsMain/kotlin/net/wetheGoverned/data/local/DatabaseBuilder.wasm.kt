package net.wetheGoverned.data.local

import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.WebWorkerSQLiteDriver
import org.w3c.dom.Worker

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    println("🔧 Initializing Room Wasm Builder with worker.js...")
    return try {
        Room.databaseBuilder<AppDatabase>(
            name = "wetheGoverned.db"
        ).setDriver(WebWorkerSQLiteDriver(
            worker = Worker("worker.js")
        ))
    } catch (e: Exception) {
        println("❌ Failed to initialize WebWorkerSQLiteDriver: ${e.message}")
        println("💡 Falling back to in-memory database for this session.")
        Room.inMemoryDatabaseBuilder<AppDatabase>()
    }
}
