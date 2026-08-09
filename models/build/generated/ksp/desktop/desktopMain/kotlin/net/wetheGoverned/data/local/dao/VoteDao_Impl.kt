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
import kotlin.Boolean
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
import net.wetheGoverned.`data`.local.entity.CivicVoteEntity
import net.wetheGoverned.model.ConflictStatus

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class VoteDao_Impl(
  __db: RoomDatabase,
) : VoteDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfCivicVoteEntity: EntityUpsertAdapter<CivicVoteEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfCivicVoteEntity = EntityUpsertAdapter<CivicVoteEntity>(object :
        EntityInsertAdapter<CivicVoteEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `poll_votes` (`id`,`pollId`,`voterPubKey`,`voterName`,`optionId`,`timestamp`,`nonce`,`signature`,`isFlagged`,`flagReason`,`disputeComment`,`disputeExpiresAt`,`status`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CivicVoteEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.pollId)
        statement.bindText(3, entity.voterPubKey)
        statement.bindText(4, entity.voterName)
        statement.bindText(5, entity.optionId)
        statement.bindLong(6, entity.timestamp)
        statement.bindLong(7, entity.nonce)
        val _tmpSignature: String? = entity.signature
        if (_tmpSignature == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpSignature)
        }
        val _tmp: Int = if (entity.isFlagged) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        val _tmpFlagReason: String? = entity.flagReason
        if (_tmpFlagReason == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpFlagReason)
        }
        val _tmpDisputeComment: String? = entity.disputeComment
        if (_tmpDisputeComment == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpDisputeComment)
        }
        val _tmpDisputeExpiresAt: Long? = entity.disputeExpiresAt
        if (_tmpDisputeExpiresAt == null) {
          statement.bindNull(12)
        } else {
          statement.bindLong(12, _tmpDisputeExpiresAt)
        }
        val _tmp_1: String = __civicConverters.fromConflictStatus(entity.status)
        statement.bindText(13, _tmp_1)
      }
    }, object : EntityDeleteOrUpdateAdapter<CivicVoteEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `poll_votes` SET `id` = ?,`pollId` = ?,`voterPubKey` = ?,`voterName` = ?,`optionId` = ?,`timestamp` = ?,`nonce` = ?,`signature` = ?,`isFlagged` = ?,`flagReason` = ?,`disputeComment` = ?,`disputeExpiresAt` = ?,`status` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CivicVoteEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.pollId)
        statement.bindText(3, entity.voterPubKey)
        statement.bindText(4, entity.voterName)
        statement.bindText(5, entity.optionId)
        statement.bindLong(6, entity.timestamp)
        statement.bindLong(7, entity.nonce)
        val _tmpSignature: String? = entity.signature
        if (_tmpSignature == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpSignature)
        }
        val _tmp: Int = if (entity.isFlagged) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        val _tmpFlagReason: String? = entity.flagReason
        if (_tmpFlagReason == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpFlagReason)
        }
        val _tmpDisputeComment: String? = entity.disputeComment
        if (_tmpDisputeComment == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpDisputeComment)
        }
        val _tmpDisputeExpiresAt: Long? = entity.disputeExpiresAt
        if (_tmpDisputeExpiresAt == null) {
          statement.bindNull(12)
        } else {
          statement.bindLong(12, _tmpDisputeExpiresAt)
        }
        val _tmp_1: String = __civicConverters.fromConflictStatus(entity.status)
        statement.bindText(13, _tmp_1)
        statement.bindText(14, entity.id)
      }
    })
  }

  public override suspend fun upsertVote(vote: CivicVoteEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfCivicVoteEntity.upsert(_connection, vote)
  }

  public override fun observeAllVotes(): Flow<List<CivicVoteEntity>> {
    val _sql: String = "SELECT * FROM poll_votes ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("poll_votes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfVoterPubKey: Int = getColumnIndexOrThrow(_stmt, "voterPubKey")
        val _cursorIndexOfVoterName: Int = getColumnIndexOrThrow(_stmt, "voterName")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _cursorIndexOfNonce: Int = getColumnIndexOrThrow(_stmt, "nonce")
        val _cursorIndexOfSignature: Int = getColumnIndexOrThrow(_stmt, "signature")
        val _cursorIndexOfIsFlagged: Int = getColumnIndexOrThrow(_stmt, "isFlagged")
        val _cursorIndexOfFlagReason: Int = getColumnIndexOrThrow(_stmt, "flagReason")
        val _cursorIndexOfDisputeComment: Int = getColumnIndexOrThrow(_stmt, "disputeComment")
        val _cursorIndexOfDisputeExpiresAt: Int = getColumnIndexOrThrow(_stmt, "disputeExpiresAt")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: MutableList<CivicVoteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CivicVoteEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpVoterPubKey: String
          _tmpVoterPubKey = _stmt.getText(_cursorIndexOfVoterPubKey)
          val _tmpVoterName: String
          _tmpVoterName = _stmt.getText(_cursorIndexOfVoterName)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp)
          val _tmpNonce: Long
          _tmpNonce = _stmt.getLong(_cursorIndexOfNonce)
          val _tmpSignature: String?
          if (_stmt.isNull(_cursorIndexOfSignature)) {
            _tmpSignature = null
          } else {
            _tmpSignature = _stmt.getText(_cursorIndexOfSignature)
          }
          val _tmpIsFlagged: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsFlagged).toInt()
          _tmpIsFlagged = _tmp != 0
          val _tmpFlagReason: String?
          if (_stmt.isNull(_cursorIndexOfFlagReason)) {
            _tmpFlagReason = null
          } else {
            _tmpFlagReason = _stmt.getText(_cursorIndexOfFlagReason)
          }
          val _tmpDisputeComment: String?
          if (_stmt.isNull(_cursorIndexOfDisputeComment)) {
            _tmpDisputeComment = null
          } else {
            _tmpDisputeComment = _stmt.getText(_cursorIndexOfDisputeComment)
          }
          val _tmpDisputeExpiresAt: Long?
          if (_stmt.isNull(_cursorIndexOfDisputeExpiresAt)) {
            _tmpDisputeExpiresAt = null
          } else {
            _tmpDisputeExpiresAt = _stmt.getLong(_cursorIndexOfDisputeExpiresAt)
          }
          val _tmpStatus: ConflictStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toConflictStatus(_tmp_1)
          _item =
              CivicVoteEntity(_tmpId,_tmpPollId,_tmpVoterPubKey,_tmpVoterName,_tmpOptionId,_tmpTimestamp,_tmpNonce,_tmpSignature,_tmpIsFlagged,_tmpFlagReason,_tmpDisputeComment,_tmpDisputeExpiresAt,_tmpStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeVotesByUser(pubKey: String): Flow<List<CivicVoteEntity>> {
    val _sql: String = "SELECT * FROM poll_votes WHERE voterPubKey = ? ORDER BY timestamp DESC"
    return createFlow(__db, false, arrayOf("poll_votes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pubKey)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfVoterPubKey: Int = getColumnIndexOrThrow(_stmt, "voterPubKey")
        val _cursorIndexOfVoterName: Int = getColumnIndexOrThrow(_stmt, "voterName")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _cursorIndexOfNonce: Int = getColumnIndexOrThrow(_stmt, "nonce")
        val _cursorIndexOfSignature: Int = getColumnIndexOrThrow(_stmt, "signature")
        val _cursorIndexOfIsFlagged: Int = getColumnIndexOrThrow(_stmt, "isFlagged")
        val _cursorIndexOfFlagReason: Int = getColumnIndexOrThrow(_stmt, "flagReason")
        val _cursorIndexOfDisputeComment: Int = getColumnIndexOrThrow(_stmt, "disputeComment")
        val _cursorIndexOfDisputeExpiresAt: Int = getColumnIndexOrThrow(_stmt, "disputeExpiresAt")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: MutableList<CivicVoteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CivicVoteEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpVoterPubKey: String
          _tmpVoterPubKey = _stmt.getText(_cursorIndexOfVoterPubKey)
          val _tmpVoterName: String
          _tmpVoterName = _stmt.getText(_cursorIndexOfVoterName)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp)
          val _tmpNonce: Long
          _tmpNonce = _stmt.getLong(_cursorIndexOfNonce)
          val _tmpSignature: String?
          if (_stmt.isNull(_cursorIndexOfSignature)) {
            _tmpSignature = null
          } else {
            _tmpSignature = _stmt.getText(_cursorIndexOfSignature)
          }
          val _tmpIsFlagged: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsFlagged).toInt()
          _tmpIsFlagged = _tmp != 0
          val _tmpFlagReason: String?
          if (_stmt.isNull(_cursorIndexOfFlagReason)) {
            _tmpFlagReason = null
          } else {
            _tmpFlagReason = _stmt.getText(_cursorIndexOfFlagReason)
          }
          val _tmpDisputeComment: String?
          if (_stmt.isNull(_cursorIndexOfDisputeComment)) {
            _tmpDisputeComment = null
          } else {
            _tmpDisputeComment = _stmt.getText(_cursorIndexOfDisputeComment)
          }
          val _tmpDisputeExpiresAt: Long?
          if (_stmt.isNull(_cursorIndexOfDisputeExpiresAt)) {
            _tmpDisputeExpiresAt = null
          } else {
            _tmpDisputeExpiresAt = _stmt.getLong(_cursorIndexOfDisputeExpiresAt)
          }
          val _tmpStatus: ConflictStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toConflictStatus(_tmp_1)
          _item =
              CivicVoteEntity(_tmpId,_tmpPollId,_tmpVoterPubKey,_tmpVoterName,_tmpOptionId,_tmpTimestamp,_tmpNonce,_tmpSignature,_tmpIsFlagged,_tmpFlagReason,_tmpDisputeComment,_tmpDisputeExpiresAt,_tmpStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getVoteById(voteId: String): CivicVoteEntity? {
    val _sql: String = "SELECT * FROM poll_votes WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, voteId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfVoterPubKey: Int = getColumnIndexOrThrow(_stmt, "voterPubKey")
        val _cursorIndexOfVoterName: Int = getColumnIndexOrThrow(_stmt, "voterName")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _cursorIndexOfNonce: Int = getColumnIndexOrThrow(_stmt, "nonce")
        val _cursorIndexOfSignature: Int = getColumnIndexOrThrow(_stmt, "signature")
        val _cursorIndexOfIsFlagged: Int = getColumnIndexOrThrow(_stmt, "isFlagged")
        val _cursorIndexOfFlagReason: Int = getColumnIndexOrThrow(_stmt, "flagReason")
        val _cursorIndexOfDisputeComment: Int = getColumnIndexOrThrow(_stmt, "disputeComment")
        val _cursorIndexOfDisputeExpiresAt: Int = getColumnIndexOrThrow(_stmt, "disputeExpiresAt")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: CivicVoteEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpVoterPubKey: String
          _tmpVoterPubKey = _stmt.getText(_cursorIndexOfVoterPubKey)
          val _tmpVoterName: String
          _tmpVoterName = _stmt.getText(_cursorIndexOfVoterName)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp)
          val _tmpNonce: Long
          _tmpNonce = _stmt.getLong(_cursorIndexOfNonce)
          val _tmpSignature: String?
          if (_stmt.isNull(_cursorIndexOfSignature)) {
            _tmpSignature = null
          } else {
            _tmpSignature = _stmt.getText(_cursorIndexOfSignature)
          }
          val _tmpIsFlagged: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsFlagged).toInt()
          _tmpIsFlagged = _tmp != 0
          val _tmpFlagReason: String?
          if (_stmt.isNull(_cursorIndexOfFlagReason)) {
            _tmpFlagReason = null
          } else {
            _tmpFlagReason = _stmt.getText(_cursorIndexOfFlagReason)
          }
          val _tmpDisputeComment: String?
          if (_stmt.isNull(_cursorIndexOfDisputeComment)) {
            _tmpDisputeComment = null
          } else {
            _tmpDisputeComment = _stmt.getText(_cursorIndexOfDisputeComment)
          }
          val _tmpDisputeExpiresAt: Long?
          if (_stmt.isNull(_cursorIndexOfDisputeExpiresAt)) {
            _tmpDisputeExpiresAt = null
          } else {
            _tmpDisputeExpiresAt = _stmt.getLong(_cursorIndexOfDisputeExpiresAt)
          }
          val _tmpStatus: ConflictStatus
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __civicConverters.toConflictStatus(_tmp_1)
          _result =
              CivicVoteEntity(_tmpId,_tmpPollId,_tmpVoterPubKey,_tmpVoterName,_tmpOptionId,_tmpTimestamp,_tmpNonce,_tmpSignature,_tmpIsFlagged,_tmpFlagReason,_tmpDisputeComment,_tmpDisputeExpiresAt,_tmpStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun flagVote(voteId: String, expiresAt: Long) {
    val _sql: String =
        "UPDATE poll_votes SET isFlagged = 1, status = 'FLAGGED', disputeExpiresAt = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, expiresAt)
        _argIndex = 2
        _stmt.bindText(_argIndex, voteId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun disputeVote(voteId: String, comment: String) {
    val _sql: String = "UPDATE poll_votes SET disputeComment = ?, status = 'DISPUTED' WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, comment)
        _argIndex = 2
        _stmt.bindText(_argIndex, voteId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun resolveVote(voteId: String) {
    val _sql: String = "UPDATE poll_votes SET status = 'RESOLVED', isFlagged = 0 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, voteId)
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
