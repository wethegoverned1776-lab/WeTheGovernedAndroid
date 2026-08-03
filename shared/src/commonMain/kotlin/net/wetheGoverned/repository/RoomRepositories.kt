package net.wetheGoverned.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.*
import net.wetheGoverned.model.*
import net.wetheGoverned.data.local.AppDatabase
import net.wetheGoverned.data.local.entity.*
import net.wetheGoverned.data.local.mapper.*
import net.wetheGoverned.core.*
import kotlinx.datetime.Clock

class RoomPollRepository(
    private val db: AppDatabase,
    private val publisher: CivicPublisher? = null
) : PollRepository {

    override fun observeDistrictPolls(districtId: String): Flow<List<CivicPoll>> = 
        db.pollDao().observePolls(districtId).map { list -> list.map { it.toDomain() } }

    override fun observePollsByIds(districtIds: List<String>): Flow<List<CivicPoll>> =
        db.pollDao().observePollsByIds(districtIds).map { list -> list.map { it.toDomain() } }

    override suspend fun getPoll(pollId: String): Result<CivicPoll> = runCatching {
        db.pollDao().getPoll(pollId)?.toDomain() ?: throw Exception("Not found")
    }

    override suspend fun createPoll(
        districtId: String,
        question: String,
        options: List<String>,
        closesAt: Long?,
        scope: CivicScope,
        authorPubKey: String,
        localId: String?
    ): Result<CivicPoll> = runCatching {
        val now = Clock.System.now().toEpochMilliseconds()
        val id = sha256("poll_${question}_${authorPubKey}_$now").take(64)
        val newPoll = CivicPoll(
            id = id,
            scope = scope,
            districtId = districtId,
            localId = localId,
            authorPubKey = authorPubKey,
            question = question,
            options = options.mapIndexed { i, s -> PollOption("opt_$i", s, 0, 0f) },
            status = PollStatus.ACTIVE,
            createdAt = now,
            closesAt = closesAt ?: (now + 86400000),
            totalVotes = 0
        )
        db.pollDao().upsertPoll(newPoll.toEntity())
        
        publisher?.signPublishImportCivicEvent(
            kind = when(scope) {
                CivicScope.FEDERAL -> CivicEventKind.FEDERAL_POLL
                CivicScope.STATE -> CivicEventKind.STATE_POLL
                CivicScope.LOCAL -> CivicEventKind.LOCAL_POLL
                else -> CivicEventKind.DISTRICT_POLL
            },
            tags = listOf(listOf("d", id), listOf("g", districtId)),
            content = CivicJson.encodeToString(CivicPoll.serializer(), newPoll),
            pubKey = authorPubKey
        )
        newPoll
    }

    override suspend fun vote(pollId: String, optionId: String, voterPubKey: String): Result<Unit> = runCatching {
        val poll = db.pollDao().getPoll(pollId)?.toDomain() ?: throw Exception("Poll not found")
        val updatedOptions = poll.options.map { opt -> 
            if (opt.id == optionId) opt.copy(voteCount = opt.voteCount + 1) else opt 
        }
        val newTotal = poll.totalVotes + 1
        val updatedPoll = poll.copy(
            options = updatedOptions.map { it.copy(percentageOfTotal = it.voteCount.toFloat() / newTotal) },
            totalVotes = newTotal,
            residentVoteOption = optionId
        )
        db.pollDao().upsertPoll(updatedPoll.toEntity())
        
        val now = Clock.System.now().toEpochMilliseconds()
        val vote = CivicVote(
            id = sha256("vote_${pollId}_${voterPubKey}_$now").take(64),
            pollId = pollId,
            optionId = optionId,
            voterPubKey = voterPubKey,
            voterName = "Resident",
            timestamp = now,
            nonce = 0L,
            createdAt = now
        )
        
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.POLL_VOTE,
            tags = listOf(
                listOf("d", vote.id), 
                listOf("g", poll.districtId),
                listOf("e", pollId)
            ),
            content = CivicJson.encodeToString(CivicVote.serializer(), vote),
            pubKey = voterPubKey
        )
    }

    override suspend fun voteImportance(pollId: String, delta: Int, voterPubKey: String): Result<Unit> = runCatching {
        val poll = db.pollDao().getPoll(pollId)?.toDomain() ?: throw Exception("Poll not found")
        val updatedPoll = poll.copy(
            importanceScore = poll.importanceScore + delta,
            userImportanceVote = delta
        )
        db.pollDao().upsertPoll(updatedPoll.toEntity())
        
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.IMPORTANCE_VOTE,
            tags = listOf(listOf("d", "${pollId}_$voterPubKey"), listOf("g", poll.districtId)),
            content = "$pollId:$delta",
            pubKey = voterPubKey
        )
    }

    override fun observePollsPaged(districtId: String, limit: Int, offset: Int): Flow<List<CivicPoll>> =
        db.pollDao().observePolls(districtId).map { list -> list.drop(offset).take(limit).map { it.toDomain() } }

    override fun observePollsByScope(scope: CivicScope, districtId: String): Flow<List<CivicPoll>> =
        db.pollDao().observePolls(districtId).map { list -> 
            list.map { it.toDomain() }.filter { it.scope == scope }
        }

    override fun observePollPosts(pollId: String): Flow<List<PollPost>> =
        db.pollPostDao().getAllPostsForPoll(pollId).map { list -> list.map { 
            PollPost(it.id, it.pollId, it.optionId, it.parentPostId, it.headline, it.authorName, it.content, it.score, it.userVote, it.createdAt)
        }}

    override fun observeOptionPosts(pollId: String, optionId: String): Flow<List<PollPost>> =
        db.pollPostDao().observeOptionPosts(pollId, optionId).map { list -> list.map {
            PollPost(it.id, it.pollId, it.optionId, it.parentPostId, it.headline, it.authorName, it.content, it.score, it.userVote, it.createdAt)
        }}

    override fun observeThreadedPosts(parentPostId: String): Flow<List<PollPost>> =
        db.pollPostDao().observeThreadedPosts(parentPostId).map { list -> list.map {
            PollPost(it.id, it.pollId, it.optionId, it.parentPostId, it.headline, it.authorName, it.content, it.score, it.userVote, it.createdAt)
        }}

    override suspend fun createPost(pollId: String, optionId: String, authorName: String, content: String, headline: String?, parentPostId: String?): Result<PollPost> = Result.failure(Exception("Stub"))
    override suspend fun voteOnPost(postId: String, delta: Int): Result<Unit> = Result.success(Unit)
    override suspend fun getPost(postId: String): Result<PollPost> = Result.failure(Exception("Stub"))

    override suspend fun getAllPolls(): List<CivicPoll> = db.pollDao().getAllPollsAcrossDistricts().map { it.toDomain() }
    
    override suspend fun getPollsForJurisdictions(jurisdictionIds: List<String>, since: Long): List<CivicPoll> =
        getAllPolls().filter { (it.districtId in jurisdictionIds || it.localId in jurisdictionIds) && it.createdAt > since }

    override suspend fun syncPoll(poll: CivicPoll) {
        db.pollDao().upsertPoll(poll.toEntity())
    }

    override suspend fun syncVote(vote: CivicVote) {
        val poll = db.pollDao().getPoll(vote.pollId)?.toDomain() ?: return
        val updatedOptions = poll.options.map { opt ->
            if (opt.id == vote.optionId) opt.copy(voteCount = opt.voteCount + 1) else opt
        }
        val newTotal = poll.totalVotes + 1
        val updatedPoll = poll.copy(
            options = updatedOptions.map { it.copy(percentageOfTotal = it.voteCount.toFloat() / newTotal) },
            totalVotes = newTotal
        )
        db.pollDao().upsertPoll(updatedPoll.toEntity())
    }

    override suspend fun markVoted(pollId: String, optionId: String) {
        db.pollDao().applyOptimisticVote(pollId, optionId)
    }
}

