package net.wetheGoverned.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun getDatabaseBuilder(): Any {
    val dbFilePath = documentDirectory() + "/wethegoverned_mesh.db"
    return Room.databaseBuilder<RoomAppDatabase>(
        name = dbFilePath,
        factory = { AppDatabaseConstructor.construct() }
    )
}

private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return documentDirectory?.path ?: ""
}
