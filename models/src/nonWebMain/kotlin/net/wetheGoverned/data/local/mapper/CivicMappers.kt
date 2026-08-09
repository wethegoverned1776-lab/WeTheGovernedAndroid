package net.wetheGoverned.data.local.mapper

import kotlinx.serialization.encodeToString
import net.wetheGoverned.data.local.entity.*
import net.wetheGoverned.model.*
import kotlinx.datetime.Clock

fun District.toEntity(): DistrictEntity = DistrictEntity(
    id = id,
    level = level,
    state = state,
    districtNumber = districtNumber,
    displayName = displayName,
    representativeName = representativeName,
    representativeParty = representativeParty,
    geoBoundaries = geoBoundaries,
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
    representativeParty = representativeParty,
    geoBoundaries = geoBoundaries
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
    address = address,
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
    address = address,
    isVerified = tier != VerificationTier.OBSERVER
)

fun CivicPoll.toEntity(): DistrictPollEntity = DistrictPollEntity(
    id = id,
    scope = scope,
    districtId = districtId,
    localId = localId,
    authorPubKey = authorPubKey,
    question = question,
    optionsJson = CivicJson.encodeToString(options),
    status = status,
    createdAt = createdAt,
    closesAt = closesAt,
    totalVotes = totalVotes,
    importanceScore = importanceScore,
    userImportanceVote = userImportanceVote,
    residentVoteOption = residentVoteOption,
    linkedLegislationId = linkedLegislationId,
    districtBreakdownJson = districtBreakdown?.let { CivicJson.encodeToString(it) },
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun DistrictPollEntity.toDomain(): CivicPoll = CivicPoll(
    id = id,
    scope = scope ?: PollScope.DISTRICT,
    districtId = districtId,
    localId = localId,
    authorPubKey = authorPubKey,
    question = question,
    options = CivicJson.decodeFromString(optionsJson),
    status = status,
    createdAt = createdAt,
    closesAt = closesAt,
    totalVotes = totalVotes,
    importanceScore = importanceScore,
    userImportanceVote = userImportanceVote,
    residentVoteOption = residentVoteOption,
    linkedLegislationId = linkedLegislationId,
    districtBreakdown = districtBreakdownJson?.let { CivicJson.decodeFromString(it) } ?: emptyMap()
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
    expiresAt = expiresAt,
    tagsJson = CivicJson.encodeToString(tags),
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
    createdAt = createdAt,
    expiresAt = expiresAt,
    tags = tagsJson?.let { CivicJson.decodeFromString(it) } ?: emptyList()
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
    scope = scope,
    title = title,
    body = body,
    publishedAt = publishedAt,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun CandidateManifestoEntity.toDomain(questions: List<ManifestoQuestionEntity>): CandidateManifesto = CandidateManifesto(
    id = id,
    candidatePubKey = candidatePubKey,
    districtId = districtId,
    scope = scope,
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

fun PollPost.toEntity(): PollPostEntity = PollPostEntity(
    id = id,
    pollId = pollId,
    optionId = optionId,
    parentPostId = parentPostId,
    headline = headline,
    authorName = authorName,
    content = content,
    score = score,
    userVote = userVote,
    createdAt = createdAt
)

fun PollPostEntity.toDomain(): PollPost = PollPost(
    id = id,
    pollId = pollId,
    optionId = optionId,
    parentPostId = parentPostId,
    headline = headline,
    authorName = authorName,
    content = content,
    score = score,
    userVote = userVote,
    createdAt = createdAt
)

fun VerificationRequest.toEntity(): VerificationRequestEntity = VerificationRequestEntity(
    id = id,
    requesterPubKey = requesterPubKey,
    requesterDisplayName = requesterDisplayName,
    email = email,
    districtId = districtId,
    stateId = stateId,
    address = address,
    createdAt = createdAt,
    status = status,
    handledByPubKey = handledByPubKey,
    cachedAt = Clock.System.now().toEpochMilliseconds()
)

fun VerificationRequestEntity.toDomain(): VerificationRequest = VerificationRequest(
    id = id,
    requesterPubKey = requesterPubKey,
    requesterDisplayName = requesterDisplayName,
    email = email,
    districtId = districtId,
    stateId = stateId,
    address = address,
    createdAt = createdAt,
    status = status,
    handledByPubKey = handledByPubKey
)

fun UserAccount.toEntity(): AccountEntity = AccountEntity(
    username = username,
    password = password,
    pubKey = pubKey,
    privateKey = privateKey,
    quantumPubKey = quantumPubKey,
    quantumPrivateKey = quantumPrivateKey,
    districtId = districtId,
    createdAt = Clock.System.now().toEpochMilliseconds(),
    requiresPasswordChange = requiresPasswordChange
)

fun AccountEntity.toDomain(): UserAccount = UserAccount(
    username = username,
    password = password,
    pubKey = pubKey,
    privateKey = privateKey,
    districtId = districtId,
    quantumPubKey = quantumPubKey,
    quantumPrivateKey = quantumPrivateKey,
    requiresPasswordChange = requiresPasswordChange
)
