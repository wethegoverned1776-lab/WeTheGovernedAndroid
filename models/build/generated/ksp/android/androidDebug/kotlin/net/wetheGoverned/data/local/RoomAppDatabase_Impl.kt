package net.wetheGoverned.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass
import net.wetheGoverned.`data`.local.dao.AccountDao
import net.wetheGoverned.`data`.local.dao.AccountDao_Impl
import net.wetheGoverned.`data`.local.dao.CommunityPostDao
import net.wetheGoverned.`data`.local.dao.CommunityPostDao_Impl
import net.wetheGoverned.`data`.local.dao.DistrictDao
import net.wetheGoverned.`data`.local.dao.DistrictDao_Impl
import net.wetheGoverned.`data`.local.dao.ManifestoDao
import net.wetheGoverned.`data`.local.dao.ManifestoDao_Impl
import net.wetheGoverned.`data`.local.dao.MetricDao
import net.wetheGoverned.`data`.local.dao.MetricDao_Impl
import net.wetheGoverned.`data`.local.dao.PendingEventDao
import net.wetheGoverned.`data`.local.dao.PendingEventDao_Impl
import net.wetheGoverned.`data`.local.dao.PollDao
import net.wetheGoverned.`data`.local.dao.PollDao_Impl
import net.wetheGoverned.`data`.local.dao.PollPostDao
import net.wetheGoverned.`data`.local.dao.PollPostDao_Impl
import net.wetheGoverned.`data`.local.dao.ResidentProfileDao
import net.wetheGoverned.`data`.local.dao.ResidentProfileDao_Impl
import net.wetheGoverned.`data`.local.dao.ScorecardDao
import net.wetheGoverned.`data`.local.dao.ScorecardDao_Impl
import net.wetheGoverned.`data`.local.dao.VerificationRequestDao
import net.wetheGoverned.`data`.local.dao.VerificationRequestDao_Impl
import net.wetheGoverned.`data`.local.dao.VoteDao
import net.wetheGoverned.`data`.local.dao.VoteDao_Impl

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RoomAppDatabase_Impl : RoomAppDatabase() {
  private val _districtDao: Lazy<DistrictDao> = lazy {
    DistrictDao_Impl(this)
  }

  private val _residentProfileDao: Lazy<ResidentProfileDao> = lazy {
    ResidentProfileDao_Impl(this)
  }

  private val _pollDao: Lazy<PollDao> = lazy {
    PollDao_Impl(this)
  }

  private val _pollPostDao: Lazy<PollPostDao> = lazy {
    PollPostDao_Impl(this)
  }

  private val _voteDao: Lazy<VoteDao> = lazy {
    VoteDao_Impl(this)
  }

  private val _scorecardDao: Lazy<ScorecardDao> = lazy {
    ScorecardDao_Impl(this)
  }

  private val _manifestoDao: Lazy<ManifestoDao> = lazy {
    ManifestoDao_Impl(this)
  }

  private val _metricDao: Lazy<MetricDao> = lazy {
    MetricDao_Impl(this)
  }

  private val _pendingEventDao: Lazy<PendingEventDao> = lazy {
    PendingEventDao_Impl(this)
  }

  private val _accountDao: Lazy<AccountDao> = lazy {
    AccountDao_Impl(this)
  }

  private val _communityPostDao: Lazy<CommunityPostDao> = lazy {
    CommunityPostDao_Impl(this)
  }

  private val _verificationRequestDao: Lazy<VerificationRequestDao> = lazy {
    VerificationRequestDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(11,
        "b8888c7ffc78fc716d0937918250083e", "159b26e1ffe25ac0f1af76a8b6c4bf37") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `districts` (`id` TEXT NOT NULL, `level` TEXT NOT NULL, `state` TEXT NOT NULL, `districtNumber` INTEGER, `displayName` TEXT NOT NULL, `representativeName` TEXT, `representativeParty` TEXT, `geoBoundaries` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `resident_profiles` (`pubKey` TEXT NOT NULL, `displayName` TEXT NOT NULL, `federalHouseId` TEXT, `federalSenateId` TEXT, `stateSenateId` TEXT, `stateHouseId` TEXT, `countyId` TEXT, `cityId` TEXT, `schoolBoardId` TEXT, `tier` TEXT NOT NULL, `avatarUrl` TEXT, `joinedAt` INTEGER NOT NULL, `addressFingerprint` TEXT, `verifiedByPubKey` TEXT, `address` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`pubKey`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `district_polls` (`id` TEXT NOT NULL, `scope` TEXT, `districtId` TEXT NOT NULL, `localId` TEXT, `authorPubKey` TEXT NOT NULL, `question` TEXT NOT NULL, `optionsJson` TEXT NOT NULL, `status` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `closesAt` INTEGER, `totalVotes` INTEGER NOT NULL, `importanceScore` INTEGER NOT NULL, `userImportanceVote` INTEGER NOT NULL, `residentVoteOption` TEXT, `linkedLegislationId` TEXT, `districtBreakdownJson` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `poll_posts` (`id` TEXT NOT NULL, `pollId` TEXT NOT NULL, `optionId` TEXT NOT NULL, `parentPostId` TEXT, `headline` TEXT, `authorName` TEXT NOT NULL, `content` TEXT NOT NULL, `score` INTEGER NOT NULL, `userVote` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `poll_votes` (`id` TEXT NOT NULL, `pollId` TEXT NOT NULL, `voterPubKey` TEXT NOT NULL, `voterName` TEXT NOT NULL, `optionId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `nonce` INTEGER NOT NULL, `signature` TEXT, `isFlagged` INTEGER NOT NULL, `flagReason` TEXT, `disputeComment` TEXT, `disputeExpiresAt` INTEGER, `status` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `representative_scorecards` (`districtId` TEXT NOT NULL, `representativePubKey` TEXT NOT NULL, `name` TEXT NOT NULL, `party` TEXT NOT NULL, `overallScore` INTEGER NOT NULL, `lastUpdated` INTEGER NOT NULL, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`districtId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `scorecard_categories` (`rowId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `districtId` TEXT NOT NULL, `categoryName` TEXT NOT NULL, `officialValue` TEXT NOT NULL, `residentReportedValue` TEXT, `score` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `candidate_manifestos` (`id` TEXT NOT NULL, `candidatePubKey` TEXT NOT NULL, `districtId` TEXT NOT NULL, `scope` TEXT NOT NULL, `title` TEXT NOT NULL, `body` TEXT NOT NULL, `publishedAt` INTEGER NOT NULL, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `manifesto_questions` (`id` TEXT NOT NULL, `manifestoId` TEXT NOT NULL, `askerPubKey` TEXT NOT NULL, `text` TEXT NOT NULL, `askedAt` INTEGER NOT NULL, `answer` TEXT, `answeredAt` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `district_metrics` (`id` TEXT NOT NULL, `districtId` TEXT NOT NULL, `category` TEXT NOT NULL, `name` TEXT NOT NULL, `officialValue` TEXT NOT NULL, `residentValue` TEXT, `unit` TEXT NOT NULL, `source` TEXT NOT NULL, `reportedAt` INTEGER NOT NULL, `reporterPubKey` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `pending_civic_events` (`eventId` TEXT NOT NULL, `kind` INTEGER NOT NULL, `contentJson` TEXT NOT NULL, `sig` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `retryCount` INTEGER NOT NULL, PRIMARY KEY(`eventId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `user_accounts` (`username` TEXT NOT NULL, `password` TEXT NOT NULL, `pubKey` TEXT NOT NULL, `privateKey` TEXT NOT NULL, `quantumPubKey` TEXT, `quantumPrivateKey` TEXT, `districtId` TEXT, `createdAt` INTEGER NOT NULL, `requiresPasswordChange` INTEGER NOT NULL, PRIMARY KEY(`username`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `community_posts` (`id` TEXT NOT NULL, `authorPubKey` TEXT NOT NULL, `districtId` TEXT NOT NULL, `kind` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `price` REAL, `location` TEXT, `contactInfo` TEXT, `createdAt` INTEGER NOT NULL, `expiresAt` INTEGER, `tagsJson` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `verification_requests` (`id` TEXT NOT NULL, `requesterPubKey` TEXT NOT NULL, `requesterDisplayName` TEXT NOT NULL, `email` TEXT NOT NULL, `districtId` TEXT NOT NULL, `stateId` TEXT NOT NULL, `address` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `status` TEXT NOT NULL, `handledByPubKey` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b8888c7ffc78fc716d0937918250083e')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `districts`")
        connection.execSQL("DROP TABLE IF EXISTS `resident_profiles`")
        connection.execSQL("DROP TABLE IF EXISTS `district_polls`")
        connection.execSQL("DROP TABLE IF EXISTS `poll_posts`")
        connection.execSQL("DROP TABLE IF EXISTS `poll_votes`")
        connection.execSQL("DROP TABLE IF EXISTS `representative_scorecards`")
        connection.execSQL("DROP TABLE IF EXISTS `scorecard_categories`")
        connection.execSQL("DROP TABLE IF EXISTS `candidate_manifestos`")
        connection.execSQL("DROP TABLE IF EXISTS `manifesto_questions`")
        connection.execSQL("DROP TABLE IF EXISTS `district_metrics`")
        connection.execSQL("DROP TABLE IF EXISTS `pending_civic_events`")
        connection.execSQL("DROP TABLE IF EXISTS `user_accounts`")
        connection.execSQL("DROP TABLE IF EXISTS `community_posts`")
        connection.execSQL("DROP TABLE IF EXISTS `verification_requests`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsDistricts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDistricts.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("level", TableInfo.Column("level", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("state", TableInfo.Column("state", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("districtNumber", TableInfo.Column("districtNumber", "INTEGER", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("displayName", TableInfo.Column("displayName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("representativeName", TableInfo.Column("representativeName", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("representativeParty", TableInfo.Column("representativeParty", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("geoBoundaries", TableInfo.Column("geoBoundaries", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistricts.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDistricts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDistricts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDistricts: TableInfo = TableInfo("districts", _columnsDistricts,
            _foreignKeysDistricts, _indicesDistricts)
        val _existingDistricts: TableInfo = read(connection, "districts")
        if (!_infoDistricts.equals(_existingDistricts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |districts(net.wetheGoverned.data.local.entity.DistrictEntity).
              | Expected:
              |""".trimMargin() + _infoDistricts + """
              |
              | Found:
              |""".trimMargin() + _existingDistricts)
        }
        val _columnsResidentProfiles: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsResidentProfiles.put("pubKey", TableInfo.Column("pubKey", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("displayName", TableInfo.Column("displayName", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("federalHouseId", TableInfo.Column("federalHouseId", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("federalSenateId", TableInfo.Column("federalSenateId", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("stateSenateId", TableInfo.Column("stateSenateId", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("stateHouseId", TableInfo.Column("stateHouseId", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("countyId", TableInfo.Column("countyId", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("cityId", TableInfo.Column("cityId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("schoolBoardId", TableInfo.Column("schoolBoardId", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("tier", TableInfo.Column("tier", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("avatarUrl", TableInfo.Column("avatarUrl", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("joinedAt", TableInfo.Column("joinedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("addressFingerprint", TableInfo.Column("addressFingerprint",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("verifiedByPubKey", TableInfo.Column("verifiedByPubKey",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("address", TableInfo.Column("address", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsResidentProfiles.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysResidentProfiles: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesResidentProfiles: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoResidentProfiles: TableInfo = TableInfo("resident_profiles",
            _columnsResidentProfiles, _foreignKeysResidentProfiles, _indicesResidentProfiles)
        val _existingResidentProfiles: TableInfo = read(connection, "resident_profiles")
        if (!_infoResidentProfiles.equals(_existingResidentProfiles)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |resident_profiles(net.wetheGoverned.data.local.entity.ResidentProfileEntity).
              | Expected:
              |""".trimMargin() + _infoResidentProfiles + """
              |
              | Found:
              |""".trimMargin() + _existingResidentProfiles)
        }
        val _columnsDistrictPolls: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDistrictPolls.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("scope", TableInfo.Column("scope", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("districtId", TableInfo.Column("districtId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("localId", TableInfo.Column("localId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("authorPubKey", TableInfo.Column("authorPubKey", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("question", TableInfo.Column("question", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("optionsJson", TableInfo.Column("optionsJson", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("closesAt", TableInfo.Column("closesAt", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("totalVotes", TableInfo.Column("totalVotes", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("importanceScore", TableInfo.Column("importanceScore", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("userImportanceVote", TableInfo.Column("userImportanceVote",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("residentVoteOption", TableInfo.Column("residentVoteOption",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("linkedLegislationId", TableInfo.Column("linkedLegislationId",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("districtBreakdownJson", TableInfo.Column("districtBreakdownJson",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictPolls.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDistrictPolls: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDistrictPolls: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDistrictPolls: TableInfo = TableInfo("district_polls", _columnsDistrictPolls,
            _foreignKeysDistrictPolls, _indicesDistrictPolls)
        val _existingDistrictPolls: TableInfo = read(connection, "district_polls")
        if (!_infoDistrictPolls.equals(_existingDistrictPolls)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |district_polls(net.wetheGoverned.data.local.entity.DistrictPollEntity).
              | Expected:
              |""".trimMargin() + _infoDistrictPolls + """
              |
              | Found:
              |""".trimMargin() + _existingDistrictPolls)
        }
        val _columnsPollPosts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPollPosts.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("pollId", TableInfo.Column("pollId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("optionId", TableInfo.Column("optionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("parentPostId", TableInfo.Column("parentPostId", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("headline", TableInfo.Column("headline", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("authorName", TableInfo.Column("authorName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("content", TableInfo.Column("content", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("score", TableInfo.Column("score", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("userVote", TableInfo.Column("userVote", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollPosts.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPollPosts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPollPosts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPollPosts: TableInfo = TableInfo("poll_posts", _columnsPollPosts,
            _foreignKeysPollPosts, _indicesPollPosts)
        val _existingPollPosts: TableInfo = read(connection, "poll_posts")
        if (!_infoPollPosts.equals(_existingPollPosts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |poll_posts(net.wetheGoverned.data.local.entity.PollPostEntity).
              | Expected:
              |""".trimMargin() + _infoPollPosts + """
              |
              | Found:
              |""".trimMargin() + _existingPollPosts)
        }
        val _columnsPollVotes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPollVotes.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("pollId", TableInfo.Column("pollId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("voterPubKey", TableInfo.Column("voterPubKey", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("voterName", TableInfo.Column("voterName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("optionId", TableInfo.Column("optionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("nonce", TableInfo.Column("nonce", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("signature", TableInfo.Column("signature", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("isFlagged", TableInfo.Column("isFlagged", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("flagReason", TableInfo.Column("flagReason", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("disputeComment", TableInfo.Column("disputeComment", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("disputeExpiresAt", TableInfo.Column("disputeExpiresAt", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPollVotes.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPollVotes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPollVotes: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPollVotes: TableInfo = TableInfo("poll_votes", _columnsPollVotes,
            _foreignKeysPollVotes, _indicesPollVotes)
        val _existingPollVotes: TableInfo = read(connection, "poll_votes")
        if (!_infoPollVotes.equals(_existingPollVotes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |poll_votes(net.wetheGoverned.data.local.entity.CivicVoteEntity).
              | Expected:
              |""".trimMargin() + _infoPollVotes + """
              |
              | Found:
              |""".trimMargin() + _existingPollVotes)
        }
        val _columnsRepresentativeScorecards: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRepresentativeScorecards.put("districtId", TableInfo.Column("districtId", "TEXT",
            true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRepresentativeScorecards.put("representativePubKey",
            TableInfo.Column("representativePubKey", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRepresentativeScorecards.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRepresentativeScorecards.put("party", TableInfo.Column("party", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRepresentativeScorecards.put("overallScore", TableInfo.Column("overallScore",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRepresentativeScorecards.put("lastUpdated", TableInfo.Column("lastUpdated",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRepresentativeScorecards.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRepresentativeScorecards: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRepresentativeScorecards: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRepresentativeScorecards: TableInfo = TableInfo("representative_scorecards",
            _columnsRepresentativeScorecards, _foreignKeysRepresentativeScorecards,
            _indicesRepresentativeScorecards)
        val _existingRepresentativeScorecards: TableInfo = read(connection,
            "representative_scorecards")
        if (!_infoRepresentativeScorecards.equals(_existingRepresentativeScorecards)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |representative_scorecards(net.wetheGoverned.data.local.entity.RepresentativeScorecardEntity).
              | Expected:
              |""".trimMargin() + _infoRepresentativeScorecards + """
              |
              | Found:
              |""".trimMargin() + _existingRepresentativeScorecards)
        }
        val _columnsScorecardCategories: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsScorecardCategories.put("rowId", TableInfo.Column("rowId", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsScorecardCategories.put("districtId", TableInfo.Column("districtId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsScorecardCategories.put("categoryName", TableInfo.Column("categoryName", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsScorecardCategories.put("officialValue", TableInfo.Column("officialValue", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsScorecardCategories.put("residentReportedValue",
            TableInfo.Column("residentReportedValue", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsScorecardCategories.put("score", TableInfo.Column("score", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysScorecardCategories: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesScorecardCategories: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoScorecardCategories: TableInfo = TableInfo("scorecard_categories",
            _columnsScorecardCategories, _foreignKeysScorecardCategories,
            _indicesScorecardCategories)
        val _existingScorecardCategories: TableInfo = read(connection, "scorecard_categories")
        if (!_infoScorecardCategories.equals(_existingScorecardCategories)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |scorecard_categories(net.wetheGoverned.data.local.entity.ScorecardCategoryEntity).
              | Expected:
              |""".trimMargin() + _infoScorecardCategories + """
              |
              | Found:
              |""".trimMargin() + _existingScorecardCategories)
        }
        val _columnsCandidateManifestos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCandidateManifestos.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCandidateManifestos.put("candidatePubKey", TableInfo.Column("candidatePubKey",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCandidateManifestos.put("districtId", TableInfo.Column("districtId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCandidateManifestos.put("scope", TableInfo.Column("scope", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCandidateManifestos.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCandidateManifestos.put("body", TableInfo.Column("body", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCandidateManifestos.put("publishedAt", TableInfo.Column("publishedAt", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCandidateManifestos.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCandidateManifestos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCandidateManifestos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCandidateManifestos: TableInfo = TableInfo("candidate_manifestos",
            _columnsCandidateManifestos, _foreignKeysCandidateManifestos,
            _indicesCandidateManifestos)
        val _existingCandidateManifestos: TableInfo = read(connection, "candidate_manifestos")
        if (!_infoCandidateManifestos.equals(_existingCandidateManifestos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |candidate_manifestos(net.wetheGoverned.data.local.entity.CandidateManifestoEntity).
              | Expected:
              |""".trimMargin() + _infoCandidateManifestos + """
              |
              | Found:
              |""".trimMargin() + _existingCandidateManifestos)
        }
        val _columnsManifestoQuestions: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsManifestoQuestions.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsManifestoQuestions.put("manifestoId", TableInfo.Column("manifestoId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsManifestoQuestions.put("askerPubKey", TableInfo.Column("askerPubKey", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsManifestoQuestions.put("text", TableInfo.Column("text", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsManifestoQuestions.put("askedAt", TableInfo.Column("askedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsManifestoQuestions.put("answer", TableInfo.Column("answer", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsManifestoQuestions.put("answeredAt", TableInfo.Column("answeredAt", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysManifestoQuestions: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesManifestoQuestions: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoManifestoQuestions: TableInfo = TableInfo("manifesto_questions",
            _columnsManifestoQuestions, _foreignKeysManifestoQuestions, _indicesManifestoQuestions)
        val _existingManifestoQuestions: TableInfo = read(connection, "manifesto_questions")
        if (!_infoManifestoQuestions.equals(_existingManifestoQuestions)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |manifesto_questions(net.wetheGoverned.data.local.entity.ManifestoQuestionEntity).
              | Expected:
              |""".trimMargin() + _infoManifestoQuestions + """
              |
              | Found:
              |""".trimMargin() + _existingManifestoQuestions)
        }
        val _columnsDistrictMetrics: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDistrictMetrics.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("districtId", TableInfo.Column("districtId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("category", TableInfo.Column("category", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("officialValue", TableInfo.Column("officialValue", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("residentValue", TableInfo.Column("residentValue", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("unit", TableInfo.Column("unit", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("source", TableInfo.Column("source", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("reportedAt", TableInfo.Column("reportedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("reporterPubKey", TableInfo.Column("reporterPubKey", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDistrictMetrics.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDistrictMetrics: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDistrictMetrics: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDistrictMetrics: TableInfo = TableInfo("district_metrics", _columnsDistrictMetrics,
            _foreignKeysDistrictMetrics, _indicesDistrictMetrics)
        val _existingDistrictMetrics: TableInfo = read(connection, "district_metrics")
        if (!_infoDistrictMetrics.equals(_existingDistrictMetrics)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |district_metrics(net.wetheGoverned.data.local.entity.DistrictMetricEntity).
              | Expected:
              |""".trimMargin() + _infoDistrictMetrics + """
              |
              | Found:
              |""".trimMargin() + _existingDistrictMetrics)
        }
        val _columnsPendingCivicEvents: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPendingCivicEvents.put("eventId", TableInfo.Column("eventId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPendingCivicEvents.put("kind", TableInfo.Column("kind", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPendingCivicEvents.put("contentJson", TableInfo.Column("contentJson", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPendingCivicEvents.put("sig", TableInfo.Column("sig", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPendingCivicEvents.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPendingCivicEvents.put("retryCount", TableInfo.Column("retryCount", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPendingCivicEvents: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPendingCivicEvents: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPendingCivicEvents: TableInfo = TableInfo("pending_civic_events",
            _columnsPendingCivicEvents, _foreignKeysPendingCivicEvents, _indicesPendingCivicEvents)
        val _existingPendingCivicEvents: TableInfo = read(connection, "pending_civic_events")
        if (!_infoPendingCivicEvents.equals(_existingPendingCivicEvents)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |pending_civic_events(net.wetheGoverned.data.local.entity.PendingCivicEventEntity).
              | Expected:
              |""".trimMargin() + _infoPendingCivicEvents + """
              |
              | Found:
              |""".trimMargin() + _existingPendingCivicEvents)
        }
        val _columnsUserAccounts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUserAccounts.put("username", TableInfo.Column("username", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("password", TableInfo.Column("password", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("pubKey", TableInfo.Column("pubKey", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("privateKey", TableInfo.Column("privateKey", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("quantumPubKey", TableInfo.Column("quantumPubKey", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("quantumPrivateKey", TableInfo.Column("quantumPrivateKey", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("districtId", TableInfo.Column("districtId", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserAccounts.put("requiresPasswordChange",
            TableInfo.Column("requiresPasswordChange", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUserAccounts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUserAccounts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUserAccounts: TableInfo = TableInfo("user_accounts", _columnsUserAccounts,
            _foreignKeysUserAccounts, _indicesUserAccounts)
        val _existingUserAccounts: TableInfo = read(connection, "user_accounts")
        if (!_infoUserAccounts.equals(_existingUserAccounts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |user_accounts(net.wetheGoverned.data.local.entity.AccountEntity).
              | Expected:
              |""".trimMargin() + _infoUserAccounts + """
              |
              | Found:
              |""".trimMargin() + _existingUserAccounts)
        }
        val _columnsCommunityPosts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCommunityPosts.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("authorPubKey", TableInfo.Column("authorPubKey", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("districtId", TableInfo.Column("districtId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("kind", TableInfo.Column("kind", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("description", TableInfo.Column("description", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("price", TableInfo.Column("price", "REAL", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("location", TableInfo.Column("location", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("contactInfo", TableInfo.Column("contactInfo", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("expiresAt", TableInfo.Column("expiresAt", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("tagsJson", TableInfo.Column("tagsJson", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCommunityPosts.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCommunityPosts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCommunityPosts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCommunityPosts: TableInfo = TableInfo("community_posts", _columnsCommunityPosts,
            _foreignKeysCommunityPosts, _indicesCommunityPosts)
        val _existingCommunityPosts: TableInfo = read(connection, "community_posts")
        if (!_infoCommunityPosts.equals(_existingCommunityPosts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |community_posts(net.wetheGoverned.data.local.entity.CommunityPostEntity).
              | Expected:
              |""".trimMargin() + _infoCommunityPosts + """
              |
              | Found:
              |""".trimMargin() + _existingCommunityPosts)
        }
        val _columnsVerificationRequests: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsVerificationRequests.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("requesterPubKey", TableInfo.Column("requesterPubKey",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("requesterDisplayName",
            TableInfo.Column("requesterDisplayName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("districtId", TableInfo.Column("districtId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("stateId", TableInfo.Column("stateId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("address", TableInfo.Column("address", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("handledByPubKey", TableInfo.Column("handledByPubKey",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVerificationRequests.put("cachedAt", TableInfo.Column("cachedAt", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysVerificationRequests: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesVerificationRequests: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoVerificationRequests: TableInfo = TableInfo("verification_requests",
            _columnsVerificationRequests, _foreignKeysVerificationRequests,
            _indicesVerificationRequests)
        val _existingVerificationRequests: TableInfo = read(connection, "verification_requests")
        if (!_infoVerificationRequests.equals(_existingVerificationRequests)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |verification_requests(net.wetheGoverned.data.local.entity.VerificationRequestEntity).
              | Expected:
              |""".trimMargin() + _infoVerificationRequests + """
              |
              | Found:
              |""".trimMargin() + _existingVerificationRequests)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "districts",
        "resident_profiles", "district_polls", "poll_posts", "poll_votes",
        "representative_scorecards", "scorecard_categories", "candidate_manifestos",
        "manifesto_questions", "district_metrics", "pending_civic_events", "user_accounts",
        "community_posts", "verification_requests")
  }

  public override fun clearAllTables() {
    super.performClear(false, "districts", "resident_profiles", "district_polls", "poll_posts",
        "poll_votes", "representative_scorecards", "scorecard_categories", "candidate_manifestos",
        "manifesto_questions", "district_metrics", "pending_civic_events", "user_accounts",
        "community_posts", "verification_requests")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(DistrictDao::class, DistrictDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ResidentProfileDao::class,
        ResidentProfileDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PollDao::class, PollDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PollPostDao::class, PollPostDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(VoteDao::class, VoteDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ScorecardDao::class, ScorecardDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ManifestoDao::class, ManifestoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(MetricDao::class, MetricDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PendingEventDao::class, PendingEventDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AccountDao::class, AccountDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CommunityPostDao::class, CommunityPostDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(VerificationRequestDao::class,
        VerificationRequestDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun districtDao(): DistrictDao = _districtDao.value

  public override fun residentProfileDao(): ResidentProfileDao = _residentProfileDao.value

  public override fun pollDao(): PollDao = _pollDao.value

  public override fun pollPostDao(): PollPostDao = _pollPostDao.value

  public override fun voteDao(): VoteDao = _voteDao.value

  public override fun scorecardDao(): ScorecardDao = _scorecardDao.value

  public override fun manifestoDao(): ManifestoDao = _manifestoDao.value

  public override fun metricDao(): MetricDao = _metricDao.value

  public override fun pendingEventDao(): PendingEventDao = _pendingEventDao.value

  public override fun accountDao(): AccountDao = _accountDao.value

  public override fun communityPostDao(): CommunityPostDao = _communityPostDao.value

  public override fun verificationRequestDao(): VerificationRequestDao =
      _verificationRequestDao.value
}
