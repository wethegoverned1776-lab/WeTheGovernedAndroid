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
        val _cursorIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _cursorIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _cursorIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _cursorIndexOfPrivateKey: Int = getColumnIndexOrThrow(_stmt, "privateKey")
        val _cursorIndexOfQuantumPubKey: Int = getColumnIndexOrThrow(_stmt, "quantumPubKey")
        val _cursorIndexOfQuantumPrivateKey: Int = getColumnIndexOrThrow(_stmt, "quantumPrivateKey")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _cursorIndexOfRequiresPasswordChange: Int = getColumnIndexOrThrow(_stmt,
            "requiresPasswordChange")
        val _result: AccountEntity?
        if (_stmt.step()) {
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_cursorIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_cursorIndexOfPassword)
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_cursorIndexOfPubKey)
          val _tmpPrivateKey: String
          _tmpPrivateKey = _stmt.getText(_cursorIndexOfPrivateKey)
          val _tmpQuantumPubKey: String?
          if (_stmt.isNull(_cursorIndexOfQuantumPubKey)) {
            _tmpQuantumPubKey = null
          } else {
            _tmpQuantumPubKey = _stmt.getText(_cursorIndexOfQuantumPubKey)
          }
          val _tmpQuantumPrivateKey: String?
          if (_stmt.isNull(_cursorIndexOfQuantumPrivateKey)) {
            _tmpQuantumPrivateKey = null
          } else {
            _tmpQuantumPrivateKey = _stmt.getText(_cursorIndexOfQuantumPrivateKey)
          }
          val _tmpDistrictId: String?
          if (_stmt.isNull(_cursorIndexOfDistrictId)) {
            _tmpDistrictId = null
          } else {
            _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          val _tmpRequiresPasswordChange: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfRequiresPasswordChange).toInt()
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
