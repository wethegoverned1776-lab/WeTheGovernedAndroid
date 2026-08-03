package net.wetheGoverned.data.local.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import net.wetheGoverned.model.*

@Entity(tableName = "districts")
data class DistrictEntity(
    @PrimaryKey val id: String,
    val level: DistrictLevel,
    val state: String,
    val districtNumber: Int?,
    val displayName: String,
    val representativeName: String?,
    val representativeParty: String?,
    val cachedAt: Long,
)

@Entity(tableName = "resident_profiles")
data class ResidentProfileEntity(
    @PrimaryKey val pubKey: String,
    val displayName: String,
    val federalHouseId: String?,
    val federalSenateId: String?,
    val stateSenateId: String?,
    val stateHouseId: String?,
    val countyId: String?,
    val cityId: String?,
    val schoolBoardId: String?,
    val tier: VerificationTier,
    val avatarUrl: String?,
    val joinedAt: Long,
    val addressFingerprint: String?,
    val verifiedByPubKey: String? = null,
    val cachedAt: Long,
)

@Entity(tableName = "district_polls")
data class DistrictPollEntity(
    @PrimaryKey val id: String,
    val scope: PollScope?,
    val districtId: String,
    val localId: String?,
    val authorPubKey: String,
    val question: String,
    val optionsJson: String,
    val status: PollStatus,
    val createdAt: Long,
    val closesAt: Long?,
    val totalVotes: Int,
    val importanceScore: Int = 0,
    val userImportanceVote: Int = 0,
    val residentVoteOption: String?,
    val linkedLegislationId: String? = null,
    val districtBreakdownJson: String? = null,
    val cachedAt: Long,
)

@Entity(tableName = "poll_votes")
data class CivicVoteEntity(
    @PrimaryKey val id: String,
    val pollId: String,
    val voterPubKey: String,
    val voterName: String,
    val optionId: String,
    val timestamp: Long,
    val nonce: Long,
    val signature: String?,
    val isFlagged: Boolean = false,
    val flagReason: String? = null,
    val disputeComment: String? = null,
    val disputeExpiresAt: Long? = null,
    val status: ConflictStatus = ConflictStatus.NONE
)

@Entity(tableName = "poll_posts")
data class PollPostEntity(
    @PrimaryKey val id: String,
    val pollId: String,
    val optionId: String,
    val parentPostId: String?,
    val headline: String?,
    val authorName: String,
    val content: String,
    val score: Int,
    val userVote: Int,
    val createdAt: Long
)

@Entity(tableName = "scorecard_categories")
data class ScorecardCategoryEntity(
    @PrimaryKey(autoGenerate = true) val rowId: Long = 0,
    val districtId: String,
    val categoryName: String,
    val officialValue: String,
    val residentReportedValue: String?,
    val score: Int,
)

@Entity(tableName = "representative_scorecards")
data class RepresentativeScorecardEntity(
    @PrimaryKey val districtId: String,
    val representativePubKey: String,
    val name: String,
    val party: String,
    val overallScore: Int,
    val lastUpdated: Long,
    val cachedAt: Long,
)

@Entity(tableName = "candidate_manifestos")
data class CandidateManifestoEntity(
    @PrimaryKey val id: String,
    val candidatePubKey: String,
    val districtId: String,
    val title: String,
    val body: String,
    val publishedAt: Long,
    val cachedAt: Long,
)

@Entity(tableName = "manifesto_questions")
data class ManifestoQuestionEntity(
    @PrimaryKey val id: String,
    val manifestoId: String,
    val askerPubKey: String,
    val text: String,
    val askedAt: Long,
    val answer: String?,
    val answeredAt: Long?,
)

@Entity(tableName = "district_metrics")
data class DistrictMetricEntity(
    @PrimaryKey val id: String,
    val districtId: String,
    val category: String,
    val name: String,
    val officialValue: String,
    val residentValue: String?,
    val unit: String,
    val source: MetricSource,
    val reportedAt: Long,
    val reporterPubKey: String?,
    val cachedAt: Long,
)

@Entity(tableName = "pending_civic_events")
data class PendingCivicEventEntity(
    @PrimaryKey val eventId: String,
    val kind: Int,
    val contentJson: String,
    val sig: String,
    val createdAt: Long,
    val retryCount: Int = 0,
)

@Entity(tableName = "user_accounts")
data class AccountEntity(
    @PrimaryKey val username: String,
    val password: String,
    val pubKey: String,
    val privateKey: String,
    val districtId: String?,
    val createdAt: Long,
    val requiresPasswordChange: Boolean = false,
)

@Entity(tableName = "community_posts")
data class CommunityPostEntity(
    @PrimaryKey val id: String,
    val authorPubKey: String,
    val districtId: String,
    val kind: CommunityPostKind,
    val title: String,
    val description: String,
    val price: Double?,
    val location: String?,
    val contactInfo: String?,
    val createdAt: Long,
    val expiresAt: Long?,
    val cachedAt: Long
)

@Entity(tableName = "verification_requests")
data class VerificationRequestEntity(
    @PrimaryKey val id: String,
    val requesterPubKey: String,
    val requesterDisplayName: String,
    val email: String,
    val districtId: String,
    val stateId: String,
    val address: String,
    val createdAt: Long,
    val status: VerificationRequestStatus,
    val handledByPubKey: String?,
    val cachedAt: Long
)

