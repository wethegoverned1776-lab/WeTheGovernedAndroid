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
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow
import net.wetheGoverned.`data`.local.CivicConverters
import net.wetheGoverned.`data`.local.entity.ResidentProfileEntity
import net.wetheGoverned.model.VerificationTier

@Generated(value = ["androidx.room3.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL", "MemberExtensionConflict"])
internal class ResidentProfileDao_Impl(
  __db: RoomDatabase,
) : ResidentProfileDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfResidentProfileEntity: EntityUpsertAdapter<ResidentProfileEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfResidentProfileEntity = EntityUpsertAdapter<ResidentProfileEntity>(object : EntityInsertAdapter<ResidentProfileEntity>() {
      protected override fun createQuery(): String = "INSERT INTO `resident_profiles` (`pubKey`,`displayName`,`federalHouseId`,`federalSenateId`,`stateSenateId`,`stateHouseId`,`countyId`,`cityId`,`schoolBoardId`,`tier`,`avatarUrl`,`joinedAt`,`addressFingerprint`,`verifiedByPubKey`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ResidentProfileEntity) {
        statement.bindText(1, entity.pubKey)
        statement.bindText(2, entity.displayName)
        val _tmpFederalHouseId: String? = entity.federalHouseId
        if (_tmpFederalHouseId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpFederalHouseId)
        }
        val _tmpFederalSenateId: String? = entity.federalSenateId
        if (_tmpFederalSenateId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpFederalSenateId)
        }
        val _tmpStateSenateId: String? = entity.stateSenateId
        if (_tmpStateSenateId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpStateSenateId)
        }
        val _tmpStateHouseId: String? = entity.stateHouseId
        if (_tmpStateHouseId == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpStateHouseId)
        }
        val _tmpCountyId: String? = entity.countyId
        if (_tmpCountyId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCountyId)
        }
        val _tmpCityId: String? = entity.cityId
        if (_tmpCityId == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpCityId)
        }
        val _tmpSchoolBoardId: String? = entity.schoolBoardId
        if (_tmpSchoolBoardId == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpSchoolBoardId)
        }
        val _tmp: String = __civicConverters.fromVerificationTier(entity.tier)
        statement.bindText(10, _tmp)
        val _tmpAvatarUrl: String? = entity.avatarUrl
        if (_tmpAvatarUrl == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpAvatarUrl)
        }
        statement.bindLong(12, entity.joinedAt)
        val _tmpAddressFingerprint: String? = entity.addressFingerprint
        if (_tmpAddressFingerprint == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpAddressFingerprint)
        }
        val _tmpVerifiedByPubKey: String? = entity.verifiedByPubKey
        if (_tmpVerifiedByPubKey == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpVerifiedByPubKey)
        }
        statement.bindLong(15, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<ResidentProfileEntity>() {
      protected override fun createQuery(): String = "UPDATE `resident_profiles` SET `pubKey` = ?,`displayName` = ?,`federalHouseId` = ?,`federalSenateId` = ?,`stateSenateId` = ?,`stateHouseId` = ?,`countyId` = ?,`cityId` = ?,`schoolBoardId` = ?,`tier` = ?,`avatarUrl` = ?,`joinedAt` = ?,`addressFingerprint` = ?,`verifiedByPubKey` = ?,`cachedAt` = ? WHERE `pubKey` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ResidentProfileEntity) {
        statement.bindText(1, entity.pubKey)
        statement.bindText(2, entity.displayName)
        val _tmpFederalHouseId: String? = entity.federalHouseId
        if (_tmpFederalHouseId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpFederalHouseId)
        }
        val _tmpFederalSenateId: String? = entity.federalSenateId
        if (_tmpFederalSenateId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpFederalSenateId)
        }
        val _tmpStateSenateId: String? = entity.stateSenateId
        if (_tmpStateSenateId == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpStateSenateId)
        }
        val _tmpStateHouseId: String? = entity.stateHouseId
        if (_tmpStateHouseId == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpStateHouseId)
        }
        val _tmpCountyId: String? = entity.countyId
        if (_tmpCountyId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCountyId)
        }
        val _tmpCityId: String? = entity.cityId
        if (_tmpCityId == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpCityId)
        }
        val _tmpSchoolBoardId: String? = entity.schoolBoardId
        if (_tmpSchoolBoardId == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpSchoolBoardId)
        }
        val _tmp: String = __civicConverters.fromVerificationTier(entity.tier)
        statement.bindText(10, _tmp)
        val _tmpAvatarUrl: String? = entity.avatarUrl
        if (_tmpAvatarUrl == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpAvatarUrl)
        }
        statement.bindLong(12, entity.joinedAt)
        val _tmpAddressFingerprint: String? = entity.addressFingerprint
        if (_tmpAddressFingerprint == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpAddressFingerprint)
        }
        val _tmpVerifiedByPubKey: String? = entity.verifiedByPubKey
        if (_tmpVerifiedByPubKey == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpVerifiedByPubKey)
        }
        statement.bindLong(15, entity.cachedAt)
        statement.bindText(16, entity.pubKey)
      }
    })
  }

  public override suspend fun upsertProfile(profile: ResidentProfileEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfResidentProfileEntity.upsert(_connection, profile)
  }

  public override fun observeProfile(pubKey: String): Flow<ResidentProfileEntity?> {
    val _sql: String = "SELECT * FROM resident_profiles WHERE pubKey = ?"
    return createFlow(__db, false, arrayOf("resident_profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pubKey)
        val _columnIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _columnIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _columnIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _columnIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _columnIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _columnIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _columnIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _columnIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _columnIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt, "addressFingerprint")
        val _columnIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: ResidentProfileEntity?
        if (_stmt.step()) {
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_columnIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_columnIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_columnIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_columnIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_columnIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_columnIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_columnIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_columnIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_columnIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_columnIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_columnIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_columnIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_columnIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_columnIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_columnIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_columnIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_columnIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_columnIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_columnIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_columnIndexOfVerifiedByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _result = ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeProfilesByFingerprint(fingerprint: String): Flow<List<ResidentProfileEntity>> {
    val _sql: String = "SELECT * FROM resident_profiles WHERE addressFingerprint = ?"
    return createFlow(__db, false, arrayOf("resident_profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, fingerprint)
        val _columnIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _columnIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _columnIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _columnIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _columnIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _columnIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _columnIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _columnIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _columnIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt, "addressFingerprint")
        val _columnIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<ResidentProfileEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ResidentProfileEntity
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_columnIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_columnIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_columnIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_columnIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_columnIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_columnIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_columnIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_columnIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_columnIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_columnIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_columnIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_columnIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_columnIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_columnIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_columnIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_columnIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_columnIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_columnIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_columnIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_columnIndexOfVerifiedByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item = ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProfileCountByFingerprint(fingerprint: String): Int {
    val _sql: String = "SELECT COUNT(*) FROM resident_profiles WHERE addressFingerprint = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, fingerprint)
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

  public override suspend fun getVouchCount(notaryPubKey: String): Int {
    val _sql: String = "SELECT COUNT(*) FROM resident_profiles WHERE verifiedByPubKey = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, notaryPubKey)
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

  public override suspend fun getAllProfiles(): List<ResidentProfileEntity> {
    val _sql: String = "SELECT * FROM resident_profiles"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _columnIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _columnIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _columnIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _columnIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _columnIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _columnIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _columnIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _columnIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt, "addressFingerprint")
        val _columnIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<ResidentProfileEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ResidentProfileEntity
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_columnIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_columnIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_columnIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_columnIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_columnIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_columnIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_columnIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_columnIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_columnIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_columnIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_columnIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_columnIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_columnIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_columnIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_columnIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_columnIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_columnIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_columnIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_columnIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_columnIndexOfVerifiedByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item = ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProfile(pubKey: String): ResidentProfileEntity? {
    val _sql: String = "SELECT * FROM resident_profiles WHERE pubKey = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pubKey)
        val _columnIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _columnIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _columnIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _columnIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _columnIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _columnIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _columnIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _columnIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _columnIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt, "addressFingerprint")
        val _columnIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: ResidentProfileEntity?
        if (_stmt.step()) {
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_columnIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_columnIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_columnIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_columnIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_columnIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_columnIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_columnIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_columnIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_columnIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_columnIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_columnIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_columnIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_columnIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_columnIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_columnIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_columnIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_columnIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_columnIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_columnIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_columnIndexOfVerifiedByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _result = ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeProfilesVerifiedBy(verifierPubKey: String): Flow<List<ResidentProfileEntity>> {
    val _sql: String = "SELECT * FROM resident_profiles WHERE verifiedByPubKey = ?"
    return createFlow(__db, false, arrayOf("resident_profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, verifierPubKey)
        val _columnIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _columnIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _columnIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _columnIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _columnIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _columnIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _columnIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _columnIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _columnIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _columnIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _columnIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt, "addressFingerprint")
        val _columnIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _columnIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<ResidentProfileEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ResidentProfileEntity
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_columnIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_columnIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_columnIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_columnIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_columnIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_columnIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_columnIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_columnIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_columnIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_columnIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_columnIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_columnIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_columnIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_columnIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_columnIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_columnIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_columnIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_columnIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_columnIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_columnIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_columnIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_columnIndexOfVerifiedByPubKey)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_columnIndexOfCachedAt)
          _item = ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateTier(pubKey: String, tier: String) {
    val _sql: String = "UPDATE resident_profiles SET tier = ? WHERE pubKey = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, tier)
        _argIndex = 2
        _stmt.bindText(_argIndex, pubKey)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateTierWithFingerprint(
    pubKey: String,
    tier: String,
    fingerprint: String,
  ) {
    val _sql: String = "UPDATE resident_profiles SET tier = ?, addressFingerprint = ? WHERE pubKey = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, tier)
        _argIndex = 2
        _stmt.bindText(_argIndex, fingerprint)
        _argIndex = 3
        _stmt.bindText(_argIndex, pubKey)
        _stmt.step()
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
