package net.wetheGoverned.data.local

import androidx.room3.Room
import androidx.room3.RoomDatabase
import net.wetheGoverned.util.AppContext

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = AppContext.context.applicationContext
    val dbFile = appContext.getDatabasePath("wetheGoverned.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
