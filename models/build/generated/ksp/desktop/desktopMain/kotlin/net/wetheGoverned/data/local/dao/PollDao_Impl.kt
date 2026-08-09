package net.wetheGoverned.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
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
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow
import net.wetheGoverned.`data`.local.CivicConverters
import net.wetheGoverned.`data`.local.entity.DistrictPollEntity
import net.wetheGoverned.model.PollScope
import net.wetheGoverned.model.PollStatus

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PollDao_Impl(
  __db: RoomDatabase,
) : PollDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfDistrictPollEntity: EntityUpsertAdapter<DistrictPollEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfDistrictPollEntity = EntityUpsertAdapter<DistrictPollEntity>(object :
        EntityInsertAdapter<DistrictPollEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `district_polls` (`id`,`scope`,`districtId`,`localId`,`authorPubKey`,`question`,`optionsJson`,`status`,`createdAt`,`closesAt`,`totalVotes`,`importanceScore`,`userImportanceVote`,`residentVoteOption`,`linkedLegislationId`,`districtBreakdownJson`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DistrictPollEntity) {
        statement.bindText(1, entity.id)
        val _tmpScope: PollScope? = entity.scope
        val _tmp: String?
        if (_tmpScope == null) {
          _tmp = null
        } else {
          _tmp = __civicConverters.fromPollScope(_tmpScope)
        }
        if (_tmp == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmp)
        }
        statement.bindText(3, entity.districtId)
        val _tmpLocalId: String? = entity.localId
        if (_tmpLocalId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpLocalId)
        }
        statement.bindText(5, entity.authorPubKey)
        statement.bindText(6, entity.question)
        statement.bindText(7, entity.optionsJson)
        val _tmp_1: String = __civicConverters.fromPollStatus(entity.status)
        statement.bindText(8, _tmp_1)
        statement.bindLong(9, entity.createdAt)
        val _tmpClosesAt: Long? = entity.closesAt
        if (_tmpClosesAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpClosesAt)
        }
        statement.bindLong(11, entity.totalVotes.toLong())
        statement.bindLong(12, entity.importanceScore.toLong())
        statement.bindLong(13, entity.userImportanceVote.toLong())
        val _tmpResidentVoteOption: String? = entity.residentVoteOption
        if (_tmpResidentVoteOption == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpResidentVoteOption)
        }
        val _tmpLinkedLegislationId: String? = entity.linkedLegislationId
        if (_tmpLinkedLegislationId == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpLinkedLegislationId)
        }
        val _tmpDistrictBreakdownJson: String? = entity.districtBreakdownJson
        if (_tmpDistrictBreakdownJson == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpDistrictBreakdownJson)
        }
        statement.bindLong(17, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<DistrictPollEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `district_polls` SET `id` = ?,`scope` = ?,`districtId` = ?,`localId` = ?,`authorPubKey` = ?,`question` = ?,`optionsJson` = ?,`status` = ?,`createdAt` = ?,`closesAt` = ?,`totalVotes` = ?,`importanceScore` = ?,`userImportanceVote` = ?,`residentVoteOption` = ?,`linkedLegislationId` = ?,`districtBreakdownJson` = ?,`cachedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DistrictPollEntity) {
        statement.bindText(1, entity.id)
        val _tmpScope: PollScope? = entity.scope
        val _tmp: String?
        if (_tmpScope == null) {
          _tmp = null
        } else {
          _tmp = __civicConverters.fromPollScope(_tmpScope)
        }
        if (_tmp == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmp)
        }
        statement.bindText(3, entity.districtId)
        val _tmpLocalId: String? = entity.localId
        if (_tmpLocalId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpLocalId)
        }
        statement.bindText(5, entity.authorPubKey)
        statement.bindText(6, entity.question)
        statement.bindText(7, entity.optionsJson)
        val _tmp_1: String = __civicConverters.fromPollStatus(entity.status)
        statement.bindText(8, _tmp_1)
        statement.bindLong(9, entity.createdAt)
        val _tmpClosesAt: Long? = entity.closesAt
        if (_tmpClosesAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpClosesAt)
        }
        statement.bindLong(11, entity.totalVotes.toLong())
        statement.bindLong(12, entity.importanceScore.toLong())
        statement.bindLong(13, entity.userImportanceVote.toLong())
        val _tmpResidentVoteOption: String? = entity.residentVoteOption
        if (_tmpResidentVoteOption == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpResidentVoteOption)
        }
        val _tmpLinkedLegislationId: String? = entity.linkedLegislationId
        if (_tmpLinkedLegislationId == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpLinkedLegislationId)
        }
        val _tmpDistrictBreakdownJson: String? = entity.districtBreakdownJson
        if (_tmpDistrictBreakdownJson == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpDistrictBreakdownJson)
        }
        statement.bindLong(17, entity.cachedAt)
        statement.bindText(18, entity.id)
      }
    })
  }

  public override suspend fun upsertPoll(poll: DistrictPollEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfDistrictPollEntity.upsert(_connection, poll)
  }

  public override fun observePolls(districtId: String): Flow<List<DistrictPollEntity>> {
    val _sql: String =
        "SELECT * FROM district_polls WHERE districtId = ? ORDER BY importanceScore DESC, createdAt DESC"
    return createFlow(__db, false, arrayOf("district_polls")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _cursorIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _cursorIndexOfQuestion: Int = getColumnIndexOrThrow(_stmt, "question")
        val _cursorIndexOfOptionsJson: Int = getColumnIndexOrThrow(_stmt, "optionsJson")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _cursorIndexOfClosesAt: Int = getColumnIndexOrThrow(_stmt, "closesAt")
        val _cursorIndexOfTotalVotes: Int = getColumnIndexOrThrow(_stmt, "totalVotes")
        val _cursorIndexOfImportanceScore: Int = getColumnIndexOrThrow(_stmt, "importanceScore")
        val _cursorIndexOfUserImportanceVote: Int = getColumnIndexOrThrow(_stmt,
            "userImportanceVote")
        val _cursorIndexOfResidentVoteOption: Int = getColumnIndexOrThrow(_stmt,
            "residentVoteOption")
        val _cursorIndexOfLinkedLegislationId: Int = getColumnIndexOrThrow(_stmt,
            "linkedLegislationId")
        val _cursorIndexOfDistrictBreakdownJson: Int = getColumnIndexOrThrow(_stmt,
            "districtBreakdownJson")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<DistrictPollEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DistrictPollEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpScope: PollScope?
          val _tmp: String?
          if (_stmt.isNull(_cursorIndexOfScope)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_cursorIndexOfScope)
          }
          if (_tmp == null) {
            _tmpScope = null
          } else {
            _tmpScope = __civicConverters.toPollScope(_tmp)
          }
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpLocalId: String?
          if (_stmt.isNull(_cursorIndexOfLocalId)) {
            _tmpLocalId = null
          } else {
            _tmpLocalId = _stmt.getText(_cursorIndexOfLocalId)
          }
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_cursorIndexOfAuthorPubKey)
          val _tmpQuestion: String
          _tmpQuestion = _stmt.getText(_cursorIndexOfQuestion)
          val _tmpOptionsJson: String
          _tmpOptionsJson = _stmt.getText(_cursorIndexOfOptionsJson)
          val _tmpStatus: PollStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toPollStatus(_tmp_1)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          val _tmpClosesAt: Long?
          if (_stmt.isNull(_cursorIndexOfClosesAt)) {
            _tmpClosesAt = null
          } else {
            _tmpClosesAt = _stmt.getLong(_cursorIndexOfClosesAt)
          }
          val _tmpTotalVotes: Int
          _tmpTotalVotes = _stmt.getLong(_cursorIndexOfTotalVotes).toInt()
          val _tmpImportanceScore: Int
          _tmpImportanceScore = _stmt.getLong(_cursorIndexOfImportanceScore).toInt()
          val _tmpUserImportanceVote: Int
          _tmpUserImportanceVote = _stmt.getLong(_cursorIndexOfUserImportanceVote).toInt()
          val _tmpResidentVoteOption: String?
          if (_stmt.isNull(_cursorIndexOfResidentVoteOption)) {
            _tmpResidentVoteOption = null
          } else {
            _tmpResidentVoteOption = _stmt.getText(_cursorIndexOfResidentVoteOption)
          }
          val _tmpLinkedLegislationId: String?
          if (_stmt.isNull(_cursorIndexOfLinkedLegislationId)) {
            _tmpLinkedLegislationId = null
          } else {
            _tmpLinkedLegislationId = _stmt.getText(_cursorIndexOfLinkedLegislationId)
          }
          val _tmpDistrictBreakdownJson: String?
          if (_stmt.isNull(_cursorIndexOfDistrictBreakdownJson)) {
            _tmpDistrictBreakdownJson = null
          } else {
            _tmpDistrictBreakdownJson = _stmt.getText(_cursorIndexOfDistrictBreakdownJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item =
              DistrictPollEntity(_tmpId,_tmpScope,_tmpDistrictId,_tmpLocalId,_tmpAuthorPubKey,_tmpQuestion,_tmpOptionsJson,_tmpStatus,_tmpCreatedAt,_tmpClosesAt,_tmpTotalVotes,_tmpImportanceScore,_tmpUserImportanceVote,_tmpResidentVoteOption,_tmpLinkedLegislationId,_tmpDistrictBreakdownJson,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observePollsHierarchy(districtId: String, stateId: String):
      Flow<List<DistrictPollEntity>> {
    val _sql: String =
        "SELECT * FROM district_polls WHERE districtId = ? OR districtId = ? OR districtId = 'us' ORDER BY importanceScore DESC, createdAt DESC"
    return createFlow(__db, false, arrayOf("district_polls")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        _argIndex = 2
        _stmt.bindText(_argIndex, stateId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _cursorIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _cursorIndexOfQuestion: Int = getColumnIndexOrThrow(_stmt, "question")
        val _cursorIndexOfOptionsJson: Int = getColumnIndexOrThrow(_stmt, "optionsJson")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _cursorIndexOfClosesAt: Int = getColumnIndexOrThrow(_stmt, "closesAt")
        val _cursorIndexOfTotalVotes: Int = getColumnIndexOrThrow(_stmt, "totalVotes")
        val _cursorIndexOfImportanceScore: Int = getColumnIndexOrThrow(_stmt, "importanceScore")
        val _cursorIndexOfUserImportanceVote: Int = getColumnIndexOrThrow(_stmt,
            "userImportanceVote")
        val _cursorIndexOfResidentVoteOption: Int = getColumnIndexOrThrow(_stmt,
            "residentVoteOption")
        val _cursorIndexOfLinkedLegislationId: Int = getColumnIndexOrThrow(_stmt,
            "linkedLegislationId")
        val _cursorIndexOfDistrictBreakdownJson: Int = getColumnIndexOrThrow(_stmt,
            "districtBreakdownJson")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<DistrictPollEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DistrictPollEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpScope: PollScope?
          val _tmp: String?
          if (_stmt.isNull(_cursorIndexOfScope)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_cursorIndexOfScope)
          }
          if (_tmp == null) {
            _tmpScope = null
          } else {
            _tmpScope = __civicConverters.toPollScope(_tmp)
          }
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpLocalId: String?
          if (_stmt.isNull(_cursorIndexOfLocalId)) {
            _tmpLocalId = null
          } else {
            _tmpLocalId = _stmt.getText(_cursorIndexOfLocalId)
          }
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_cursorIndexOfAuthorPubKey)
          val _tmpQuestion: String
          _tmpQuestion = _stmt.getText(_cursorIndexOfQuestion)
          val _tmpOptionsJson: String
          _tmpOptionsJson = _stmt.getText(_cursorIndexOfOptionsJson)
          val _tmpStatus: PollStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toPollStatus(_tmp_1)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          val _tmpClosesAt: Long?
          if (_stmt.isNull(_cursorIndexOfClosesAt)) {
            _tmpClosesAt = null
          } else {
            _tmpClosesAt = _stmt.getLong(_cursorIndexOfClosesAt)
          }
          val _tmpTotalVotes: Int
          _tmpTotalVotes = _stmt.getLong(_cursorIndexOfTotalVotes).toInt()
          val _tmpImportanceScore: Int
          _tmpImportanceScore = _stmt.getLong(_cursorIndexOfImportanceScore).toInt()
          val _tmpUserImportanceVote: Int
          _tmpUserImportanceVote = _stmt.getLong(_cursorIndexOfUserImportanceVote).toInt()
          val _tmpResidentVoteOption: String?
          if (_stmt.isNull(_cursorIndexOfResidentVoteOption)) {
            _tmpResidentVoteOption = null
          } else {
            _tmpResidentVoteOption = _stmt.getText(_cursorIndexOfResidentVoteOption)
          }
          val _tmpLinkedLegislationId: String?
          if (_stmt.isNull(_cursorIndexOfLinkedLegislationId)) {
            _tmpLinkedLegislationId = null
          } else {
            _tmpLinkedLegislationId = _stmt.getText(_cursorIndexOfLinkedLegislationId)
          }
          val _tmpDistrictBreakdownJson: String?
          if (_stmt.isNull(_cursorIndexOfDistrictBreakdownJson)) {
            _tmpDistrictBreakdownJson = null
          } else {
            _tmpDistrictBreakdownJson = _stmt.getText(_cursorIndexOfDistrictBreakdownJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item =
              DistrictPollEntity(_tmpId,_tmpScope,_tmpDistrictId,_tmpLocalId,_tmpAuthorPubKey,_tmpQuestion,_tmpOptionsJson,_tmpStatus,_tmpCreatedAt,_tmpClosesAt,_tmpTotalVotes,_tmpImportanceScore,_tmpUserImportanceVote,_tmpResidentVoteOption,_tmpLinkedLegislationId,_tmpDistrictBreakdownJson,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observePollsByIds(districtIds: List<String>): Flow<List<DistrictPollEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM district_polls WHERE districtId IN (")
    val _inputSize: Int = districtIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") ORDER BY importanceScore DESC, createdAt DESC")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("district_polls")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in districtIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _cursorIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _cursorIndexOfQuestion: Int = getColumnIndexOrThrow(_stmt, "question")
        val _cursorIndexOfOptionsJson: Int = getColumnIndexOrThrow(_stmt, "optionsJson")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _cursorIndexOfClosesAt: Int = getColumnIndexOrThrow(_stmt, "closesAt")
        val _cursorIndexOfTotalVotes: Int = getColumnIndexOrThrow(_stmt, "totalVotes")
        val _cursorIndexOfImportanceScore: Int = getColumnIndexOrThrow(_stmt, "importanceScore")
        val _cursorIndexOfUserImportanceVote: Int = getColumnIndexOrThrow(_stmt,
            "userImportanceVote")
        val _cursorIndexOfResidentVoteOption: Int = getColumnIndexOrThrow(_stmt,
            "residentVoteOption")
        val _cursorIndexOfLinkedLegislationId: Int = getColumnIndexOrThrow(_stmt,
            "linkedLegislationId")
        val _cursorIndexOfDistrictBreakdownJson: Int = getColumnIndexOrThrow(_stmt,
            "districtBreakdownJson")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<DistrictPollEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: DistrictPollEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpScope: PollScope?
          val _tmp: String?
          if (_stmt.isNull(_cursorIndexOfScope)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_cursorIndexOfScope)
          }
          if (_tmp == null) {
            _tmpScope = null
          } else {
            _tmpScope = __civicConverters.toPollScope(_tmp)
          }
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpLocalId: String?
          if (_stmt.isNull(_cursorIndexOfLocalId)) {
            _tmpLocalId = null
          } else {
            _tmpLocalId = _stmt.getText(_cursorIndexOfLocalId)
          }
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_cursorIndexOfAuthorPubKey)
          val _tmpQuestion: String
          _tmpQuestion = _stmt.getText(_cursorIndexOfQuestion)
          val _tmpOptionsJson: String
          _tmpOptionsJson = _stmt.getText(_cursorIndexOfOptionsJson)
          val _tmpStatus: PollStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toPollStatus(_tmp_1)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          val _tmpClosesAt: Long?
          if (_stmt.isNull(_cursorIndexOfClosesAt)) {
            _tmpClosesAt = null
          } else {
            _tmpClosesAt = _stmt.getLong(_cursorIndexOfClosesAt)
          }
          val _tmpTotalVotes: Int
          _tmpTotalVotes = _stmt.getLong(_cursorIndexOfTotalVotes).toInt()
          val _tmpImportanceScore: Int
          _tmpImportanceScore = _stmt.getLong(_cursorIndexOfImportanceScore).toInt()
          val _tmpUserImportanceVote: Int
          _tmpUserImportanceVote = _stmt.getLong(_cursorIndexOfUserImportanceVote).toInt()
          val _tmpResidentVoteOption: String?
          if (_stmt.isNull(_cursorIndexOfResidentVoteOption)) {
            _tmpResidentVoteOption = null
          } else {
            _tmpResidentVoteOption = _stmt.getText(_cursorIndexOfResidentVoteOption)
          }
          val _tmpLinkedLegislationId: String?
          if (_stmt.isNull(_cursorIndexOfLinkedLegislationId)) {
            _tmpLinkedLegislationId = null
          } else {
            _tmpLinkedLegislationId = _stmt.getText(_cursorIndexOfLinkedLegislationId)
          }
          val _tmpDistrictBreakdownJson: String?
          if (_stmt.isNull(_cursorIndexOfDistrictBreakdownJson)) {
            _tmpDistrictBreakdownJson = null
          } else {
            _tmpDistrictBreakdownJson = _stmt.getText(_cursorIndexOfDistrictBreakdownJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item_1 =
              DistrictPollEntity(_tmpId,_tmpScope,_tmpDistrictId,_tmpLocalId,_tmpAuthorPubKey,_tmpQuestion,_tmpOptionsJson,_tmpStatus,_tmpCreatedAt,_tmpClosesAt,_tmpTotalVotes,_tmpImportanceScore,_tmpUserImportanceVote,_tmpResidentVoteOption,_tmpLinkedLegislationId,_tmpDistrictBreakdownJson,_tmpCachedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPollsSync(districtId: String): List<DistrictPollEntity> {
    val _sql: String =
        "SELECT * FROM district_polls WHERE districtId = ? ORDER BY importanceScore DESC, createdAt DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _cursorIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _cursorIndexOfQuestion: Int = getColumnIndexOrThrow(_stmt, "question")
        val _cursorIndexOfOptionsJson: Int = getColumnIndexOrThrow(_stmt, "optionsJson")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _cursorIndexOfClosesAt: Int = getColumnIndexOrThrow(_stmt, "closesAt")
        val _cursorIndexOfTotalVotes: Int = getColumnIndexOrThrow(_stmt, "totalVotes")
        val _cursorIndexOfImportanceScore: Int = getColumnIndexOrThrow(_stmt, "importanceScore")
        val _cursorIndexOfUserImportanceVote: Int = getColumnIndexOrThrow(_stmt,
            "userImportanceVote")
        val _cursorIndexOfResidentVoteOption: Int = getColumnIndexOrThrow(_stmt,
            "residentVoteOption")
        val _cursorIndexOfLinkedLegislationId: Int = getColumnIndexOrThrow(_stmt,
            "linkedLegislationId")
        val _cursorIndexOfDistrictBreakdownJson: Int = getColumnIndexOrThrow(_stmt,
            "districtBreakdownJson")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<DistrictPollEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DistrictPollEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpScope: PollScope?
          val _tmp: String?
          if (_stmt.isNull(_cursorIndexOfScope)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_cursorIndexOfScope)
          }
          if (_tmp == null) {
            _tmpScope = null
          } else {
            _tmpScope = __civicConverters.toPollScope(_tmp)
          }
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpLocalId: String?
          if (_stmt.isNull(_cursorIndexOfLocalId)) {
            _tmpLocalId = null
          } else {
            _tmpLocalId = _stmt.getText(_cursorIndexOfLocalId)
          }
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_cursorIndexOfAuthorPubKey)
          val _tmpQuestion: String
          _tmpQuestion = _stmt.getText(_cursorIndexOfQuestion)
          val _tmpOptionsJson: String
          _tmpOptionsJson = _stmt.getText(_cursorIndexOfOptionsJson)
          val _tmpStatus: PollStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toPollStatus(_tmp_1)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          val _tmpClosesAt: Long?
          if (_stmt.isNull(_cursorIndexOfClosesAt)) {
            _tmpClosesAt = null
          } else {
            _tmpClosesAt = _stmt.getLong(_cursorIndexOfClosesAt)
          }
          val _tmpTotalVotes: Int
          _tmpTotalVotes = _stmt.getLong(_cursorIndexOfTotalVotes).toInt()
          val _tmpImportanceScore: Int
          _tmpImportanceScore = _stmt.getLong(_cursorIndexOfImportanceScore).toInt()
          val _tmpUserImportanceVote: Int
          _tmpUserImportanceVote = _stmt.getLong(_cursorIndexOfUserImportanceVote).toInt()
          val _tmpResidentVoteOption: String?
          if (_stmt.isNull(_cursorIndexOfResidentVoteOption)) {
            _tmpResidentVoteOption = null
          } else {
            _tmpResidentVoteOption = _stmt.getText(_cursorIndexOfResidentVoteOption)
          }
          val _tmpLinkedLegislationId: String?
          if (_stmt.isNull(_cursorIndexOfLinkedLegislationId)) {
            _tmpLinkedLegislationId = null
          } else {
            _tmpLinkedLegislationId = _stmt.getText(_cursorIndexOfLinkedLegislationId)
          }
          val _tmpDistrictBreakdownJson: String?
          if (_stmt.isNull(_cursorIndexOfDistrictBreakdownJson)) {
            _tmpDistrictBreakdownJson = null
          } else {
            _tmpDistrictBreakdownJson = _stmt.getText(_cursorIndexOfDistrictBreakdownJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item =
              DistrictPollEntity(_tmpId,_tmpScope,_tmpDistrictId,_tmpLocalId,_tmpAuthorPubKey,_tmpQuestion,_tmpOptionsJson,_tmpStatus,_tmpCreatedAt,_tmpClosesAt,_tmpTotalVotes,_tmpImportanceScore,_tmpUserImportanceVote,_tmpResidentVoteOption,_tmpLinkedLegislationId,_tmpDistrictBreakdownJson,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllPollsAcrossDistricts(): List<DistrictPollEntity> {
    val _sql: String = "SELECT * FROM district_polls ORDER BY importanceScore DESC, createdAt DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _cursorIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _cursorIndexOfQuestion: Int = getColumnIndexOrThrow(_stmt, "question")
        val _cursorIndexOfOptionsJson: Int = getColumnIndexOrThrow(_stmt, "optionsJson")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _cursorIndexOfClosesAt: Int = getColumnIndexOrThrow(_stmt, "closesAt")
        val _cursorIndexOfTotalVotes: Int = getColumnIndexOrThrow(_stmt, "totalVotes")
        val _cursorIndexOfImportanceScore: Int = getColumnIndexOrThrow(_stmt, "importanceScore")
        val _cursorIndexOfUserImportanceVote: Int = getColumnIndexOrThrow(_stmt,
            "userImportanceVote")
        val _cursorIndexOfResidentVoteOption: Int = getColumnIndexOrThrow(_stmt,
            "residentVoteOption")
        val _cursorIndexOfLinkedLegislationId: Int = getColumnIndexOrThrow(_stmt,
            "linkedLegislationId")
        val _cursorIndexOfDistrictBreakdownJson: Int = getColumnIndexOrThrow(_stmt,
            "districtBreakdownJson")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<DistrictPollEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DistrictPollEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpScope: PollScope?
          val _tmp: String?
          if (_stmt.isNull(_cursorIndexOfScope)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_cursorIndexOfScope)
          }
          if (_tmp == null) {
            _tmpScope = null
          } else {
            _tmpScope = __civicConverters.toPollScope(_tmp)
          }
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpLocalId: String?
          if (_stmt.isNull(_cursorIndexOfLocalId)) {
            _tmpLocalId = null
          } else {
            _tmpLocalId = _stmt.getText(_cursorIndexOfLocalId)
          }
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_cursorIndexOfAuthorPubKey)
          val _tmpQuestion: String
          _tmpQuestion = _stmt.getText(_cursorIndexOfQuestion)
          val _tmpOptionsJson: String
          _tmpOptionsJson = _stmt.getText(_cursorIndexOfOptionsJson)
          val _tmpStatus: PollStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toPollStatus(_tmp_1)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          val _tmpClosesAt: Long?
          if (_stmt.isNull(_cursorIndexOfClosesAt)) {
            _tmpClosesAt = null
          } else {
            _tmpClosesAt = _stmt.getLong(_cursorIndexOfClosesAt)
          }
          val _tmpTotalVotes: Int
          _tmpTotalVotes = _stmt.getLong(_cursorIndexOfTotalVotes).toInt()
          val _tmpImportanceScore: Int
          _tmpImportanceScore = _stmt.getLong(_cursorIndexOfImportanceScore).toInt()
          val _tmpUserImportanceVote: Int
          _tmpUserImportanceVote = _stmt.getLong(_cursorIndexOfUserImportanceVote).toInt()
          val _tmpResidentVoteOption: String?
          if (_stmt.isNull(_cursorIndexOfResidentVoteOption)) {
            _tmpResidentVoteOption = null
          } else {
            _tmpResidentVoteOption = _stmt.getText(_cursorIndexOfResidentVoteOption)
          }
          val _tmpLinkedLegislationId: String?
          if (_stmt.isNull(_cursorIndexOfLinkedLegislationId)) {
            _tmpLinkedLegislationId = null
          } else {
            _tmpLinkedLegislationId = _stmt.getText(_cursorIndexOfLinkedLegislationId)
          }
          val _tmpDistrictBreakdownJson: String?
          if (_stmt.isNull(_cursorIndexOfDistrictBreakdownJson)) {
            _tmpDistrictBreakdownJson = null
          } else {
            _tmpDistrictBreakdownJson = _stmt.getText(_cursorIndexOfDistrictBreakdownJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item =
              DistrictPollEntity(_tmpId,_tmpScope,_tmpDistrictId,_tmpLocalId,_tmpAuthorPubKey,_tmpQuestion,_tmpOptionsJson,_tmpStatus,_tmpCreatedAt,_tmpClosesAt,_tmpTotalVotes,_tmpImportanceScore,_tmpUserImportanceVote,_tmpResidentVoteOption,_tmpLinkedLegislationId,_tmpDistrictBreakdownJson,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPoll(pollId: String): DistrictPollEntity? {
    val _sql: String = "SELECT * FROM district_polls WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pollId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _cursorIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _cursorIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _cursorIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _cursorIndexOfQuestion: Int = getColumnIndexOrThrow(_stmt, "question")
        val _cursorIndexOfOptionsJson: Int = getColumnIndexOrThrow(_stmt, "optionsJson")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _cursorIndexOfClosesAt: Int = getColumnIndexOrThrow(_stmt, "closesAt")
        val _cursorIndexOfTotalVotes: Int = getColumnIndexOrThrow(_stmt, "totalVotes")
        val _cursorIndexOfImportanceScore: Int = getColumnIndexOrThrow(_stmt, "importanceScore")
        val _cursorIndexOfUserImportanceVote: Int = getColumnIndexOrThrow(_stmt,
            "userImportanceVote")
        val _cursorIndexOfResidentVoteOption: Int = getColumnIndexOrThrow(_stmt,
            "residentVoteOption")
        val _cursorIndexOfLinkedLegislationId: Int = getColumnIndexOrThrow(_stmt,
            "linkedLegislationId")
        val _cursorIndexOfDistrictBreakdownJson: Int = getColumnIndexOrThrow(_stmt,
            "districtBreakdownJson")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: DistrictPollEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpScope: PollScope?
          val _tmp: String?
          if (_stmt.isNull(_cursorIndexOfScope)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_cursorIndexOfScope)
          }
          if (_tmp == null) {
            _tmpScope = null
          } else {
            _tmpScope = __civicConverters.toPollScope(_tmp)
          }
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_cursorIndexOfDistrictId)
          val _tmpLocalId: String?
          if (_stmt.isNull(_cursorIndexOfLocalId)) {
            _tmpLocalId = null
          } else {
            _tmpLocalId = _stmt.getText(_cursorIndexOfLocalId)
          }
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_cursorIndexOfAuthorPubKey)
          val _tmpQuestion: String
          _tmpQuestion = _stmt.getText(_cursorIndexOfQuestion)
          val _tmpOptionsJson: String
          _tmpOptionsJson = _stmt.getText(_cursorIndexOfOptionsJson)
          val _tmpStatus: PollStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toPollStatus(_tmp_1)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          val _tmpClosesAt: Long?
          if (_stmt.isNull(_cursorIndexOfClosesAt)) {
            _tmpClosesAt = null
          } else {
            _tmpClosesAt = _stmt.getLong(_cursorIndexOfClosesAt)
          }
          val _tmpTotalVotes: Int
          _tmpTotalVotes = _stmt.getLong(_cursorIndexOfTotalVotes).toInt()
          val _tmpImportanceScore: Int
          _tmpImportanceScore = _stmt.getLong(_cursorIndexOfImportanceScore).toInt()
          val _tmpUserImportanceVote: Int
          _tmpUserImportanceVote = _stmt.getLong(_cursorIndexOfUserImportanceVote).toInt()
          val _tmpResidentVoteOption: String?
          if (_stmt.isNull(_cursorIndexOfResidentVoteOption)) {
            _tmpResidentVoteOption = null
          } else {
            _tmpResidentVoteOption = _stmt.getText(_cursorIndexOfResidentVoteOption)
          }
          val _tmpLinkedLegislationId: String?
          if (_stmt.isNull(_cursorIndexOfLinkedLegislationId)) {
            _tmpLinkedLegislationId = null
          } else {
            _tmpLinkedLegislationId = _stmt.getText(_cursorIndexOfLinkedLegislationId)
          }
          val _tmpDistrictBreakdownJson: String?
          if (_stmt.isNull(_cursorIndexOfDistrictBreakdownJson)) {
            _tmpDistrictBreakdownJson = null
          } else {
            _tmpDistrictBreakdownJson = _stmt.getText(_cursorIndexOfDistrictBreakdownJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _result =
              DistrictPollEntity(_tmpId,_tmpScope,_tmpDistrictId,_tmpLocalId,_tmpAuthorPubKey,_tmpQuestion,_tmpOptionsJson,_tmpStatus,_tmpCreatedAt,_tmpClosesAt,_tmpTotalVotes,_tmpImportanceScore,_tmpUserImportanceVote,_tmpResidentVoteOption,_tmpLinkedLegislationId,_tmpDistrictBreakdownJson,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun applyOptimisticVote(pollId: String, optionId: String) {
    val _sql: String =
        "UPDATE district_polls SET residentVoteOption = ?, totalVotes = totalVotes + 1 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, optionId)
        _argIndex = 2
        _stmt.bindText(_argIndex, pollId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun evictStalePolls(districtId: String, before: Long) {
    val _sql: String = "DELETE FROM district_polls WHERE districtId = ? AND cachedAt < ?"
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
