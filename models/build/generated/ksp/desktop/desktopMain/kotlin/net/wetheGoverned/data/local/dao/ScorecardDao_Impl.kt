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
import net.wetheGoverned.`data`.local.entity.RepresentativeScorecardEntity
import net.wetheGoverned.`data`.local.entity.ScorecardCategoryEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ScorecardDao_Impl(
  __db: RoomDatabase,
) : ScorecardDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfScorecardCategoryEntity: EntityInsertAdapter<ScorecardCategoryEntity>

  private val __upsertAdapterOfRepresentativeScorecardEntity:
      EntityUpsertAdapter<RepresentativeScorecardEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfScorecardCategoryEntity = object :
        EntityInsertAdapter<ScorecardCategoryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `scorecard_categories` (`rowId`,`districtId`,`categoryName`,`officialValue`,`residentReportedValue`,`score`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ScorecardCategoryEntity) {
        statement.bindLong(1, entity.rowId)
        statement.bindText(2, entity.districtId)
        statement.bindText(3, entity.categoryName)
        statement.bindText(4, entity.officialValue)
        val _tmpResidentReportedValue: String? = entity.residentReportedValue
        if (_tmpResidentReportedValue == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpResidentReportedValue)
        }
        statement.bindLong(6, entity.score.toLong())
      }
    }
    this.__upsertAdapterOfRepresentativeScorecardEntity =
        EntityUpsertAdapter<RepresentativeScorecardEntity>(object :
        EntityInsertAdapter<RepresentativeScorecardEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `representative_scorecards` (`districtId`,`representativePubKey`,`name`,`party`,`overallScore`,`lastUpdated`,`cachedAt`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement,
          entity: RepresentativeScorecardEntity) {
        statement.bindText(1, entity.districtId)
        statement.bindText(2, entity.representativePubKey)
        statement.bindText(3, entity.name)
        statement.bindText(4, entity.party)
        statement.bindLong(5, entity.overallScore.toLong())
        statement.bindLong(6, entity.lastUpdated)
        statement.bindLong(7, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<RepresentativeScorecardEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `representative_scorecards` SET `districtId` = ?,`representativePubKey` = ?,`name` = ?,`party` = ?,`overallScore` = ?,`lastUpdated` = ?,`cachedAt` = ? WHERE `districtId` = ?"

      protected override fun bind(statement: SQLiteStatement,
          entity: RepresentativeScorecardEntity) {
        statement.bindText(1, entity.districtId)
        statement.bindText(2, entity.representativePubKey)
        statement.bindText(3, entity.name)
        statement.bindText(4, entity.party)
        statement.bindLong(5, entity.overallScore.toLong())
        statement.bindLong(6, entity.lastUpdated)
        statement.bindLong(7, entity.cachedAt)
        statement.bindText(8, entity.districtId)
      }
    })
  }

  public override suspend fun upsertCategories(categories: List<ScorecardCategoryEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfScorecardCategoryEntity.insert(_connection, categories)
  }

  public override suspend fun upsertScorecard(scorecard: RepresentativeScorecardEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfRepresentativeScorecardEntity.upsert(_connection, scorecard)
  }

  public override fun observeScorecard(districtId: String): Flow<RepresentativeScorecardEntity?> {
    val _sql: String = "SELECT * FROM representative_scorecards WHERE districtId = ?"
    return createFlow(__db, false, arrayOf("representative_scorecards")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfRepresentativePubKey: Int = getColumnIndexOrThrow(_stmt,
            "representativePubKey")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfParty: Int = getColumnIndexOrThrow(_stmt, "party")
        val _cursorIndexOfOverallScore: Int = getColumnIndexOrThrow(_stmt, "overallScore")
        val _cursorIndexOfLastUpdated: Int = getColumnIndexOrThrow(_stmt, "lastUpdated")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: RepresentativeScorecardEntity?
        if (_stmt.step()) {
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpRepresentativePubKey: String
          _tmpRepresentativePubKey = _stmt.getText(_cursorIndexOfRepresentativePubKey)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpParty: String
          _tmpParty = _stmt.getText(_cursorIndexOfParty)
          val _tmpOverallScore: Int
          _tmpOverallScore = _stmt.getLong(_cursorIndexOfOverallScore).toInt()
          val _tmpLastUpdated: Long
          _tmpLastUpdated = _stmt.getLong(_cursorIndexOfLastUpdated)
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _result =
              RepresentativeScorecardEntity(_tmpDistrictId,_tmpRepresentativePubKey,_tmpName,_tmpParty,_tmpOverallScore,_tmpLastUpdated,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getScorecard(districtId: String): RepresentativeScorecardEntity? {
    val _sql: String = "SELECT * FROM representative_scorecards WHERE districtId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfRepresentativePubKey: Int = getColumnIndexOrThrow(_stmt,
            "representativePubKey")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfParty: Int = getColumnIndexOrThrow(_stmt, "party")
        val _cursorIndexOfOverallScore: Int = getColumnIndexOrThrow(_stmt, "overallScore")
        val _cursorIndexOfLastUpdated: Int = getColumnIndexOrThrow(_stmt, "lastUpdated")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: RepresentativeScorecardEntity?
        if (_stmt.step()) {
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpRepresentativePubKey: String
          _tmpRepresentativePubKey = _stmt.getText(_cursorIndexOfRepresentativePubKey)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpParty: String
          _tmpParty = _stmt.getText(_cursorIndexOfParty)
          val _tmpOverallScore: Int
          _tmpOverallScore = _stmt.getLong(_cursorIndexOfOverallScore).toInt()
          val _tmpLastUpdated: Long
          _tmpLastUpdated = _stmt.getLong(_cursorIndexOfLastUpdated)
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _result =
              RepresentativeScorecardEntity(_tmpDistrictId,_tmpRepresentativePubKey,_tmpName,_tmpParty,_tmpOverallScore,_tmpLastUpdated,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCategoriesForDistrict(districtId: String):
      List<ScorecardCategoryEntity> {
    val _sql: String = "SELECT * FROM scorecard_categories WHERE districtId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _cursorIndexOfRowId: Int = getColumnIndexOrThrow(_stmt, "rowId")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "categoryName")
        val _cursorIndexOfOfficialValue: Int = getColumnIndexOrThrow(_stmt, "officialValue")
        val _cursorIndexOfResidentReportedValue: Int = getColumnIndexOrThrow(_stmt,
            "residentReportedValue")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _result: MutableList<ScorecardCategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ScorecardCategoryEntity
          val _tmpRowId: Long
          _tmpRowId = _stmt.getLong(_cursorIndexOfRowId)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpCategoryName: String
          _tmpCategoryName = _stmt.getText(_cursorIndexOfCategoryName)
          val _tmpOfficialValue: String
          _tmpOfficialValue = _stmt.getText(_cursorIndexOfOfficialValue)
          val _tmpResidentReportedValue: String?
          if (_stmt.isNull(_cursorIndexOfResidentReportedValue)) {
            _tmpResidentReportedValue = null
          } else {
            _tmpResidentReportedValue = _stmt.getText(_cursorIndexOfResidentReportedValue)
          }
          val _tmpScore: Int
          _tmpScore = _stmt.getLong(_cursorIndexOfScore).toInt()
          _item =
              ScorecardCategoryEntity(_tmpRowId,_tmpDistrictId,_tmpCategoryName,_tmpOfficialValue,_tmpResidentReportedValue,_tmpScore)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteCategoriesForDistrict(districtId: String) {
    val _sql: String = "DELETE FROM scorecard_categories WHERE districtId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
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
