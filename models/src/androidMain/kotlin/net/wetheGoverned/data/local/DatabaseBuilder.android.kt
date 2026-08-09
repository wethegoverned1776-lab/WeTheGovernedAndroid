package net.wetheGoverned.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

private lateinit var appContext: Context

fun initDatabaseContext(context: Context) {
    appContext = context.applicationContext
}

actual fun getDatabaseBuilder(): Any {
    val dbFile = appContext.getDatabasePath("wethegoverned_mesh.db")
    return Room.databaseBuilder<RoomAppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
