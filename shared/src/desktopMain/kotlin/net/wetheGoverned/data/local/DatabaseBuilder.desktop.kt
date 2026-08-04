package net.wetheGoverned.data.local

import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("user.home"), ".wethegoverned/wetheGoverned.db")
    dbFile.parentFile.mkdirs()
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    ).setDriver(BundledSQLiteDriver())
}
