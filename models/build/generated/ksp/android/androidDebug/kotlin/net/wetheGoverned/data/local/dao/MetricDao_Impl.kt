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
import net.wetheGoverned.`data`.local.entity.DistrictMetricEntity
import net.wetheGoverned.model.MetricSource

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class MetricDao_Impl(
  __db: RoomDatabase,
) : MetricDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDistrictMetricEntity: EntityInsertAdapter<DistrictMetricEntity>

  private val __civicConverters: CivicConverters = CivicConverters()

  private val __upsertAdapterOfDistrictMetricEntity: EntityUpsertAdapter<DistrictMetricEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDistrictMetricEntity = object :
        EntityInsertAdapter<DistrictMetricEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `district_metrics` (`id`,`districtId`,`category`,`name`,`officialValue`,`residentValue`,`unit`,`source`,`reportedAt`,`reporterPubKey`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DistrictMetricEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.districtId)
        statement.bindText(3, entity.category)
        statement.bindText(4, entity.name)
        statement.bindText(5, entity.officialValue)
        val _tmpResidentValue: String? = entity.residentValue
        if (_tmpResidentValue == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpResidentValue)
        }
        statement.bindText(7, entity.unit)
        val _tmp: String = __civicConverters.fromMetricSource(entity.source)
        statement.bindText(8, _tmp)
        statement.bindLong(9, entity.reportedAt)
        val _tmpReporterPubKey: String? = entity.reporterPubKey
        if (_tmpReporterPubKey == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpReporterPubKey)
        }
        statement.bindLong(11, entity.cachedAt)
      }
    }
    this.__upsertAdapterOfDistrictMetricEntity = EntityUpsertAdapter<DistrictMetricEntity>(object :
        EntityInsertAdapter<DistrictMetricEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `district_metrics` (`id`,`districtId`,`category`,`name`,`officialValue`,`residentValue`,`unit`,`source`,`reportedAt`,`reporterPubKey`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DistrictMetricEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.districtId)
        statement.bindText(3, entity.category)
        statement.bindText(4, entity.name)
        statement.bindText(5, entity.officialValue)
        val _tmpResidentValue: String? = entity.residentValue
        if (_tmpResidentValue == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpResidentValue)
        }
        statement.bindText(7, entity.unit)
        val _tmp: String = __civicConverters.fromMetricSource(entity.source)
        statement.bindText(8, _tmp)
        statement.bindLong(9, entity.reportedAt)
        val _tmpReporterPubKey: String? = entity.reporterPubKey
        if (_tmpReporterPubKey == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpReporterPubKey)
        }
        statement.bindLong(11, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<DistrictMetricEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `district_metrics` SET `id` = ?,`districtId` = ?,`category` = ?,`name` = ?,`officialValue` = ?,`residentValue` = ?,`unit` = ?,`source` = ?,`reportedAt` = ?,`reporterPubKey` = ?,`cachedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DistrictMetricEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.districtId)
        statement.bindText(3, entity.category)
        statement.bindText(4, entity.name)
        statement.bindText(5, entity.officialValue)
        val _tmpResidentValue: String? = entity.residentValue
        if (_tmpResidentValue == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpResidentValue)
        }
        statement.bindText(7, entity.unit)
        val _tmp: String = __civicConverters.fromMetricSource(entity.source)
        statement.bindText(8, _tmp)
        statement.bindLong(9, entity.reportedAt)
        val _tmpReporterPubKey: String? = entity.reporterPubKey
        if (_tmpReporterPubKey == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpReporterPubKey)
        }
        statement.bindLong(11, entity.cachedAt)
        statement.bindText(12, entity.id)
      }
    })
  }

  public override suspend fun upsertMetrics(metrics: List<DistrictMetricEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfDistrictMetricEntity.insert(_connection, metrics)
  }

  public override suspend fun upsertMetric(metric: DistrictMetricEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfDistrictMetricEntity.upsert(_connection, metric)
  }

  public override fun observeMetrics(districtId: String): Flow<List<DistrictMetricEntity>> {
    val _sql: String =
        "SELECT * FROM district_metrics WHERE districtId = ? ORDER BY reportedAt DESC"
    return createFlow(__db, false, arrayOf("district_metrics")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfOfficialValue: Int = getColumnIndexOrThrow(_stmt, "officialValue")
        val _columnIndexOfResidentValue: Int = getColumnIndexOrThrow(_stmt, "residentValue")
        val _columnIndexOfUnit: Int = getColumnIndexOrThrow(_stmt, "unit")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfReportedAt: Int = getColumnIndexOrThrow(_stmt, "reportedAt")
        val _columnIndexOfReporterPubKey: Int = getColumnIndexOrThrow(_stmt, "reporterPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<DistrictMetricEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DistrictMetricEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpOfficialValue: String
          _tmpOfficialValue = _stmt.getText(_columnIndexOfOfficialValue)
          val _tmpResidentValue: String?
          if (_stmt.isNull(_columnIndexOfResidentValue)) {
            _tmpResidentValue = null
          } else {
            _tmpResidentValue = _stmt.getText(_columnIndexOfResidentValue)
          }
          val _tmpUnit: String
          _tmpUnit = _stmt.getText(_columnIndexOfUnit)
          val _tmpSource: MetricSource
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfSource)
          _tmpSource = __civicConverters.toMetricSource(_tmp)
          val _tmpReportedAt: Long
          _tmpReportedAt = _stmt.getLong(_columnIndexOfReportedAt)
          val _tmpReporterPubKey: String?
          if (_stmt.isNull(_columnIndexOfReporterPubKey)) {
            _tmpReporterPubKey = null
          } else {
            _tmpReporterPubKey = _stmt.getText(_columnIndexOfReporterPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item =
              DistrictMetricEntity(_tmpId,_tmpDistrictId,_tmpCategory,_tmpName,_tmpOfficialValue,_tmpResidentValue,_tmpUnit,_tmpSource,_tmpReportedAt,_tmpReporterPubKey,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun evictStaleMetrics(districtId: String, before: Long) {
    val _sql: String = "DELETE FROM district_metrics WHERE districtId = ? AND cachedAt < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, before)
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
