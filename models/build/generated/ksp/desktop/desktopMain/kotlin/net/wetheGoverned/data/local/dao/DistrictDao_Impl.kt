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
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow
import net.wetheGoverned.`data`.local.CivicConverters
import net.wetheGoverned.`data`.local.entity.DistrictEntity
import net.wetheGoverned.model.DistrictLevel

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class DistrictDao_Impl(
  __db: RoomDatabase,
) : DistrictDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfDistrictEntity: EntityUpsertAdapter<DistrictEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfDistrictEntity = EntityUpsertAdapter<DistrictEntity>(object :
        EntityInsertAdapter<DistrictEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `districts` (`id`,`level`,`state`,`districtNumber`,`displayName`,`representativeName`,`representativeParty`,`geoBoundaries`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?)"

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
        val _tmpGeoBoundaries: String? = entity.geoBoundaries
        if (_tmpGeoBoundaries == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpGeoBoundaries)
        }
        statement.bindLong(9, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<DistrictEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `districts` SET `id` = ?,`level` = ?,`state` = ?,`districtNumber` = ?,`displayName` = ?,`representativeName` = ?,`representativeParty` = ?,`geoBoundaries` = ?,`cachedAt` = ? WHERE `id` = ?"

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
        val _tmpGeoBoundaries: String? = entity.geoBoundaries
        if (_tmpGeoBoundaries == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpGeoBoundaries)
        }
        statement.bindLong(9, entity.cachedAt)
        statement.bindText(10, entity.id)
      }
    })
  }

  public override suspend fun upsertDistrict(district: DistrictEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfDistrictEntity.upsert(_connection, district)
  }

  public override fun observeDistrict(districtId: String): Flow<DistrictEntity?> {
    val _sql: String = "SELECT * FROM districts WHERE id = ?"
    return createFlow(__db, false, arrayOf("districts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _cursorIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _cursorIndexOfDistrictNumber: Int = getColumnIndexOrThrow(_stmt, "districtNumber")
        val _cursorIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _cursorIndexOfRepresentativeName: Int = getColumnIndexOrThrow(_stmt,
            "representativeName")
        val _cursorIndexOfRepresentativeParty: Int = getColumnIndexOrThrow(_stmt,
            "representativeParty")
        val _cursorIndexOfGeoBoundaries: Int = getColumnIndexOrThrow(_stmt, "geoBoundaries")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: DistrictEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpLevel: DistrictLevel
          val _tmp: String
          _tmp = _stmt.getText(_cursorIndexOfLevel)
          _tmpLevel = __civicConverters.toDistrictLevel(_tmp)
          val _tmpState: String
          _tmpState = _stmt.getText(_cursorIndexOfState)
          val _tmpDistrictNumber: Int?
          if (_stmt.isNull(_cursorIndexOfDistrictNumber)) {
            _tmpDistrictNumber = null
          } else {
            _tmpDistrictNumber = _stmt.getLong(_cursorIndexOfDistrictNumber).toInt()
          }
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_cursorIndexOfDisplayName)
          val _tmpRepresentativeName: String?
          if (_stmt.isNull(_cursorIndexOfRepresentativeName)) {
            _tmpRepresentativeName = null
          } else {
            _tmpRepresentativeName = _stmt.getText(_cursorIndexOfRepresentativeName)
          }
          val _tmpRepresentativeParty: String?
          if (_stmt.isNull(_cursorIndexOfRepresentativeParty)) {
            _tmpRepresentativeParty = null
          } else {
            _tmpRepresentativeParty = _stmt.getText(_cursorIndexOfRepresentativeParty)
          }
          val _tmpGeoBoundaries: String?
          if (_stmt.isNull(_cursorIndexOfGeoBoundaries)) {
            _tmpGeoBoundaries = null
          } else {
            _tmpGeoBoundaries = _stmt.getText(_cursorIndexOfGeoBoundaries)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _result =
              DistrictEntity(_tmpId,_tmpLevel,_tmpState,_tmpDistrictNumber,_tmpDisplayName,_tmpRepresentativeName,_tmpRepresentativeParty,_tmpGeoBoundaries,_tmpCachedAt)
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
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _cursorIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _cursorIndexOfDistrictNumber: Int = getColumnIndexOrThrow(_stmt, "districtNumber")
        val _cursorIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _cursorIndexOfRepresentativeName: Int = getColumnIndexOrThrow(_stmt,
            "representativeName")
        val _cursorIndexOfRepresentativeParty: Int = getColumnIndexOrThrow(_stmt,
            "representativeParty")
        val _cursorIndexOfGeoBoundaries: Int = getColumnIndexOrThrow(_stmt, "geoBoundaries")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: DistrictEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpLevel: DistrictLevel
          val _tmp: String
          _tmp = _stmt.getText(_cursorIndexOfLevel)
          _tmpLevel = __civicConverters.toDistrictLevel(_tmp)
          val _tmpState: String
          _tmpState = _stmt.getText(_cursorIndexOfState)
          val _tmpDistrictNumber: Int?
          if (_stmt.isNull(_cursorIndexOfDistrictNumber)) {
            _tmpDistrictNumber = null
          } else {
            _tmpDistrictNumber = _stmt.getLong(_cursorIndexOfDistrictNumber).toInt()
          }
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_cursorIndexOfDisplayName)
          val _tmpRepresentativeName: String?
          if (_stmt.isNull(_cursorIndexOfRepresentativeName)) {
            _tmpRepresentativeName = null
          } else {
            _tmpRepresentativeName = _stmt.getText(_cursorIndexOfRepresentativeName)
          }
          val _tmpRepresentativeParty: String?
          if (_stmt.isNull(_cursorIndexOfRepresentativeParty)) {
            _tmpRepresentativeParty = null
          } else {
            _tmpRepresentativeParty = _stmt.getText(_cursorIndexOfRepresentativeParty)
          }
          val _tmpGeoBoundaries: String?
          if (_stmt.isNull(_cursorIndexOfGeoBoundaries)) {
            _tmpGeoBoundaries = null
          } else {
            _tmpGeoBoundaries = _stmt.getText(_cursorIndexOfGeoBoundaries)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _result =
              DistrictEntity(_tmpId,_tmpLevel,_tmpState,_tmpDistrictNumber,_tmpDisplayName,_tmpRepresentativeName,_tmpRepresentativeParty,_tmpGeoBoundaries,_tmpCachedAt)
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
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