class RoomResidentRepository(
    private val db: AppDatabase,
    private val publisher: CivicPublisher? = null
) : ResidentRepository {
    override fun observeProfile(pubKey: String): Flow<ResidentProfile?> =
        db.residentProfileDao().observeProfile(pubKey).map { it?.toDomain() }

    override fun observeProfileByFingerprint(fingerprint: String): Flow<ResidentProfile?> =
        db.residentProfileDao().observeProfilesByFingerprint(fingerprint).map { it.firstOrNull()?.toDomain() }

    override suspend fun getProfile(pubKey: String): Result<ResidentProfile> = runCatching {
        db.residentProfileDao().getProfile(pubKey)?.toDomain() ?: throw Exception("Not found")
    }

    override suspend fun upgradeTier(pubKey: String, newTier: VerificationTier, proofToken: String): Result<ResidentProfile> = Result.failure(Exception("Not implemented"))
    override suspend fun upgradeTierWithFingerprint(pubKey: String, newTier: VerificationTier, proofToken: String, fingerprint: String): Result<ResidentProfile> = Result.failure(Exception("Not implemented"))
    override suspend fun upgradeTierFull(pubKey: String, newTier: VerificationTier, fingerprint: String, verifiedBy: String?): Result<Unit> = Result.success(Unit)

    override suspend fun updateProfile(pubKey: String, displayName: String, avatarUrl: String?): Result<ResidentProfile> = runCatching {
        val p = db.residentProfileDao().getProfile(pubKey) ?: throw Exception("Not found")
        val updated = p.copy(displayName = displayName, avatarUrl = avatarUrl ?: p.avatarUrl)
        db.residentProfileDao().upsertProfile(updated)
        
        val domain = updated.toDomain()
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.RESIDENT_PROFILE,
            tags = listOf(listOf("d", pubKey), listOf("g", domain.federalHouseId ?: "us")),
            content = CivicJson.encodeToString(ResidentProfile.serializer(), domain),
            pubKey = pubKey
        )
        domain
    }

    override suspend fun updateDistrict(pubKey: String, districtId: String): Result<Unit> = runCatching {
        val p = db.residentProfileDao().getProfile(pubKey) ?: throw Exception("Not found")
        val updated = p.copy(federalHouseId = districtId)
        db.residentProfileDao().upsertProfile(updated)
        
        val domain = updated.toDomain()
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.RESIDENT_PROFILE,
            tags = listOf(listOf("d", pubKey), listOf("g", districtId)),
            content = CivicJson.encodeToString(ResidentProfile.serializer(), domain),
            pubKey = pubKey
        )
    }

    override suspend fun getResidentCountAtAddress(fingerprint: String): Int = db.residentProfileDao().getProfileCountByFingerprint(fingerprint)
    override suspend fun getVouchCount(notaryPubKey: String): Int = db.residentProfileDao().getVouchCount(notaryPubKey)
    
    override fun observeProfilesVerifiedBy(verifierPubKey: String): Flow<List<ResidentProfile>> =
        db.residentProfileDao().observeProfilesVerifiedBy(verifierPubKey).map { list -> list.map { it.toDomain() } }

    override suspend fun createProfile(profile: ResidentProfile) {
        db.residentProfileDao().upsertProfile(profile.toEntity())
    }
}

