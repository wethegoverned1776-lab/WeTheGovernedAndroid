package net.wetheGoverned.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
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
import kotlinx.coroutines.flow.Flow
import net.wetheGoverned.`data`.local.CivicConverters
import net.wetheGoverned.`data`.local.entity.VerificationRequestEntity
import net.wetheGoverned.model.VerificationRequestStatus

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class VerificationRequestDao_Impl(
  __db: RoomDatabase,
) : VerificationRequestDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfVerificationRequestEntity:
      EntityUpsertAdapter<VerificationRequestEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfVerificationRequestEntity =
        EntityUpsertAdapter<VerificationRequestEntity>(object :
        EntityInsertAdapter<VerificationRequestEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `verification_requests` (`id`,`requesterPubKey`,`requesterDisplayName`,`email`,`districtId`,`stateId`,`address`,`createdAt`,`status`,`handledByPubKey`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: VerificationRequestEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.requesterPubKey)
        statement.bindText(3, entity.requesterDisplayName)
        statement.bindText(4, entity.email)
        statement.bindText(5, entity.districtId)
        statement.bindText(6, entity.stateId)
        statement.bindText(7, entity.address)
        statement.bindLong(8, entity.createdAt)
        val _tmp: String = __civicConverters.fromVerificationRequestStatus(entity.status)
        statement.bindText(9, _tmp)
        val _tmpHandledByPubKey: String? = entity.handledByPubKey
        if (_tmpHandledByPubKey == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpHandledByPubKey)
        }
        statement.bindLong(11, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<VerificationRequestEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `verification_requests` SET `id` = ?,`requesterPubKey` = ?,`requesterDisplayName` = ?,`email` = ?,`districtId` = ?,`stateId` = ?,`address` = ?,`createdAt` = ?,`status` = ?,`handledByPubKey` = ?,`cachedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: VerificationRequestEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.requesterPubKey)
        statement.bindText(3, entity.requesterDisplayName)
        statement.bindText(4, entity.email)
        statement.bindText(5, entity.districtId)
        statement.bindText(6, entity.stateId)
        statement.bindText(7, entity.address)
        statement.bindLong(8, entity.createdAt)
        val _tmp: String = __civicConverters.fromVerificationRequestStatus(entity.status)
        statement.bindText(9, _tmp)
        val _tmpHandledByPubKey: String? = entity.handledByPubKey
        if (_tmpHandledByPubKey == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpHandledByPubKey)
        }
        statement.bindLong(11, entity.cachedAt)
        statement.bindText(12, entity.id)
      }
    })
  }

  public override suspend fun upsertRequest(request: VerificationRequestEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfVerificationRequestEntity.upsert(_connection, request)
  }

  public override fun observeRequestsForDistrict(districtId: String):
      Flow<List<VerificationRequestEntity>> {
    val _sql: String =
        "SELECT * FROM verification_requests WHERE districtId = ? ORDER BY createdAt DESC"
    return createFlow(__db, false, arrayOf("verification_requests")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfRequesterPubKey: Int = getColumnIndexOrThrow(_stmt, "requesterPubKey")
        val _columnIndexOfRequesterDisplayName: Int = getColumnIndexOrThrow(_stmt,
            "requesterDisplayName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfStateId: Int = getColumnIndexOrThrow(_stmt, "stateId")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfHandledByPubKey: Int = getColumnIndexOrThrow(_stmt, "handledByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<VerificationRequestEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: VerificationRequestEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpRequesterPubKey: String
          _tmpRequesterPubKey = _stmt.getText(_columnIndexOfRequesterPubKey)
          val _tmpRequesterDisplayName: String
          _tmpRequesterDisplayName = _stmt.getText(_columnIndexOfRequesterDisplayName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpStateId: String
          _tmpStateId = _stmt.getText(_columnIndexOfStateId)
          val _tmpAddress: String
          _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpStatus: VerificationRequestStatus
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfStatus)
          _tmpStatus = __civicConverters.toVerificationRequestStatus(_tmp)
          val _tmpHandledByPubKey: String?
          if (_stmt.isNull(_columnIndexOfHandledByPubKey)) {
            _tmpHandledByPubKey = null
          } else {
            _tmpHandledByPubKey = _stmt.getText(_columnIndexOfHandledByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item =
              VerificationRequestEntity(_tmpId,_tmpRequesterPubKey,_tmpRequesterDisplayName,_tmpEmail,_tmpDistrictId,_tmpStateId,_tmpAddress,_tmpCreatedAt,_tmpStatus,_tmpHandledByPubKey,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeRequestsForState(stateId: String):
      Flow<List<VerificationRequestEntity>> {
    val _sql: String =
        "SELECT * FROM verification_requests WHERE stateId = ? ORDER BY createdAt DESC"
    return createFlow(__db, false, arrayOf("verification_requests")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, stateId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfRequesterPubKey: Int = getColumnIndexOrThrow(_stmt, "requesterPubKey")
        val _columnIndexOfRequesterDisplayName: Int = getColumnIndexOrThrow(_stmt,
            "requesterDisplayName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfStateId: Int = getColumnIndexOrThrow(_stmt, "stateId")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfHandledByPubKey: Int = getColumnIndexOrThrow(_stmt, "handledByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<VerificationRequestEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: VerificationRequestEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpRequesterPubKey: String
          _tmpRequesterPubKey = _stmt.getText(_columnIndexOfRequesterPubKey)
          val _tmpRequesterDisplayName: String
          _tmpRequesterDisplayName = _stmt.getText(_columnIndexOfRequesterDisplayName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpStateId: String
          _tmpStateId = _stmt.getText(_columnIndexOfStateId)
          val _tmpAddress: String
          _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpStatus: VerificationRequestStatus
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfStatus)
          _tmpStatus = __civicConverters.toVerificationRequestStatus(_tmp)
          val _tmpHandledByPubKey: String?
          if (_stmt.isNull(_columnIndexOfHandledByPubKey)) {
            _tmpHandledByPubKey = null
          } else {
            _tmpHandledByPubKey = _stmt.getText(_columnIndexOfHandledByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item =
              VerificationRequestEntity(_tmpId,_tmpRequesterPubKey,_tmpRequesterDisplayName,_tmpEmail,_tmpDistrictId,_tmpStateId,_tmpAddress,_tmpCreatedAt,_tmpStatus,_tmpHandledByPubKey,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRequest(id: String): VerificationRequestEntity? {
    val _sql: String = "SELECT * FROM verification_requests WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfRequesterPubKey: Int = getColumnIndexOrThrow(_stmt, "requesterPubKey")
        val _columnIndexOfRequesterDisplayName: Int = getColumnIndexOrThrow(_stmt,
            "requesterDisplayName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfStateId: Int = getColumnIndexOrThrow(_stmt, "stateId")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfHandledByPubKey: Int = getColumnIndexOrThrow(_stmt, "handledByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: VerificationRequestEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpRequesterPubKey: String
          _tmpRequesterPubKey = _stmt.getText(_columnIndexOfRequesterPubKey)
          val _tmpRequesterDisplayName: String
          _tmpRequesterDisplayName = _stmt.getText(_columnIndexOfRequesterDisplayName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpStateId: String
          _tmpStateId = _stmt.getText(_columnIndexOfStateId)
          val _tmpAddress: String
          _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpStatus: VerificationRequestStatus
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfStatus)
          _tmpStatus = __civicConverters.toVerificationRequestStatus(_tmp)
          val _tmpHandledByPubKey: String?
          if (_stmt.isNull(_columnIndexOfHandledByPubKey)) {
            _tmpHandledByPubKey = null
          } else {
            _tmpHandledByPubKey = _stmt.getText(_columnIndexOfHandledByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _result =
              VerificationRequestEntity(_tmpId,_tmpRequesterPubKey,_tmpRequesterDisplayName,_tmpEmail,_tmpDistrictId,_tmpStateId,_tmpAddress,_tmpCreatedAt,_tmpStatus,_tmpHandledByPubKey,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateStatus(
    id: String,
    status: VerificationRequestStatus,
    handledBy: String,
  ) {
    val _sql: String =
        "UPDATE verification_requests SET status = ?, handledByPubKey = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: String = __civicConverters.fromVerificationRequestStatus(status)
        _stmt.bindText(_argIndex, _tmp)
        _argIndex = 2
        _stmt.bindText(_argIndex, handledBy)
        _argIndex = 3
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
