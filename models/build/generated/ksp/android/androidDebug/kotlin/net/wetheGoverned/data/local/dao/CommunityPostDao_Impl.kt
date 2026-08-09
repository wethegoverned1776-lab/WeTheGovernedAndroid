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
import kotlin.Double
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
import net.wetheGoverned.`data`.local.entity.CommunityPostEntity
import net.wetheGoverned.model.CommunityPostKind

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CommunityPostDao_Impl(
  __db: RoomDatabase,
) : CommunityPostDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfCommunityPostEntity: EntityUpsertAdapter<CommunityPostEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfCommunityPostEntity = EntityUpsertAdapter<CommunityPostEntity>(object :
        EntityInsertAdapter<CommunityPostEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `community_posts` (`id`,`authorPubKey`,`districtId`,`kind`,`title`,`description`,`price`,`location`,`contactInfo`,`createdAt`,`expiresAt`,`tagsJson`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CommunityPostEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.authorPubKey)
        statement.bindText(3, entity.districtId)
        val _tmp: String = __civicConverters.fromCommunityPostKind(entity.kind)
        statement.bindText(4, _tmp)
        statement.bindText(5, entity.title)
        statement.bindText(6, entity.description)
        val _tmpPrice: Double? = entity.price
        if (_tmpPrice == null) {
          statement.bindNull(7)
        } else {
          statement.bindDouble(7, _tmpPrice)
        }
        val _tmpLocation: String? = entity.location
        if (_tmpLocation == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpLocation)
        }
        val _tmpContactInfo: String? = entity.contactInfo
        if (_tmpContactInfo == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpContactInfo)
        }
        statement.bindLong(10, entity.createdAt)
        val _tmpExpiresAt: Long? = entity.expiresAt
        if (_tmpExpiresAt == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpExpiresAt)
        }
        val _tmpTagsJson: String? = entity.tagsJson
        if (_tmpTagsJson == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpTagsJson)
        }
        statement.bindLong(13, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<CommunityPostEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `community_posts` SET `id` = ?,`authorPubKey` = ?,`districtId` = ?,`kind` = ?,`title` = ?,`description` = ?,`price` = ?,`location` = ?,`contactInfo` = ?,`createdAt` = ?,`expiresAt` = ?,`tagsJson` = ?,`cachedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CommunityPostEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.authorPubKey)
        statement.bindText(3, entity.districtId)
        val _tmp: String = __civicConverters.fromCommunityPostKind(entity.kind)
        statement.bindText(4, _tmp)
        statement.bindText(5, entity.title)
        statement.bindText(6, entity.description)
        val _tmpPrice: Double? = entity.price
        if (_tmpPrice == null) {
          statement.bindNull(7)
        } else {
          statement.bindDouble(7, _tmpPrice)
        }
        val _tmpLocation: String? = entity.location
        if (_tmpLocation == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpLocation)
        }
        val _tmpContactInfo: String? = entity.contactInfo
        if (_tmpContactInfo == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpContactInfo)
        }
        statement.bindLong(10, entity.createdAt)
        val _tmpExpiresAt: Long? = entity.expiresAt
        if (_tmpExpiresAt == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpExpiresAt)
        }
        val _tmpTagsJson: String? = entity.tagsJson
        if (_tmpTagsJson == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpTagsJson)
        }
        statement.bindLong(13, entity.cachedAt)
        statement.bindText(14, entity.id)
      }
    })
  }

  public override suspend fun upsertPost(post: CommunityPostEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfCommunityPostEntity.upsert(_connection, post)
  }

  public override fun observePosts(districtId: String): Flow<List<CommunityPostEntity>> {
    val _sql: String = "SELECT * FROM community_posts WHERE districtId = ? ORDER BY createdAt DESC"
    return createFlow(__db, false, arrayOf("community_posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfKind: Int = getColumnIndexOrThrow(_stmt, "kind")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfPrice: Int = getColumnIndexOrThrow(_stmt, "price")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfContactInfo: Int = getColumnIndexOrThrow(_stmt, "contactInfo")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfExpiresAt: Int = getColumnIndexOrThrow(_stmt, "expiresAt")
        val _columnIndexOfTagsJson: Int = getColumnIndexOrThrow(_stmt, "tagsJson")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<CommunityPostEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CommunityPostEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_columnIndexOfAuthorPubKey)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpKind: CommunityPostKind
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfKind)
          _tmpKind = __civicConverters.toCommunityPostKind(_tmp)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpPrice: Double?
          if (_stmt.isNull(_columnIndexOfPrice)) {
            _tmpPrice = null
          } else {
            _tmpPrice = _stmt.getDouble(_columnIndexOfPrice)
          }
          val _tmpLocation: String?
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          }
          val _tmpContactInfo: String?
          if (_stmt.isNull(_columnIndexOfContactInfo)) {
            _tmpContactInfo = null
          } else {
            _tmpContactInfo = _stmt.getText(_columnIndexOfContactInfo)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpExpiresAt: Long?
          if (_stmt.isNull(_columnIndexOfExpiresAt)) {
            _tmpExpiresAt = null
          } else {
            _tmpExpiresAt = _stmt.getLong(_columnIndexOfExpiresAt)
          }
          val _tmpTagsJson: String?
          if (_stmt.isNull(_columnIndexOfTagsJson)) {
            _tmpTagsJson = null
          } else {
            _tmpTagsJson = _stmt.getText(_columnIndexOfTagsJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item =
              CommunityPostEntity(_tmpId,_tmpAuthorPubKey,_tmpDistrictId,_tmpKind,_tmpTitle,_tmpDescription,_tmpPrice,_tmpLocation,_tmpContactInfo,_tmpCreatedAt,_tmpExpiresAt,_tmpTagsJson,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observePostsByKind(districtId: String, kind: CommunityPostKind):
      Flow<List<CommunityPostEntity>> {
    val _sql: String =
        "SELECT * FROM community_posts WHERE districtId = ? AND kind = ? ORDER BY createdAt DESC"
    return createFlow(__db, false, arrayOf("community_posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, districtId)
        _argIndex = 2
        val _tmp: String = __civicConverters.fromCommunityPostKind(kind)
        _stmt.bindText(_argIndex, _tmp)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfKind: Int = getColumnIndexOrThrow(_stmt, "kind")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfPrice: Int = getColumnIndexOrThrow(_stmt, "price")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfContactInfo: Int = getColumnIndexOrThrow(_stmt, "contactInfo")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfExpiresAt: Int = getColumnIndexOrThrow(_stmt, "expiresAt")
        val _columnIndexOfTagsJson: Int = getColumnIndexOrThrow(_stmt, "tagsJson")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<CommunityPostEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CommunityPostEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_columnIndexOfAuthorPubKey)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpKind: CommunityPostKind
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfKind)
          _tmpKind = __civicConverters.toCommunityPostKind(_tmp_1)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpPrice: Double?
          if (_stmt.isNull(_columnIndexOfPrice)) {
            _tmpPrice = null
          } else {
            _tmpPrice = _stmt.getDouble(_columnIndexOfPrice)
          }
          val _tmpLocation: String?
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          }
          val _tmpContactInfo: String?
          if (_stmt.isNull(_columnIndexOfContactInfo)) {
            _tmpContactInfo = null
          } else {
            _tmpContactInfo = _stmt.getText(_columnIndexOfContactInfo)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpExpiresAt: Long?
          if (_stmt.isNull(_columnIndexOfExpiresAt)) {
            _tmpExpiresAt = null
          } else {
            _tmpExpiresAt = _stmt.getLong(_columnIndexOfExpiresAt)
          }
          val _tmpTagsJson: String?
          if (_stmt.isNull(_columnIndexOfTagsJson)) {
            _tmpTagsJson = null
          } else {
            _tmpTagsJson = _stmt.getText(_columnIndexOfTagsJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item =
              CommunityPostEntity(_tmpId,_tmpAuthorPubKey,_tmpDistrictId,_tmpKind,_tmpTitle,_tmpDescription,_tmpPrice,_tmpLocation,_tmpContactInfo,_tmpCreatedAt,_tmpExpiresAt,_tmpTagsJson,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPost(postId: String): CommunityPostEntity? {
    val _sql: String = "SELECT * FROM community_posts WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, postId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfKind: Int = getColumnIndexOrThrow(_stmt, "kind")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfPrice: Int = getColumnIndexOrThrow(_stmt, "price")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfContactInfo: Int = getColumnIndexOrThrow(_stmt, "contactInfo")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfExpiresAt: Int = getColumnIndexOrThrow(_stmt, "expiresAt")
        val _columnIndexOfTagsJson: Int = getColumnIndexOrThrow(_stmt, "tagsJson")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: CommunityPostEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_columnIndexOfAuthorPubKey)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpKind: CommunityPostKind
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfKind)
          _tmpKind = __civicConverters.toCommunityPostKind(_tmp)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpPrice: Double?
          if (_stmt.isNull(_columnIndexOfPrice)) {
            _tmpPrice = null
          } else {
            _tmpPrice = _stmt.getDouble(_columnIndexOfPrice)
          }
          val _tmpLocation: String?
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          }
          val _tmpContactInfo: String?
          if (_stmt.isNull(_columnIndexOfContactInfo)) {
            _tmpContactInfo = null
          } else {
            _tmpContactInfo = _stmt.getText(_columnIndexOfContactInfo)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpExpiresAt: Long?
          if (_stmt.isNull(_columnIndexOfExpiresAt)) {
            _tmpExpiresAt = null
          } else {
            _tmpExpiresAt = _stmt.getLong(_columnIndexOfExpiresAt)
          }
          val _tmpTagsJson: String?
          if (_stmt.isNull(_columnIndexOfTagsJson)) {
            _tmpTagsJson = null
          } else {
            _tmpTagsJson = _stmt.getText(_columnIndexOfTagsJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _result =
              CommunityPostEntity(_tmpId,_tmpAuthorPubKey,_tmpDistrictId,_tmpKind,_tmpTitle,_tmpDescription,_tmpPrice,_tmpLocation,_tmpContactInfo,_tmpCreatedAt,_tmpExpiresAt,_tmpTagsJson,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllPosts(): List<CommunityPostEntity> {
    val _sql: String = "SELECT * FROM community_posts"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAuthorPubKey: Int = getColumnIndexOrThrow(_stmt, "authorPubKey")
        val _columnIndexOfDistrictId: Int = getColumnIndexOrThrow(_stmt, "districtId")
        val _columnIndexOfKind: Int = getColumnIndexOrThrow(_stmt, "kind")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfPrice: Int = getColumnIndexOrThrow(_stmt, "price")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfContactInfo: Int = getColumnIndexOrThrow(_stmt, "contactInfo")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfExpiresAt: Int = getColumnIndexOrThrow(_stmt, "expiresAt")
        val _columnIndexOfTagsJson: Int = getColumnIndexOrThrow(_stmt, "tagsJson")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<CommunityPostEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CommunityPostEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAuthorPubKey: String
          _tmpAuthorPubKey = _stmt.getText(_columnIndexOfAuthorPubKey)
          val _tmpDistrictId: String
          _tmpDistrictId = _stmt.getText(_columnIndexOfDistrictId)
          val _tmpKind: CommunityPostKind
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfKind)
          _tmpKind = __civicConverters.toCommunityPostKind(_tmp)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpPrice: Double?
          if (_stmt.isNull(_columnIndexOfPrice)) {
            _tmpPrice = null
          } else {
            _tmpPrice = _stmt.getDouble(_columnIndexOfPrice)
          }
          val _tmpLocation: String?
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          }
          val _tmpContactInfo: String?
          if (_stmt.isNull(_columnIndexOfContactInfo)) {
            _tmpContactInfo = null
          } else {
            _tmpContactInfo = _stmt.getText(_columnIndexOfContactInfo)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpExpiresAt: Long?
          if (_stmt.isNull(_columnIndexOfExpiresAt)) {
            _tmpExpiresAt = null
          } else {
            _tmpExpiresAt = _stmt.getLong(_columnIndexOfExpiresAt)
          }
          val _tmpTagsJson: String?
          if (_stmt.isNull(_columnIndexOfTagsJson)) {
            _tmpTagsJson = null
          } else {
            _tmpTagsJson = _stmt.getText(_columnIndexOfTagsJson)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item =
              CommunityPostEntity(_tmpId,_tmpAuthorPubKey,_tmpDistrictId,_tmpKind,_tmpTitle,_tmpDescription,_tmpPrice,_tmpLocation,_tmpContactInfo,_tmpCreatedAt,_tmpExpiresAt,_tmpTagsJson,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deletePost(postId: String) {
    val _sql: String = "DELETE FROM community_posts WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
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
