package net.wetheGoverned.`data`.local

import androidx.room.RoomDatabaseConstructor

public actual object AppDatabaseConstructor : RoomDatabaseConstructor<RoomAppDatabase> {
  override fun initialize(): RoomAppDatabase = net.wetheGoverned.`data`.local.RoomAppDatabase_Impl()
}
