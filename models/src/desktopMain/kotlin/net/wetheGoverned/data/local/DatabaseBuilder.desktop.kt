package net.wetheGoverned.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual fun getDatabaseBuilder(): Any {
    val dbFile = File(System.getProperty("user.home"), ".wethegoverned_mesh.db")
    return Room.databaseBuilder<RoomAppDatabase>(
        name = dbFile.absolutePath,
    )
}
