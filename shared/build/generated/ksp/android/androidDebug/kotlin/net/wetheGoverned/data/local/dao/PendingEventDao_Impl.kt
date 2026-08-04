package net.wetheGoverned.`data`.local.dao

import androidx.room3.EntityDeleteOrUpdateAdapter
import androidx.room3.EntityInsertAdapter
import androidx.room3.EntityUpsertAdapter
import androidx.room3.RoomDatabase
import androidx.room3.util.getColumnIndexOrThrow
import androidx.room3.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import net.wetheGoverned.`data`.local.entity.PendingCivicEventEntity

@Generated(value = ["androidx.room3.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL", "MemberExtensionConflict"])
internal class PendingEventDao_Impl(
  __db: RoomDatabase,
) : PendingEventDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfPendingCivicEventEntity: EntityUpsertAdapter<PendingCivicEventEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfPendingCivicEventEntity = EntityUpsertAdapter<PendingCivicEventEntity>(object : EntityInsertAdapter<PendingCivicEventEntity>() {
      protected override fun createQuery(): String = "INSERT INTO `pending_civic_events` (`eventId`,`kind`,`contentJson`,`sig`,`createdAt`,`retryCount`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PendingCivicEventEntity) {
        statement.bindText(1, entity.eventId)
        statement.bindLong(2, entity.kind.toLong())
        statement.bindText(3, entity.contentJson)
        statement.bindText(4, entity.sig)
        statement.bindLong(5, entity.createdAt)
        statement.bindLong(6, entity.retryCount.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<PendingCivicEventEntity>() {
      protected override fun createQuery(): String = "UPDATE `pending_civic_events` SET `eventId` = ?,`kind` = ?,`contentJson` = ?,`sig` = ?,`createdAt` = ?,`retryCount` = ? WHERE `eventId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PendingCivicEventEntity) {
        statement.bindText(1, entity.eventId)
        statement.bindLong(2, entity.kind.toLong())
        statement.bindText(3, entity.contentJson)
        statement.bindText(4, entity.sig)
        statement.bindLong(5, entity.createdAt)
        statement.bindLong(6, entity.retryCount.toLong())
        statement.bindText(7, entity.eventId)
      }
    })
  }

  public override suspend fun enqueue(event: PendingCivicEventEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfPendingCivicEventEntity.upsert(_connection, event)
  }

  public override suspend fun getAllPending(): List<PendingCivicEventEntity> {
    val _sql: String = "SELECT * FROM pending_civic_events ORDER BY createdAt ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfEventId: Int = getColumnIndexOrThrow(_stmt, "eventId")
        val _columnIndexOfKind: Int = getColumnIndexOrThrow(_stmt, "kind")
        val _columnIndexOfContentJson: Int = getColumnIndexOrThrow(_stmt, "contentJson")
        val _columnIndexOfSig: Int = getColumnIndexOrThrow(_stmt, "sig")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retryCount")
        val _result: MutableList<PendingCivicEventEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PendingCivicEventEntity
          val _tmpEventId: String
          _tmpEventId = _stmt.getText(_columnIndexOfEventId)
          val _tmpKind: Int
          _tmpKind = _stmt.getLong(_columnIndexOfKind).toInt()
          val _tmpContentJson: String
          _tmpContentJson = _stmt.getText(_columnIndexOfContentJson)
          val _tmpSig: String
          _tmpSig = _stmt.getText(_columnIndexOfSig)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          _item = PendingCivicEventEntity(_tmpEventId,_tmpKind,_tmpContentJson,_tmpSig,_tmpCreatedAt,_tmpRetryCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun dequeue(eventId: String) {
    val _sql: String = "DELETE FROM pending_civic_events WHERE eventId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, eventId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredColumnConverters(): List<KClass<*>> = emptyList()

    public fun getRequiredDaoReturnTypeConverters(): List<KClass<*>> = emptyList()
  }
}