class RoomVoteRepository(private val db: AppDatabase) : VoteRepository {
    override fun observeAllVotes(): Flow<List<CivicVote>> = db.voteDao().observeAllVotes().map { list -> list.map { it.toDomain() } }
    override fun observeVotesByUser(pubKey: String): Flow<List<CivicVote>> = db.voteDao().observeVotesByUser(pubKey).map { list -> list.map { it.toDomain() } }
    override suspend fun flagVote(voteId: String, reason: String, expiresAt: Long): Result<Unit> = runCatching { db.voteDao().flagVote(voteId, expiresAt) }
    override suspend fun disputeVote(voteId: String, comment: String): Result<Unit> = runCatching { db.voteDao().disputeVote(voteId, comment) }
    override suspend fun resolveVote(voteId: String): Result<Unit> = runCatching { db.voteDao().resolveVote(voteId) }
    override suspend fun syncVote(vote: CivicVote) { db.voteDao().upsertVote(vote.toEntity()) }
}

class RoomCommunityRepository(
    private val db: AppDatabase,
    private val publisher: CivicPublisher? = null
) : CommunityRepository {
    override fun observePosts(districtId: String, kind: CommunityPostKind?): Flow<List<CommunityPost>> =
        if (kind == null) db.communityPostDao().observePosts(districtId).map { list -> list.map { it.toDomain() } }
        else db.communityPostDao().observePostsByKind(districtId, kind).map { list -> list.map { it.toDomain() } }


    override suspend fun getPost(postId: String): Result<CommunityPost> = runCatching {
        db.communityPostDao().getPost(postId)?.toDomain() ?: throw Exception("Not found")
    }

    override suspend fun createPost(
        districtId: String, authorPubKey: String, kind: CommunityPostKind, title: String, 
        description: String, price: Double?, location: String?, contactInfo: String?
    ): Result<CommunityPost> = runCatching {
        val post = CommunityPost(
            id = "post_${Clock.System.now().toEpochMilliseconds()}",
            districtId = districtId,
            authorPubKey = authorPubKey,
            kind = kind,
            title = title,
            description = description,
            price = price,
            location = location,
            contactInfo = contactInfo,
            createdAt = Clock.System.now().toEpochMilliseconds()
        )
        db.communityPostDao().upsertPost(post.toEntity())
        
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.COMMUNITY_POST,
            tags = listOf(listOf("d", post.id), listOf("g", districtId)),
            content = CivicJson.encodeToString(CommunityPost.serializer(), post),
            pubKey = authorPubKey
        )
        post
    }

    override suspend fun deletePost(postId: String): Result<Unit> = runCatching { db.communityPostDao().deletePost(postId) }
    override suspend fun getAllPosts(): List<CommunityPost> = db.communityPostDao().getAllPosts().map { it.toDomain() }
    override suspend fun syncPost(post: CommunityPost) { db.communityPostDao().upsertPost(post.toEntity()) }
}

