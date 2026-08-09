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
import net.wetheGoverned.`data`.local.entity.CandidateManifestoEntity
import net.wetheGoverned.`data`.local.entity.ManifestoQuestionEntity
import net.wetheGoverned.model.PollScope

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ManifestoDao_Impl(
  __db: RoomDatabase,
) : ManifestoDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfCandidateManifestoEntity:
      EntityUpsertAdapter<CandidateManifestoEntity>

  private val __civicConverters: CivicConverters = CivicConverters()

  private val __upsertAdapterOfManifestoQuestionEntity: EntityUpsertAdapter<ManifestoQuestionEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfCandidateManifestoEntity =
        EntityUpsertAdapter<CandidateManifestoEntity>(object :
        EntityInsertAdapter<CandidateManifestoEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `candidate_manifestos` (`id`,`candidatePubKey`,`districtId`,`scope`,`title`,`body`,`publishedAt`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CandidateManifestoEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.candidatePubKey)
        statement.bindText(3, entity.districtId)
        val _tmp: String = __civicConverters.fromPollScope(entity.scope)
        statement.bindText(4, _tmp)
        statement.bindText(5, entity.title)
        statement.bindText(6, entity.body)
        statement.bindLong(7, entity.publishedAt)
        statement.bindLong(8, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<CandidateManifestoEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `candidate_manifestos` SET `id` = ?,`candidatePubKey` = ?,`districtId` = ?,`scope` = ?,`title` = ?,`body` = ?,`publishedAt` = ?,`cachedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CandidateManifestoEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.candidatePubKey)
        statement.bindText(3, entity.districtId)
        val _tmp: String = __civicConverters.fromPollScope(entity.scope)
        statement.bindText(4, _tmp)
        statement.bindText(5, entity.title)
        statement.bindText(6, entity.body)
        statement.bindLong(7, entity.publishedAt)
        statement.bindLong(8, entity.cachedAt)
        statement.bindText(9, entity.id)
      }
    })
    this.__upsertAdapterOfManifestoQuestionEntity =
        EntityUpsertAdapter<ManifestoQuestionEntity>(object :
        EntityInsertAdapter<ManifestoQuestionEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `manifesto_questions` (`id`,`manifestoId`,`askerPubKey`,`text`,`askedAt`,`answer`,`answeredAt`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ManifestoQuestionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.manifestoId)
        statement.bindText(3, entity.askerPubKey)
        statement.bindText(4, entity.text)
        statement.bindLong(5, entity.askedAt)
        val _tmpAnswer: String? = entity.answer
        if (_tmpAnswer == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpAnswer)
        }
        val _tmpAnsweredAt: Long? = entity.answeredAt
        if (_tmpAnsweredAt == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpAnsweredAt)
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<ManifestoQuestionEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `manifesto_questions` SET `id` = ?,`manifestoId` = ?,`askerPubKey` = ?,`text` = ?,`askedAt` = ?,`answer` = ?,`answeredAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ManifestoQuestionEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.manifestoId)
        statement.bindText(3, entity.askerPubKey)
        statement.bindText(4, entity.text)
        statement.bindLong(5, entity.askedAt)
        val _tmpAnswer: String? = entity.answer
        if (_tmpAnswer == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpAnswer)
        }
        val _tmpAnsweredAt: Long? = entity.answeredAt
        if (_tmpAnsweredAt == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpAnsweredAt)
        }
        statement.bindText(8, entity.id)
      }
    })
  }

  public override suspend fun upsertManifesto(manifesto: CandidateManifestoEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfCandidateManifestoEntity.upsert(_connection, manifesto)
  }

  public override suspend fun upsertQuestion(question: ManifestoQuestionEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfManifestoQuestionEntity.upsert(_connection, question)
  }

  public override fun observeManifestos(districtId: String): Flow<List<CandidateManifestoEntity>> {
    val _sql: String =
        "SELECT * FROM candidate_manifestos WHERE districtId = ? ORDER BY publishedAt DESC"
    return createFlow(__db, false, arrayOf("candidate_manifestos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCandidatePubKey: Int = getColumnIndexOrThrow(_stmt, "candidatePubKey")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfBody: Int = getColumnIndexOrThrow(_stmt, "body")
        val _columnIndexOfPublishedAt: Int = getColumnIndexOrThrow(_stmt, "publishedAt")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<CandidateManifestoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CandidateManifestoEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCandidatePubKey: String
          _tmpCandidatePubKey = _stmt.getText(_columnIndexOfCandidatePubKey)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpScope: PollScope
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfScope)
          _tmpScope = __civicConverters.toPollScope(_tmp)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpBody: String
          _tmpBody = _stmt.getText(_columnIndexOfBody)
          val _tmpPublishedAt: Long
          _tmpPublishedAt = _stmt.getLong(_columnIndexOfPublishedAt)
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item =
              CandidateManifestoEntity(_tmpId,_tmpCandidatePubKey,_tmpDistrictId,_tmpScope,_tmpTitle,_tmpBody,_tmpPublishedAt,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getManifesto(manifestoId: String): CandidateManifestoEntity? {
    val _sql: String = "SELECT * FROM candidate_manifestos WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, manifestoId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCandidatePubKey: Int = getColumnIndexOrThrow(_stmt, "candidatePubKey")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfScope: Int = getColumnIndexOrThrow(_stmt, "scope")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfBody: Int = getColumnIndexOrThrow(_stmt, "body")
        val _columnIndexOfPublishedAt: Int = getColumnIndexOrThrow(_stmt, "publishedAt")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: CandidateManifestoEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCandidatePubKey: String
          _tmpCandidatePubKey = _stmt.getText(_columnIndexOfCandidatePubKey)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpScope: PollScope
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfScope)
          _tmpScope = __civicConverters.toPollScope(_tmp)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpBody: String
          _tmpBody = _stmt.getText(_columnIndexOfBody)
          val _tmpPublishedAt: Long
          _tmpPublishedAt = _stmt.getLong(_columnIndexOfPublishedAt)
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _result =
              CandidateManifestoEntity(_tmpId,_tmpCandidatePubKey,_tmpDistrictId,_tmpScope,_tmpTitle,_tmpBody,_tmpPublishedAt,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getQuestions(manifestoId: String): List<ManifestoQuestionEntity> {
    val _sql: String =
        "SELECT * FROM manifesto_questions WHERE manifestoId = ? ORDER BY askedAt ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, manifestoId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfManifestoId: Int = getColumnIndexOrThrow(_stmt, "manifestoId")
        val _columnIndexOfAskerPubKey: Int = getColumnIndexOrThrow(_stmt, "askerPubKey")
        val _columnIndexOfText: Int = getColumnIndexOrThrow(_stmt, "text")
        val _columnIndexOfAskedAt: Int = getColumnIndexOrThrow(_stmt, "askedAt")
        val _columnIndexOfAnswer: Int = getColumnIndexOrThrow(_stmt, "answer")
        val _columnIndexOfAnsweredAt: Int = getColumnIndexOrThrow(_stmt, "answeredAt")
        val _result: MutableList<ManifestoQuestionEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ManifestoQuestionEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpManifestoId: String
          _tmpManifestoId = _stmt.getText(_columnIndexOfManifestoId)
          val _tmpAskerPubKey: String
          _tmpAskerPubKey = _stmt.getText(_columnIndexOfAskerPubKey)
          val _tmpText: String
          _tmpText = _stmt.getText(_columnIndexOfText)
          val _tmpAskedAt: Long
          _tmpAskedAt = _stmt.getLong(_columnIndexOfAskedAt)
          val _tmpAnswer: String?
          if (_stmt.isNull(_columnIndexOfAnswer)) {
            _tmpAnswer = null
          } else {
            _tmpAnswer = _stmt.getText(_columnIndexOfAnswer)
          }
          val _tmpAnsweredAt: Long?
          if (_stmt.isNull(_columnIndexOfAnsweredAt)) {
            _tmpAnsweredAt = null
          } else {
            _tmpAnsweredAt = _stmt.getLong(_columnIndexOfAnsweredAt)
          }
          _item =
              ManifestoQuestionEntity(_tmpId,_tmpManifestoId,_tmpAskerPubKey,_tmpText,_tmpAskedAt,_tmpAnswer,_tmpAnsweredAt)
          _result.add(_item)
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
