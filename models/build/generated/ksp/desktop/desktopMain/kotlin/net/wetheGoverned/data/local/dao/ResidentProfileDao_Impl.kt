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
import net.wetheGoverned.`data`.local.entity.ResidentProfileEntity
import net.wetheGoverned.model.VerificationTier

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ResidentProfileDao_Impl(
  __db: RoomDatabase,
) : ResidentProfileDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfResidentProfileEntity: EntityUpsertAdapter<ResidentProfileEntity>

  private val __civicConverters: CivicConverters = CivicConverters()
  init {
    this.__db = __db
    this.__upsertAdapterOfResidentProfileEntity = EntityUpsertAdapter<ResidentProfileEntity>(object
        : EntityInsertAdapter<ResidentProfileEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `resident_profiles` (`pubKey`,`displayName`,`federalHouseId`,`federalSenateId`,`stateSenateId`,`stateHouseId`,`countyId`,`cityId`,`schoolBoardId`,`tier`,`avatarUrl`,`joinedAt`,`addressFingerprint`,`verifiedByPubKey`,`address`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

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
        val _tmpAddress: String? = entity.address
        if (_tmpAddress == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpAddress)
        }
        statement.bindLong(16, entity.cachedAt)
      }
    }, object : EntityDeleteOrUpdateAdapter<ResidentProfileEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `resident_profiles` SET `pubKey` = ?,`displayName` = ?,`federalHouseId` = ?,`federalSenateId` = ?,`stateSenateId` = ?,`stateHouseId` = ?,`countyId` = ?,`cityId` = ?,`schoolBoardId` = ?,`tier` = ?,`avatarUrl` = ?,`joinedAt` = ?,`addressFingerprint` = ?,`verifiedByPubKey` = ?,`address` = ?,`cachedAt` = ? WHERE `pubKey` = ?"

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
        val _tmpAddress: String? = entity.address
        if (_tmpAddress == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpAddress)
        }
        statement.bindLong(16, entity.cachedAt)
        statement.bindText(17, entity.pubKey)
      }
    })
  }

  public override suspend fun upsertProfile(profile: ResidentProfileEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfResidentProfileEntity.upsert(_connection, profile)
  }

  public override fun observeProfile(pubKey: String): Flow<ResidentProfileEntity?> {
    val _sql: String = "SELECT * FROM resident_profiles WHERE pubKey = ?"
    return createFlow(__db, false, arrayOf("resident_profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pubKey)
        val _cursorIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _cursorIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _cursorIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _cursorIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _cursorIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _cursorIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _cursorIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _cursorIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _cursorIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _cursorIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _cursorIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _cursorIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _cursorIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt,
            "addressFingerprint")
        val _cursorIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _cursorIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: ResidentProfileEntity?
        if (_stmt.step()) {
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_cursorIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_cursorIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_cursorIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_cursorIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_cursorIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_cursorIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_cursorIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_cursorIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_cursorIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_cursorIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_cursorIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_cursorIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_cursorIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_cursorIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_cursorIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_cursorIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_cursorIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_cursorIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_cursorIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_cursorIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_cursorIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_cursorIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_cursorIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_cursorIndexOfVerifiedByPubKey)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_cursorIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_cursorIndexOfAddress)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _result =
              ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpAddress,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeProfilesByFingerprint(fingerprint: String):
      Flow<List<ResidentProfileEntity>> {
    val _sql: String = "SELECT * FROM resident_profiles WHERE addressFingerprint = ?"
    return createFlow(__db, false, arrayOf("resident_profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, fingerprint)
        val _cursorIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _cursorIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _cursorIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _cursorIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _cursorIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _cursorIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _cursorIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _cursorIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _cursorIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _cursorIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _cursorIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _cursorIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _cursorIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt,
            "addressFingerprint")
        val _cursorIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _cursorIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<ResidentProfileEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ResidentProfileEntity
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_cursorIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_cursorIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_cursorIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_cursorIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_cursorIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_cursorIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_cursorIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_cursorIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_cursorIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_cursorIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_cursorIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_cursorIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_cursorIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_cursorIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_cursorIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_cursorIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_cursorIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_cursorIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_cursorIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_cursorIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_cursorIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_cursorIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_cursorIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_cursorIndexOfVerifiedByPubKey)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_cursorIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_cursorIndexOfAddress)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item =
              ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpAddress,_tmpCachedAt)
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
        val _cursorIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _cursorIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _cursorIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _cursorIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _cursorIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _cursorIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _cursorIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _cursorIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _cursorIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _cursorIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _cursorIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _cursorIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _cursorIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt,
            "addressFingerprint")
        val _cursorIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _cursorIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<ResidentProfileEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ResidentProfileEntity
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_cursorIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_cursorIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_cursorIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_cursorIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_cursorIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_cursorIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_cursorIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_cursorIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_cursorIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_cursorIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_cursorIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_cursorIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_cursorIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_cursorIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_cursorIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_cursorIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_cursorIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_cursorIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_cursorIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_cursorIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_cursorIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_cursorIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_cursorIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_cursorIndexOfVerifiedByPubKey)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_cursorIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_cursorIndexOfAddress)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item =
              ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpAddress,_tmpCachedAt)
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
        val _cursorIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _cursorIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _cursorIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _cursorIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _cursorIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _cursorIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _cursorIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _cursorIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _cursorIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _cursorIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _cursorIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _cursorIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _cursorIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt,
            "addressFingerprint")
        val _cursorIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _cursorIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: ResidentProfileEntity?
        if (_stmt.step()) {
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_cursorIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_cursorIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_cursorIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_cursorIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_cursorIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_cursorIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_cursorIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_cursorIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_cursorIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_cursorIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_cursorIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_cursorIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_cursorIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_cursorIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_cursorIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_cursorIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_cursorIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_cursorIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_cursorIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_cursorIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_cursorIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_cursorIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_cursorIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_cursorIndexOfVerifiedByPubKey)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_cursorIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_cursorIndexOfAddress)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _result =
              ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpAddress,_tmpCachedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeProfilesVerifiedBy(verifierPubKey: String):
      Flow<List<ResidentProfileEntity>> {
    val _sql: String = "SELECT * FROM resident_profiles WHERE verifiedByPubKey = ?"
    return createFlow(__db, false, arrayOf("resident_profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, verifierPubKey)
        val _cursorIndexOfPubKey: Int = getColumnIndexOrThrow(_stmt, "pubKey")
        val _cursorIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _cursorIndexOfFederalHouseId: Int = getColumnIndexOrThrow(_stmt, "federalHouseId")
        val _cursorIndexOfFederalSenateId: Int = getColumnIndexOrThrow(_stmt, "federalSenateId")
        val _cursorIndexOfStateSenateId: Int = getColumnIndexOrThrow(_stmt, "stateSenateId")
        val _cursorIndexOfStateHouseId: Int = getColumnIndexOrThrow(_stmt, "stateHouseId")
        val _cursorIndexOfCountyId: Int = getColumnIndexOrThrow(_stmt, "countyId")
        val _cursorIndexOfCityId: Int = getColumnIndexOrThrow(_stmt, "cityId")
        val _cursorIndexOfSchoolBoardId: Int = getColumnIndexOrThrow(_stmt, "schoolBoardId")
        val _cursorIndexOfTier: Int = getColumnIndexOrThrow(_stmt, "tier")
        val _cursorIndexOfAvatarUrl: Int = getColumnIndexOrThrow(_stmt, "avatarUrl")
        val _cursorIndexOfJoinedAt: Int = getColumnIndexOrThrow(_stmt, "joinedAt")
        val _cursorIndexOfAddressFingerprint: Int = getColumnIndexOrThrow(_stmt,
            "addressFingerprint")
        val _cursorIndexOfVerifiedByPubKey: Int = getColumnIndexOrThrow(_stmt, "verifiedByPubKey")
        val _cursorIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _cursorIndexOfCachedAt: Int = getColumnIndexOrThrow(_stmt, "cachedAt")
        val _result: MutableList<ResidentProfileEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ResidentProfileEntity
          val _tmpPubKey: String
          _tmpPubKey = _stmt.getText(_cursorIndexOfPubKey)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_cursorIndexOfDisplayName)
          val _tmpFederalHouseId: String?
          if (_stmt.isNull(_cursorIndexOfFederalHouseId)) {
            _tmpFederalHouseId = null
          } else {
            _tmpFederalHouseId = _stmt.getText(_cursorIndexOfFederalHouseId)
          }
          val _tmpFederalSenateId: String?
          if (_stmt.isNull(_cursorIndexOfFederalSenateId)) {
            _tmpFederalSenateId = null
          } else {
            _tmpFederalSenateId = _stmt.getText(_cursorIndexOfFederalSenateId)
          }
          val _tmpStateSenateId: String?
          if (_stmt.isNull(_cursorIndexOfStateSenateId)) {
            _tmpStateSenateId = null
          } else {
            _tmpStateSenateId = _stmt.getText(_cursorIndexOfStateSenateId)
          }
          val _tmpStateHouseId: String?
          if (_stmt.isNull(_cursorIndexOfStateHouseId)) {
            _tmpStateHouseId = null
          } else {
            _tmpStateHouseId = _stmt.getText(_cursorIndexOfStateHouseId)
          }
          val _tmpCountyId: String?
          if (_stmt.isNull(_cursorIndexOfCountyId)) {
            _tmpCountyId = null
          } else {
            _tmpCountyId = _stmt.getText(_cursorIndexOfCountyId)
          }
          val _tmpCityId: String?
          if (_stmt.isNull(_cursorIndexOfCityId)) {
            _tmpCityId = null
          } else {
            _tmpCityId = _stmt.getText(_cursorIndexOfCityId)
          }
          val _tmpSchoolBoardId: String?
          if (_stmt.isNull(_cursorIndexOfSchoolBoardId)) {
            _tmpSchoolBoardId = null
          } else {
            _tmpSchoolBoardId = _stmt.getText(_cursorIndexOfSchoolBoardId)
          }
          val _tmpTier: VerificationTier
          val _tmp: String
          _tmp = _stmt.getText(_cursorIndexOfTier)
          _tmpTier = __civicConverters.toVerificationTier(_tmp)
          val _tmpAvatarUrl: String?
          if (_stmt.isNull(_cursorIndexOfAvatarUrl)) {
            _tmpAvatarUrl = null
          } else {
            _tmpAvatarUrl = _stmt.getText(_cursorIndexOfAvatarUrl)
          }
          val _tmpJoinedAt: Long
          _tmpJoinedAt = _stmt.getLong(_cursorIndexOfJoinedAt)
          val _tmpAddressFingerprint: String?
          if (_stmt.isNull(_cursorIndexOfAddressFingerprint)) {
            _tmpAddressFingerprint = null
          } else {
            _tmpAddressFingerprint = _stmt.getText(_cursorIndexOfAddressFingerprint)
          }
          val _tmpVerifiedByPubKey: String?
          if (_stmt.isNull(_cursorIndexOfVerifiedByPubKey)) {
            _tmpVerifiedByPubKey = null
          } else {
            _tmpVerifiedByPubKey = _stmt.getText(_cursorIndexOfVerifiedByPubKey)
          }
          val _tmpAddress: String?
          if (_stmt.isNull(_cursorIndexOfAddress)) {
            _tmpAddress = null
          } else {
            _tmpAddress = _stmt.getText(_cursorIndexOfAddress)
          }
          val _tmpCachedAt: Long
          _tmpCachedAt = _stmt.getLong(_cursorIndexOfCachedAt)
          _item =
              ResidentProfileEntity(_tmpPubKey,_tmpDisplayName,_tmpFederalHouseId,_tmpFederalSenateId,_tmpStateSenateId,_tmpStateHouseId,_tmpCountyId,_tmpCityId,_tmpSchoolBoardId,_tmpTier,_tmpAvatarUrl,_tmpJoinedAt,_tmpAddressFingerprint,_tmpVerifiedByPubKey,_tmpAddress,_tmpCachedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun evictStaleProfiles(before: Long, adminPubKey: String) {
    val _sql: String = "DELETE FROM resident_profiles WHERE cachedAt < ? AND pubKey != ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, before)
        _argIndex = 2
        _stmt.bindText(_argIndex, adminPubKey)
        _stmt.step()
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
    val _sql: String =
        "UPDATE resident_profiles SET tier = ?, addressFingerprint = ? WHERE pubKey = ?"
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
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