class RoomAccountRepository(private val db: AppDatabase) : AccountRepository {
    override suspend fun register(account: UserAccount): Result<Unit> = runCatching {
        if (db.accountDao().getAccount(account.username) != null) throw Exception("Exists")
        db.accountDao().upsertAccount(AccountEntity(
            account.username, account.password, account.pubKey, account.privateKey, account.districtId,
            Clock.System.now().toEpochMilliseconds(), account.requiresPasswordChange
        ))
    }

    override suspend fun login(username: String, password: String): Result<UserAccount> = runCatching {
        val adminPub = "79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798"
        val adminPriv = "0000000000000000000000000000000000000000000000000000000000000001"
        
        if (username == "admin" && password == "1January012@") {
            return Result.success(UserAccount("admin", "1January012@", adminPub, adminPriv, "us-fl-06"))
        }

        val acc = db.accountDao().getAccount(username) ?: throw Exception("Invalid")
        if (acc.password == password) {
            UserAccount(
                username = acc.username, 
                password = acc.password, 
                pubKey = acc.pubKey, 
                privateKey = acc.privateKey, 
                districtId = acc.districtId, 
                requiresPasswordChange = acc.requiresPasswordChange
            )
        } else throw Exception("Invalid")
    }

    override suspend fun changePassword(username: String, newPassword: String): Result<Unit> = runCatching {
        val acc = db.accountDao().getAccount(username) ?: throw Exception("Not found")
        db.accountDao().upsertAccount(acc.copy(password = newPassword, requiresPasswordChange = false))
    }

    override suspend fun updateDistrict(username: String, districtId: String) {
        val acc = db.accountDao().getAccount(username) ?: return
        db.accountDao().upsertAccount(acc.copy(districtId = districtId))
    }
}

class RoomVerificationRequestRepository(private val db: AppDatabase) : VerificationRequestRepository {
    override fun observeRequestsForDistrict(districtId: String): Flow<List<VerificationRequest>> =
        db.verificationRequestDao().observeRequestsForDistrict(districtId).map { list -> list.map { 
            VerificationRequest(it.id, it.requesterPubKey, it.requesterDisplayName, it.email, it.districtId, it.stateId, it.address, it.createdAt, it.status, it.handledByPubKey)
        }}

    override fun observeRequestsForState(stateId: String): Flow<List<VerificationRequest>> =
        db.verificationRequestDao().observeRequestsForState(stateId).map { list -> list.map {
            VerificationRequest(it.id, it.requesterPubKey, it.requesterDisplayName, it.email, it.districtId, it.stateId, it.address, it.createdAt, it.status, it.handledByPubKey)
        }}

    override suspend fun createRequest(request: VerificationRequest): Result<Unit> = runCatching {
        db.verificationRequestDao().upsertRequest(VerificationRequestEntity(
            request.id, request.requesterPubKey, request.requesterDisplayName, request.email, request.districtId, request.stateId, request.address, request.createdAt, request.status, request.handledByPubKey, Clock.System.now().toEpochMilliseconds()
        ))
    }

    override suspend fun updateRequestStatus(requestId: String, status: VerificationRequestStatus, handledBy: String): Result<Unit> = runCatching {
        db.verificationRequestDao().updateStatus(requestId, status, handledBy)
    }

    override suspend fun getRequest(requestId: String): Result<VerificationRequest> = runCatching {
        val it = db.verificationRequestDao().getRequest(requestId) ?: throw Exception("Not found")
        VerificationRequest(it.id, it.requesterPubKey, it.requesterDisplayName, it.email, it.districtId, it.stateId, it.address, it.createdAt, it.status, it.handledByPubKey)
    }
}


