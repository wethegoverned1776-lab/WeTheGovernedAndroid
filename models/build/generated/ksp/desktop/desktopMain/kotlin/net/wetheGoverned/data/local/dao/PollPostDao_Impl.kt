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
import net.wetheGoverned.`data`.local.entity.PollPostEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PollPostDao_Impl(
  __db: RoomDatabase,
) : PollPostDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfPollPostEntity: EntityUpsertAdapter<PollPostEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfPollPostEntity = EntityUpsertAdapter<PollPostEntity>(object :
        EntityInsertAdapter<PollPostEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `poll_posts` (`id`,`pollId`,`optionId`,`parentPostId`,`headline`,`authorName`,`content`,`score`,`userVote`,`createdAt`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PollPostEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.pollId)
        statement.bindText(3, entity.optionId)
        val _tmpParentPostId: String? = entity.parentPostId
        if (_tmpParentPostId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpParentPostId)
        }
        val _tmpHeadline: String? = entity.headline
        if (_tmpHeadline == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpHeadline)
        }
        statement.bindText(6, entity.authorName)
        statement.bindText(7, entity.content)
        statement.bindLong(8, entity.score.toLong())
        statement.bindLong(9, entity.userVote.toLong())
        statement.bindLong(10, entity.createdAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<PollPostEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `poll_posts` SET `id` = ?,`pollId` = ?,`optionId` = ?,`parentPostId` = ?,`headline` = ?,`authorName` = ?,`content` = ?,`score` = ?,`userVote` = ?,`createdAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PollPostEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.pollId)
        statement.bindText(3, entity.optionId)
        val _tmpParentPostId: String? = entity.parentPostId
        if (_tmpParentPostId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpParentPostId)
        }
        val _tmpHeadline: String? = entity.headline
        if (_tmpHeadline == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpHeadline)
        }
        statement.bindText(6, entity.authorName)
        statement.bindText(7, entity.content)
        statement.bindLong(8, entity.score.toLong())
        statement.bindLong(9, entity.userVote.toLong())
        statement.bindLong(10, entity.createdAt)
        statement.bindText(11, entity.id)
      }
    })
  }

  public override suspend fun upsertPost(post: PollPostEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfPollPostEntity.upsert(_connection, post)
  }

  public override fun getAllPostsForPoll(pollId: String): Flow<List<PollPostEntity>> {
    val _sql: String = "SELECT * FROM poll_posts WHERE pollId = ? ORDER BY score DESC"
    return createFlow(__db, false, arrayOf("poll_posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pollId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfParentPostId: Int = getColumnIndexOrThrow(_stmt, "parentPostId")
        val _cursorIndexOfHeadline: Int = getColumnIndexOrThrow(_stmt, "headline")
        val _cursorIndexOfAuthorName: Int = getColumnIndexOrThrow(_stmt, "authorName")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _cursorIndexOfUserVote: Int = getColumnIndexOrThrow(_stmt, "userVote")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<PollPostEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PollPostEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpParentPostId: String?
          if (_stmt.isNull(_cursorIndexOfParentPostId)) {
            _tmpParentPostId = null
          } else {
            _tmpParentPostId = _stmt.getText(_cursorIndexOfParentPostId)
          }
          val _tmpHeadline: String?
          if (_stmt.isNull(_cursorIndexOfHeadline)) {
            _tmpHeadline = null
          } else {
            _tmpHeadline = _stmt.getText(_cursorIndexOfHeadline)
          }
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_cursorIndexOfAuthorName)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_cursorIndexOfContent)
          val _tmpScore: Int
          _tmpScore = _stmt.getLong(_cursorIndexOfScore).toInt()
          val _tmpUserVote: Int
          _tmpUserVote = _stmt.getLong(_cursorIndexOfUserVote).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          _item =
              PollPostEntity(_tmpId,_tmpPollId,_tmpOptionId,_tmpParentPostId,_tmpHeadline,_tmpAuthorName,_tmpContent,_tmpScore,_tmpUserVote,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeOptionPosts(pollId: String, optionId: String):
      Flow<List<PollPostEntity>> {
    val _sql: String =
        "SELECT * FROM poll_posts WHERE pollId = ? AND optionId = ? AND parentPostId IS NULL ORDER BY score DESC"
    return createFlow(__db, false, arrayOf("poll_posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pollId)
        _argIndex = 2
        _stmt.bindText(_argIndex, optionId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfParentPostId: Int = getColumnIndexOrThrow(_stmt, "parentPostId")
        val _cursorIndexOfHeadline: Int = getColumnIndexOrThrow(_stmt, "headline")
        val _cursorIndexOfAuthorName: Int = getColumnIndexOrThrow(_stmt, "authorName")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _cursorIndexOfUserVote: Int = getColumnIndexOrThrow(_stmt, "userVote")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<PollPostEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PollPostEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpParentPostId: String?
          if (_stmt.isNull(_cursorIndexOfParentPostId)) {
            _tmpParentPostId = null
          } else {
            _tmpParentPostId = _stmt.getText(_cursorIndexOfParentPostId)
          }
          val _tmpHeadline: String?
          if (_stmt.isNull(_cursorIndexOfHeadline)) {
            _tmpHeadline = null
          } else {
            _tmpHeadline = _stmt.getText(_cursorIndexOfHeadline)
          }
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_cursorIndexOfAuthorName)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_cursorIndexOfContent)
          val _tmpScore: Int
          _tmpScore = _stmt.getLong(_cursorIndexOfScore).toInt()
          val _tmpUserVote: Int
          _tmpUserVote = _stmt.getLong(_cursorIndexOfUserVote).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          _item =
              PollPostEntity(_tmpId,_tmpPollId,_tmpOptionId,_tmpParentPostId,_tmpHeadline,_tmpAuthorName,_tmpContent,_tmpScore,_tmpUserVote,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeThreadedPosts(parentPostId: String): Flow<List<PollPostEntity>> {
    val _sql: String = "SELECT * FROM poll_posts WHERE parentPostId = ? ORDER BY score DESC"
    return createFlow(__db, false, arrayOf("poll_posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, parentPostId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfParentPostId: Int = getColumnIndexOrThrow(_stmt, "parentPostId")
        val _cursorIndexOfHeadline: Int = getColumnIndexOrThrow(_stmt, "headline")
        val _cursorIndexOfAuthorName: Int = getColumnIndexOrThrow(_stmt, "authorName")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _cursorIndexOfUserVote: Int = getColumnIndexOrThrow(_stmt, "userVote")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<PollPostEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PollPostEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpParentPostId: String?
          if (_stmt.isNull(_cursorIndexOfParentPostId)) {
            _tmpParentPostId = null
          } else {
            _tmpParentPostId = _stmt.getText(_cursorIndexOfParentPostId)
          }
          val _tmpHeadline: String?
          if (_stmt.isNull(_cursorIndexOfHeadline)) {
            _tmpHeadline = null
          } else {
            _tmpHeadline = _stmt.getText(_cursorIndexOfHeadline)
          }
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_cursorIndexOfAuthorName)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_cursorIndexOfContent)
          val _tmpScore: Int
          _tmpScore = _stmt.getLong(_cursorIndexOfScore).toInt()
          val _tmpUserVote: Int
          _tmpUserVote = _stmt.getLong(_cursorIndexOfUserVote).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          _item =
              PollPostEntity(_tmpId,_tmpPollId,_tmpOptionId,_tmpParentPostId,_tmpHeadline,_tmpAuthorName,_tmpContent,_tmpScore,_tmpUserVote,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPost(postId: String): PollPostEntity? {
    val _sql: String = "SELECT * FROM poll_posts WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, postId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfParentPostId: Int = getColumnIndexOrThrow(_stmt, "parentPostId")
        val _cursorIndexOfHeadline: Int = getColumnIndexOrThrow(_stmt, "headline")
        val _cursorIndexOfAuthorName: Int = getColumnIndexOrThrow(_stmt, "authorName")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _cursorIndexOfUserVote: Int = getColumnIndexOrThrow(_stmt, "userVote")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: PollPostEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpParentPostId: String?
          if (_stmt.isNull(_cursorIndexOfParentPostId)) {
            _tmpParentPostId = null
          } else {
            _tmpParentPostId = _stmt.getText(_cursorIndexOfParentPostId)
          }
          val _tmpHeadline: String?
          if (_stmt.isNull(_cursorIndexOfHeadline)) {
            _tmpHeadline = null
          } else {
            _tmpHeadline = _stmt.getText(_cursorIndexOfHeadline)
          }
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_cursorIndexOfAuthorName)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_cursorIndexOfContent)
          val _tmpScore: Int
          _tmpScore = _stmt.getLong(_cursorIndexOfScore).toInt()
          val _tmpUserVote: Int
          _tmpUserVote = _stmt.getLong(_cursorIndexOfUserVote).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          _result =
              PollPostEntity(_tmpId,_tmpPollId,_tmpOptionId,_tmpParentPostId,_tmpHeadline,_tmpAuthorName,_tmpContent,_tmpScore,_tmpUserVote,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllPosts(): List<PollPostEntity> {
    val _sql: String = "SELECT * FROM poll_posts"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPollId: Int = getColumnIndexOrThrow(_stmt, "pollId")
        val _cursorIndexOfOptionId: Int = getColumnIndexOrThrow(_stmt, "optionId")
        val _cursorIndexOfParentPostId: Int = getColumnIndexOrThrow(_stmt, "parentPostId")
        val _cursorIndexOfHeadline: Int = getColumnIndexOrThrow(_stmt, "headline")
        val _cursorIndexOfAuthorName: Int = getColumnIndexOrThrow(_stmt, "authorName")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _cursorIndexOfUserVote: Int = getColumnIndexOrThrow(_stmt, "userVote")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<PollPostEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PollPostEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpPollId: String
          _tmpPollId = _stmt.getText(_cursorIndexOfPollId)
          val _tmpOptionId: String
          _tmpOptionId = _stmt.getText(_cursorIndexOfOptionId)
          val _tmpParentPostId: String?
          if (_stmt.isNull(_cursorIndexOfParentPostId)) {
            _tmpParentPostId = null
          } else {
            _tmpParentPostId = _stmt.getText(_cursorIndexOfParentPostId)
          }
          val _tmpHeadline: String?
          if (_stmt.isNull(_cursorIndexOfHeadline)) {
            _tmpHeadline = null
          } else {
            _tmpHeadline = _stmt.getText(_cursorIndexOfHeadline)
          }
          val _tmpAuthorName: String
          _tmpAuthorName = _stmt.getText(_cursorIndexOfAuthorName)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_cursorIndexOfContent)
          val _tmpScore: Int
          _tmpScore = _stmt.getLong(_cursorIndexOfScore).toInt()
          val _tmpUserVote: Int
          _tmpUserVote = _stmt.getLong(_cursorIndexOfUserVote).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          _item =
              PollPostEntity(_tmpId,_tmpPollId,_tmpOptionId,_tmpParentPostId,_tmpHeadline,_tmpAuthorName,_tmpContent,_tmpScore,_tmpUserVote,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateVote(
    postId: String,
    delta: Int,
    userVote: Int,
  ) {
    val _sql: String = "UPDATE poll_posts SET score = score + ?, userVote = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, delta.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, userVote.toLong())
        _argIndex = 3
        _stmt.bindText(_argIndex, postId)
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
