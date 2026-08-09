package net.wetheGoverned.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import net.wetheGoverned.`data`.local.entity.AccountEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AccountDao_Impl(
  __db: RoomDatabase,
) : AccountDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfAccountEntity: EntityUpsertAdapter<AccountEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfAccountEntity = EntityUpsertAdapter<AccountEntity>(object :
        EntityInsertAdapter<AccountEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `user_accounts` (`username`,`password`,`pubKey`,`privateKey`,`quantumPubKey`,`quantumPrivateKey`,`districtId`,`createdAt`,`requiresPasswordChange`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AccountEntity) {
        statement.bindText(1, entity.username)
        statement.bindText(2, entity.password)
        statement.bindText(3, entity.pubKey)
        statement.bindText(4, entity.privateKey)
        val _tmpQuantumPubKey: String? = entity.quantumPubKey
        if (_tmpQuantumPubKey == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpQuantumPubKey)
        }
        val _tmpQuantumPrivateKey: String? = entity.quantumPrivateKey
        if (_tmpQuantumPrivateKey == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpQuantumPrivateKey)
        }
        val _tmpDistrictId: String? = entity.districtId
        if (_tmpDistrictId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpDistrictId)
        }
        statement.bindLong(8, entity.createdAt)
        val _tmp: Int = if (entity.requiresPasswordChange) 1 else 0
        statement.bindLong(9, _tmp.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<AccountEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `user_accounts` SET `username` = ?,`password` = ?,`pubKey` = ?,`privateKey` = ?,`quantumPubKey` = ?,`quantumPrivateKey` = ?,`districtId` = ?,`createdAt` = ?,`requiresPasswordChange` = ? WHERE `username` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AccountEntity) {
        statement.bindText(1, entity.username)
        statement.bindText(2, entity.password)
        statement.bindText(3, entity.pubKey)
        statement.bindText(4, entity.privateKey)
        val _tmpQuantumPubKey: String? = entity.quantumPubKey
        if (_tmpQuantumPubKey == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpQuantumPubKey)
        }
        val _tmpQuantumPrivateKey: String? = entity.quantumPrivateKey
        if (_tmpQuantumPrivateKey == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpQuantumPrivateKey)
        }
        val _tmpDistrictId: String? = entity.districtId
        if (_tmpDistrictId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpDistrictId)
        }
        statement.bindLong(8, entity.createdAt)
        val _tmp: Int = if (entity.requiresPasswordChange) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        statement.bindText(10, entity.username)
      }
    })
  }

  public override suspend fun upsertAccount(account: AccountEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfAccountEntity.upsert(_connection, account)
  }

  public override suspend fun getAccount(username: String): AccountEntity? {
    val _sql: String = "SELECT * FROM user_accounts WHERE username = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, username)
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _columnIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "privateKey")
        val _columnIndexOfQuantumPubKey: Int = getColumnIndexOrThrow(_stmt, "quantumPubKey")
        val _columnIndexOfQuantumPrivateKey: Int = getColumnIndexOrThrow(_stmt, "quantumPrivateKey")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfRequiresPasswordChange: Int = getColumnIndexOrThrow(_stmt,
            "requiresPasswordChange")
        val _result: AccountEntity?
        if (_stmt.step()) {
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_columnIndexOfPubKey)
          val _tmpPrivateKey: String
          _tmpPrivateKey = _stmt.getText(_columnIndexOfPrivateKey)
          val _tmpQuantumPubKey: String?
          if (_stmt.isNull(_columnIndexOfQuantumPubKey)) {
            _tmpQuantumPubKey = null
          } else {
            _tmpQuantumPubKey = _stmt.getText(_columnIndexOfQuantumPubKey)
          }
          val _tmpQuantumPrivateKey: String?
          if (_stmt.isNull(_columnIndexOfQuantumPrivateKey)) {
            _tmpQuantumPrivateKey = null
          } else {
            _tmpQuantumPrivateKey = _stmt.getText(_columnIndexOfQuantumPrivateKey)
          }
          val _tmpDistrictId: String?
          if (_stmt.isNull(_columnIndexOfDistrictId)) {
            _tmpDistrictId = null
          } else {
            _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpRequiresPasswordChange: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfRequiresPasswordChange).toInt()
          _tmpRequiresPasswordChange = _tmp != 0
          _result =
              AccountEntity(_tmpUsername,_tmpPassword,_tmpPubKey,_tmpPrivateKey,_tmpQuantumPubKey,_tmpQuantumPrivateKey,_tmpDistrictId,_tmpCreatedAt,_tmpRequiresPasswordChange)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAccountCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM user_accounts"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
