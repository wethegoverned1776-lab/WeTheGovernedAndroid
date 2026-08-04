package net.wetheGoverned.`data`.local.dao

import androidx.room3.EntityDeleteOrUpdateAdapter
import androidx.room3.EntityInsertAdapter
import androidx.room3.EntityUpsertAdapter
import androidx.room3.RoomDatabase
import androidx.room3.coroutines.createFlow
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
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow
import net.wetheGoverned.`data`.local.CivicConverters
import net.wetheGoverned.`data`.local.entity.DistrictEntity
import net.wetheGoverned.model.DistrictLevel

@Generated(value = ["androidx.room3.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL", "MemberExtensionConflict"])
internal class DistrictDao_Impl(
  __db: RoomDatabase,
) : DistrictDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfDistrictEntity: EntityUpsertAdapter<DistrictEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfDistrictEntity = EntityUpsertAdapter<DistrictEntity>(object : EntityInsertAdapter<DistrictEntity>() {
      protected override fun createQuery(): String = "INSERT INTO `districts` (`id`,`level`,`state`,`districtNumber`,`displayName`,`representativeName`,`representativeParty`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DistrictEntity) {
        statement.bindText(1, entity.id)
        val _tmp: String = __civicConverters.fromDistrictLevel(entity.level)
        statement.bindText(2, _tmp)
        statement.bindText(3, entity.state)
        val _tmpDistrictNumber: Int? = entity.districtNumber
        if (_tmpDistrictNumber == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpDistrictNumber.toLong())
        }
        statement.bindText(5, entity.displayName)
        val _tmpRepresentativeName: String? = entity.representativeName
        if (_tmpRepresentativeName == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpRepresentativeName)
        }
        val _tmpRepresentativeParty: String? = entity.representativeParty
        if (_tmpRepresentativeParty == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpRepresentativeParty)
        }
        statement.bindLong(8, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<DistrictEntity>() {
      protected override fun createQuery(): String = "UPDATE `districts` SET `id` = ?,`level` = ?,`state` = ?,`districtNumber` = ?,`displayName` = ?,`representativeName` = ?,`representativeParty` = ?,`cachedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DistrictEntity) {
        statement.bindText(1, entity.id)
        val _tmp: String = __civicConverters.fromDistrictLevel(entity.level)
        statement.bindText(2, _tmp)
        statement.bindText(3, entity.state)
        val _tmpDistrictNumber: Int? = entity.districtNumber
        if (_tmpDistrictNumber == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpDistrictNumber.toLong())
        }
        statement.bindText(5, entity.displayName)
        val _tmpRepresentativeName: String? = entity.representativeName
        if (_tmpRepresentativeName == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpRepresentativeName)
        }
        val _tmpRepresentativeParty: String? = entity.representativeParty
        if (_tmpRepresentativeParty == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpRepresentativeParty)
        }
        statement.bindLong(8, entity.cachedAt)
        statement.bindText(9, entity.id)
      }
    })
  }

  public override suspend fun upsertDistrict(district: DistrictEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfDistrictEntity.upsert(_connection, district)
  }

  public override fun observeDistrict(districtId: String): Flow<DistrictEntity?> {
    val _sql: String = "SELECT * FROM districts WHERE id = ?"
    return createFlow(__db, false, arrayOf("districts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfDistrictNumber: Int = getColumnIndexOrThrow(_stmt, "districtNumber")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfRepresentativeName: Int = getColumnIndexOrThrow(_stmt, "representativeName")
        val _columnIndexOfRepresentativeParty: Int = getColumnIndexOrThrow(_stmt, "representativeParty")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: DistrictEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpLevel: DistrictLevel
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfLevel)
          _tmpLevel = __civicConverters.toDistrictLevel(_tmp)
          val _tmpState: String
          _tmpState = _stmt.getText(_columnIndexOfState)
          val _tmpDistrictNumber: Int?
          if (_stmt.isNull(_columnIndexOfDistrictNumber)) {
            _tmpDistrictNumber = null
          } else {
            _tmpDistrictNumber = _stmt.getLong(_columnIndexOfDistrictNumber).toInt()
          }
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpRepresentativeName: String?
          if (_stmt.isNull(_columnIndexOfRepresentativeName)) {
            _tmpRepresentativeName = null
          } else {
            _tmpRepresentativeName = _stmt.getText(_columnIndexOfRepresentativeName)
          }
          val _tmpRepresentativeParty: String?
          if (_stmt.isNull(_columnIndexOfRepresentativeParty)) {
            _tmpRepresentativeParty = null
          } else {
            _tmpRepresentativeParty = _stmt.getText(_columnIndexOfRepresentativeParty)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _result = DistrictEntity(_tmpId,_tmpLevel,_tmpState,_tmpDistrictNumber,_tmpDisplayName,_tmpRepresentativeName,_tmpRepresentativeParty,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDistrict(districtId: String): DistrictEntity? {
    val _sql: String = "SELECT * FROM districts WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfDistrictNumber: Int = getColumnIndexOrThrow(_stmt, "districtNumber")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfRepresentativeName: Int = getColumnIndexOrThrow(_stmt, "representativeName")
        val _columnIndexOfRepresentativeParty: Int = getColumnIndexOrThrow(_stmt, "representativeParty")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: DistrictEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpLevel: DistrictLevel
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfLevel)
          _tmpLevel = __civicConverters.toDistrictLevel(_tmp)
          val _tmpState: String
          _tmpState = _stmt.getText(_columnIndexOfState)
          val _tmpDistrictNumber: Int?
          if (_stmt.isNull(_columnIndexOfDistrictNumber)) {
            _tmpDistrictNumber = null
          } else {
            _tmpDistrictNumber = _stmt.getLong(_columnIndexOfDistrictNumber).toInt()
          }
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpRepresentativeName: String?
          if (_stmt.isNull(_columnIndexOfRepresentativeName)) {
            _tmpRepresentativeName = null
          } else {
            _tmpRepresentativeName = _stmt.getText(_columnIndexOfRepresentativeName)
          }
          val _tmpRepresentativeParty: String?
          if (_stmt.isNull(_columnIndexOfRepresentativeParty)) {
            _tmpRepresentativeParty = null
          } else {
            _tmpRepresentativeParty = _stmt.getText(_columnIndexOfRepresentativeParty)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _result = DistrictEntity(_tmpId,_tmpLevel,_tmpState,_tmpDistrictNumber,_tmpDisplayName,_tmpRepresentativeName,_tmpRepresentativeParty,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
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
