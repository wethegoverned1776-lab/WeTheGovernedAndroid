package net.wetheGoverned.`data`.local

import androidx.room3.RoomDatabaseConstructor

public actual object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
  override fun initialize(): AppDatabase = net.wetheGoverned.`data`.local.AppDatabase_Impl()
}