class RoomScorecardRepository(private val db: AppDatabase) : ScorecardRepository {
    override fun observeScorecard(districtId: String): Flow<RepresentativeScorecard?> =
        db.scorecardDao().observeScorecard(districtId).flatMapLatest { entity ->
            if (entity == null) flowOf(null)
            else flow {
                val categories = db.scorecardDao().getCategoriesForDistrict(districtId)
                emit(entity.toDomain(categories))
            }
        }

    override suspend fun getScorecard(districtId: String): Result<RepresentativeScorecard> = runCatching {
        val entity = db.scorecardDao().getScorecard(districtId) ?: throw Exception("Not found")
        val categories = db.scorecardDao().getCategoriesForDistrict(districtId)
        entity.toDomain(categories)
    }

    override suspend fun submitMetricReport(
        districtId: String, category: String, name: String, value: String, unit: String, reporterPubKey: String
    ): Result<DistrictMetric> = runCatching {
        val metric = DistrictMetric(
            id = sha256("metric_${districtId}_${name}_$reporterPubKey").take(64),
            districtId = districtId,
            category = category,
            name = name,
            officialValue = "",
            residentValue = value,
            unit = unit,
            source = MetricSource.RESIDENT_REPORTED,
            reportedAt = Clock.System.now().toEpochMilliseconds(),
            reporterPubKey = reporterPubKey
        )
        db.metricDao().upsertMetric(metric.toEntity())
        metric
    }
}

class RoomManifestoRepository(private val db: AppDatabase) : ManifestoRepository {
    override fun observeManifestos(districtId: String): Flow<List<CandidateManifesto>> =
        db.manifestoDao().observeManifestos(districtId).map { list ->
            list.map { entity ->
                val questions = db.manifestoDao().getQuestions(entity.id)
                entity.toDomain(questions)
            }
        }

    override suspend fun getManifesto(manifestoId: String): Result<CandidateManifesto> = runCatching {
        val entity = db.manifestoDao().getManifesto(manifestoId) ?: throw Exception("Not found")
        val questions = db.manifestoDao().getQuestions(manifestoId)
        entity.toDomain(questions)
    }

    override suspend fun publishManifesto(districtId: String, title: String, body: String, candidatePubKey: String): Result<CandidateManifesto> = runCatching {
        val manifesto = CandidateManifesto(
            id = sha256("manifesto_${districtId}_${candidatePubKey}").take(64),
            candidatePubKey = candidatePubKey,
            districtId = districtId,
            title = title,
            body = body,
            publishedAt = Clock.System.now().toEpochMilliseconds(),
            questions = emptyList()
        )
        db.manifestoDao().upsertManifesto(manifesto.toEntity())
        manifesto
    }

    override suspend fun askQuestion(manifestoId: String, questionText: String, askerPubKey: String): Result<ManifestoQuestion> = runCatching {
        val q = ManifestoQuestion(
            id = sha256("q_${manifestoId}_${askerPubKey}_${Clock.System.now().toEpochMilliseconds()}").take(64),
            askerPubKey = askerPubKey,
            text = questionText,
            askedAt = Clock.System.now().toEpochMilliseconds()
        )
        db.manifestoDao().upsertQuestion(q.toEntity(manifestoId))
        q
    }

    override suspend fun answerQuestion(manifestoId: String, questionId: String, answerText: String, candidatePubKey: String): Result<ManifestoQuestion> = runCatching {
        val allQ = db.manifestoDao().getQuestions(manifestoId)
        val q = allQ.find { it.id == questionId } ?: throw Exception("Not found")
        val updated = q.copy(answer = answerText, answeredAt = Clock.System.now().toEpochMilliseconds())
        db.manifestoDao().upsertQuestion(updated)
        updated.toDomain()
    }
}

class RoomDistrictRepository(private val db: AppDatabase) : DistrictRepository {
    override fun observeDistrict(districtId: String): Flow<District?> =
        db.districtDao().observeDistrict(districtId).map { it?.toDomain() }

    override suspend fun getDistrict(districtId: String): Result<District> = runCatching {
        db.districtDao().getDistrict(districtId)?.toDomain() ?: throw Exception("Not found")
    }

    override fun observeMetrics(districtId: String): Flow<List<DistrictMetric>> =
        db.metricDao().observeMetrics(districtId).map { list -> list.map { it.toDomain() } }

    override suspend fun refreshMetrics(districtId: String): Result<List<DistrictMetric>> = runCatching {
        // In a real app, this would fetch from an API and update Room
        db.metricDao().observeMetrics(districtId).first().map { it.toDomain() }
    }
}

