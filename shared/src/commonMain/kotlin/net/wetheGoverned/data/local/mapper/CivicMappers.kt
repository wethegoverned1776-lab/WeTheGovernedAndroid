package net.wetheGoverned.data.local.mapper

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.wetheGoverned.data.local.entity.*
import net.wetheGoverned.model.*
import kotlinx.datetime.Clock

private val json = Json { ignoreUnknownKeys = true }

fun District.toEntity(): DistrictEntity = DistrictEntity(
    id = id,
    level = level,
    state = state,
    districtNumber = districtNumber,
    displayName = displayName,
    representativeName = representativeName,
    representativeParty = representativeParty,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun DistrictEntity.toDomain(): District = District(
    id = id,
    level = level,
    state = state,
    districtNumber = districtNumber,
    name = displayName,
    displayName = displayName,
    representativeName = representativeName,
    representativeParty = representativeParty
)

fun ResidentProfile.toEntity(): ResidentProfileEntity = ResidentProfileEntity(
    pubKey = pubKey,
    displayName = displayName,
    federalHouseId = federalHouseId,
    federalSenateId = federalSenateId,
    stateSenateId = stateSenateId,
    stateHouseId = stateHouseId,
    countyId = countyId,
    cityId = cityId,
    schoolBoardId = schoolBoardId,
    tier = tier,
    avatarUrl = avatarUrl,
    joinedAt = joinedAt,
    addressFingerprint = addressFingerprint,
    verifiedByPubKey = verifiedByPubKey,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun ResidentProfileEntity.toDomain(): ResidentProfile = ResidentProfile(
    pubKey = pubKey,
    displayName = displayName,
    federalHouseId = federalHouseId,
    federalSenateId = federalSenateId,
    stateSenateId = stateSenateId,
    stateHouseId = stateHouseId,
    countyId = countyId,
    cityId = cityId,
    schoolBoardId = schoolBoardId,
    tier = tier,
    avatarUrl = avatarUrl,
    joinedAt = joinedAt,
    addressFingerprint = addressFingerprint,
    verifiedByPubKey = verifiedByPubKey,
    isVerified = tier != VerificationTier.OBSERVER
)

fun CivicPoll.toEntity(): DistrictPollEntity = DistrictPollEntity(
    id = id,
    scope = scope,
    districtId = districtId,
    localId = localId,
    authorPubKey = authorPubKey,
    question = question,
    optionsJson = json.encodeToString(options),
    status = status,
    createdAt = createdAt,
    closesAt = closesAt,
    totalVotes = totalVotes,
    importanceScore = importanceScore,
    userImportanceVote = userImportanceVote,
    residentVoteOption = residentVoteOption,
    linkedLegislationId = linkedLegislationId,
    districtBreakdownJson = districtBreakdown?.let { json.encodeToString(it) },
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun DistrictPollEntity.toDomain(): CivicPoll = CivicPoll(
    id = id,
    scope = scope ?: PollScope.DISTRICT,
    districtId = districtId,
    localId = localId,
    authorPubKey = authorPubKey,
    question = question,
    options = json.decodeFromString(optionsJson),
    status = status,
    createdAt = createdAt,
    closesAt = closesAt,
    totalVotes = totalVotes,
    importanceScore = importanceScore,
    userImportanceVote = userImportanceVote,
    residentVoteOption = residentVoteOption,
    linkedLegislationId = linkedLegislationId,
    districtBreakdown = districtBreakdownJson?.let { json.decodeFromString(it) } ?: emptyMap()
)

fun CivicVote.toEntity(): CivicVoteEntity = CivicVoteEntity(
    id = id,
    pollId = pollId,
    voterPubKey = voterPubKey,
    voterName = voterName,
    optionId = optionId,
    timestamp = timestamp,
    nonce = nonce,
    signature = signature,
    isFlagged = isFlagged,
    flagReason = flagReason,
    disputeComment = disputeComment,
    disputeExpiresAt = disputeExpiresAt,
    status = status
)

fun CivicVoteEntity.toDomain(): CivicVote = CivicVote(
    id = id,
    pollId = pollId,
    voterPubKey = voterPubKey,
    voterName = voterName,
    optionId = optionId,
    timestamp = timestamp,
    nonce = nonce,
    signature = signature,
    isFlagged = isFlagged,
    flagReason = flagReason,
    disputeComment = disputeComment,
    disputeExpiresAt = disputeExpiresAt,
    status = status
)

fun CommunityPost.toEntity(): CommunityPostEntity = CommunityPostEntity(
    id = id,
    authorPubKey = authorPubKey,
    districtId = districtId,
    kind = kind,
    title = title,
    description = description,
    price = price,
    location = location,
    contactInfo = contactInfo,
    createdAt = createdAt,
    expiresAt = null,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun CommunityPostEntity.toDomain(): CommunityPost = CommunityPost(
    id = id,
    districtId = districtId,
    authorPubKey = authorPubKey,
    kind = kind,
    title = title,
    description = description,
    price = price,
    location = location,
    contactInfo = contactInfo,
    createdAt = createdAt
)


fun RepresentativeScorecard.toEntity(): RepresentativeScorecardEntity = RepresentativeScorecardEntity(
    districtId = districtId,
    representativePubKey = representativePubKey,
    name = name,
    party = party,
    overallScore = overallScore,
    lastUpdated = lastUpdated,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun RepresentativeScorecardEntity.toDomain(categories: List<ScorecardCategoryEntity>): RepresentativeScorecard = RepresentativeScorecard(
    representativePubKey = representativePubKey,
    districtId = districtId,
    name = name,
    party = party,
    overallScore = overallScore,
    categories = categories.map { it.toDomain() },
    lastUpdated = lastUpdated
)

fun ScorecardCategoryEntity.toDomain(): ScorecardCategory = ScorecardCategory(
    name = categoryName,
    officialValue = officialValue,
    residentReportedValue = residentReportedValue,
    score = score
)

fun ScorecardCategory.toEntity(districtId: String): ScorecardCategoryEntity = ScorecardCategoryEntity(
    districtId = districtId,
    categoryName = name,
    officialValue = officialValue,
    residentReportedValue = residentReportedValue,
    score = score
)

fun CandidateManifesto.toEntity(): CandidateManifestoEntity = CandidateManifestoEntity(
    id = id,
    candidatePubKey = candidatePubKey,
    districtId = districtId,
    title = title,
    body = body,
    publishedAt = publishedAt,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun CandidateManifestoEntity.toDomain(questions: List<ManifestoQuestionEntity>): CandidateManifesto = CandidateManifesto(
    id = id,
    candidatePubKey = candidatePubKey,
    districtId = districtId,
    title = title,
    body = body,
    publishedAt = publishedAt,
    questions = questions.map { it.toDomain() }
)

fun ManifestoQuestionEntity.toDomain(): ManifestoQuestion = ManifestoQuestion(
    id = id,
    askerPubKey = askerPubKey,
    text = text,
    askedAt = askedAt,
    answer = answer,
    answeredAt = answeredAt
)

fun ManifestoQuestion.toEntity(manifestoId: String): ManifestoQuestionEntity = ManifestoQuestionEntity(
    id = id,
    manifestoId = manifestoId,
    askerPubKey = askerPubKey,
    text = text,
    askedAt = askedAt,
    answer = answer,
    answeredAt = answeredAt
)

fun DistrictMetric.toEntity(): DistrictMetricEntity = DistrictMetricEntity(
    id = id,
    districtId = districtId,
    category = category,
    name = name,
    officialValue = officialValue,
    residentValue = residentValue,
    unit = unit,
    source = source,
    reportedAt = reportedAt,
    reporterPubKey = reporterPubKey,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun DistrictMetricEntity.toDomain(): DistrictMetric = DistrictMetric(
    id = id,
    districtId = districtId,
    category = category,
    name = name,
    officialValue = officialValue,
    residentValue = residentValue,
    unit = unit,
    source = source,
    reportedAt = reportedAt,
    reporterPubKey = reporterPubKey
)


