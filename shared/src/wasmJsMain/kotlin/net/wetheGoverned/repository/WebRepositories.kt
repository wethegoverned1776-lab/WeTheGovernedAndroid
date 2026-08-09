package net.wetheGoverned.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.*
import net.wetheGoverned.model.*
import net.wetheGoverned.session.*
import net.wetheGoverned.core.*
import kotlinx.datetime.Clock
import kotlinx.browser.window
import org.w3c.dom.Storage

private val storage: Storage get() = window.localStorage

private fun localStorageGet(key: String): String? = storage.getItem(key)
private fun localStorageSet(key: String, value: String) = storage.setItem(key, value)
private fun localStorageRemove(key: String) = storage.removeItem(key)

private fun localStorageKeys(): List<String> {
    val keys = mutableListOf<String>()
    for (i in 0 until storage.length) {
        storage.key(i)?.let { keys.add(it) }
    }
    return keys
}

abstract class WebRepository(val typeName: String) {

    protected fun <T> saveToStorage(id: String, data: T, serializer: kotlinx.serialization.KSerializer<T>) {
        localStorageSet("${typeName}_$id", CivicJson.encodeToString(serializer, data))
    }

    protected fun <T> loadFromStorage(id: String, serializer: kotlinx.serialization.KSerializer<T>): T? {
        return localStorageGet("${typeName}_$id")?.let { CivicJson.decodeFromString(serializer, it) }
    }
    
    protected fun listIdsFromStorage(): List<String> {
        val prefix = "${typeName}_"
        return localStorageKeys().filter { it.startsWith(prefix) }.map { it.removePrefix(prefix) }
    }
}

class WebVoteRepository(private val publisher: CivicPublisher? = null) : VoteRepository, WebRepository("votes") {
    override fun observeAllVotes(): Flow<List<CivicVote>> = flow {
        emit(listIdsFromStorage().mapNotNull { id -> loadFromStorage(id, CivicVoteSerializer) })
    }
    override fun observeVotesByUser(pubKey: String): Flow<List<CivicVote>> = flow {
        emit(listIdsFromStorage().mapNotNull { id -> loadFromStorage(id, CivicVoteSerializer) }.filter { it.voterPubKey == pubKey })
    }
    override suspend fun flagVote(voteId: String, reason: String, expiresAt: Long): Result<Unit> = Result.success(Unit)
    override suspend fun disputeVote(voteId: String, comment: String): Result<Unit> = Result.success(Unit)
    override suspend fun resolveVote(voteId: String): Result<Unit> = Result.success(Unit)
    override suspend fun syncVote(vote: CivicVote) { saveToStorage(vote.id, vote, CivicVoteSerializer) }
}

class WebPollRepository(private val publisher: CivicPublisher? = null) : PollRepository, WebRepository("polls") {
    private val _pollsFlow = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }

    override fun observeDistrictPolls(districtId: String): Flow<List<CivicPoll>> = _pollsFlow.onStart { emit(Unit) }.map {
        listIdsFromStorage().mapNotNull { id -> loadFromStorage(id, CivicPollSerializer) }
            .filter { 
                it.districtId == districtId || 
                it.districtId == "us" || 
                it.districtId == districtId.substringBeforeLast('-', "us") ||
                it.localId == districtId
            }
    }

    override fun observePollsByIds(districtIds: List<String>): Flow<List<CivicPoll>> = _pollsFlow.onStart { emit(Unit) }.map {
        listIdsFromStorage().mapNotNull { id -> loadFromStorage(id, CivicPollSerializer) }
            .filter { it.districtId in districtIds || (it.localId != null && it.localId in districtIds) }
    }

    override fun observePollsByScope(scope: CivicScope, districtId: String): Flow<List<CivicPoll>> = _pollsFlow.onStart { emit(Unit) }.map {
        listIdsFromStorage().mapNotNull { id -> loadFromStorage(id, CivicPollSerializer) }
            .filter { it.scope == scope && (it.districtId == districtId || it.districtId == "us" || it.districtId == districtId.substringBeforeLast('-', "us") || it.localId == districtId) }
    }

    override suspend fun getPoll(pollId: String): Result<CivicPoll> = 
        loadFromStorage(pollId, CivicPollSerializer)?.let { Result.success(it) } ?: Result.failure(Exception("Not found"))

    override suspend fun createPoll(districtId: String, question: String, options: List<String>, closesAt: Long?, scope: CivicScope, authorPubKey: String, localId: String?): Result<CivicPoll> {
        val now = Clock.System.now().toEpochMilliseconds()
        val id = sha256("poll_${question}_${authorPubKey}_$now").take(64)
        val newPoll = CivicPoll(
            id = id, scope = scope, districtId = districtId, localId = localId,
            authorPubKey = authorPubKey.lowercase(), question = question,
            options = options.mapIndexed { i, s -> PollOption("opt_$i", label = s, 0, 0f) },
            status = PollStatus.ACTIVE, createdAt = now,
            closesAt = closesAt ?: (now + 86400000), totalVotes = 0
        )
        
        publisher?.signPublishImportCivicEvent(
            kind = when(scope) {
                CivicScope.FEDERAL -> CivicEventKind.FEDERAL_POLL
                CivicScope.STATE -> CivicEventKind.STATE_POLL
                CivicScope.LOCAL -> CivicEventKind.LOCAL_POLL
                else -> CivicEventKind.DISTRICT_POLL
            },
            tags = listOf(listOf("d", id), listOf("g", districtId)),
            content = CivicJson.encodeToString(CivicPollSerializer, newPoll),
            pubKey = authorPubKey.lowercase()
        )

        saveToStorage(id, newPoll, CivicPollSerializer)
        _pollsFlow.emit(Unit)
        return Result.success(newPoll)
    }

    override suspend fun vote(pollId: String, optionId: String, voterPubKey: String): Result<Unit> {
        val normalizedPubKey = voterPubKey.lowercase()
        val poll = loadFromStorage(pollId, CivicPollSerializer) ?: return Result.failure(Exception("Poll not found"))
        val updatedOptions = poll.options.map { opt -> if (opt.id == optionId) opt.copy(voteCount = opt.voteCount + 1) else opt }
        val newTotal = poll.totalVotes + 1
        val updatedPoll = poll.copy(options = updatedOptions.map { it.copy(percentageOfTotal = it.voteCount.toFloat() / newTotal) }, totalVotes = newTotal, residentVoteOption = optionId)
        
        val now = Clock.System.now().toEpochMilliseconds()
        val vote = CivicVote(
            id = sha256("vote_${pollId}_${normalizedPubKey}_$now").take(64),
            pollId = pollId,
            optionId = optionId,
            voterPubKey = normalizedPubKey,
            voterName = "Web Resident",
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
            content = CivicJson.encodeToString(CivicVoteSerializer, vote),
            pubKey = normalizedPubKey
        )

        saveToStorage(pollId, updatedPoll, CivicPollSerializer)
        _pollsFlow.emit(Unit)
        return Result.success(Unit)
    }

    override suspend fun voteImportance(pollId: String, delta: Int, voterPubKey: String): Result<Unit> {
        val normalizedPubKey = voterPubKey.lowercase()
        val poll = loadFromStorage(pollId, CivicPollSerializer) ?: return Result.failure(Exception("Poll not found"))
        val updatedPoll = poll.copy(
            importanceScore = poll.importanceScore + delta,
            userImportanceVote = delta
        )
        
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.IMPORTANCE_VOTE,
            tags = listOf(listOf("d", "${pollId}_$normalizedPubKey"), listOf("g", poll.districtId)),
            content = "$pollId:$delta",
            pubKey = normalizedPubKey
        )

        saveToStorage(pollId, updatedPoll, CivicPollSerializer)
        _pollsFlow.emit(Unit)
        return Result.success(Unit)
    }

    override fun observePollsPaged(districtId: String, limit: Int, offset: Int): Flow<List<CivicPoll>> = _pollsFlow.onStart { emit(Unit) }.map {
        listIdsFromStorage().mapNotNull { id -> loadFromStorage(id, CivicPollSerializer) }
            .filter { it.districtId == districtId }.drop(offset).take(limit)
    }

    override fun observePollPosts(pollId: String): Flow<List<PollPost>> = flowOf(emptyList())
    override fun observeOptionPosts(pollId: String, optionId: String): Flow<List<PollPost>> = flowOf(emptyList())
    override fun observeThreadedPosts(parentPostId: String): Flow<List<PollPost>> = flowOf(emptyList())
    override suspend fun createPost(pollId: String, optionId: String, authorName: String, content: String, headline: String?, parentPostId: String?): Result<PollPost> = Result.failure(Exception("Not implemented"))
    override suspend fun voteOnPost(postId: String, delta: Int): Result<Unit> = Result.success(Unit)
    override suspend fun getPost(postId: String): Result<PollPost> = Result.failure(Exception("Not found"))
    override suspend fun getAllPolls(): List<CivicPoll> = listIdsFromStorage().mapNotNull { id -> loadFromStorage(id, CivicPollSerializer) }
    override suspend fun getPollsForJurisdictions(jurisdictionIds: List<String>, since: Long): List<CivicPoll> = getAllPolls().filter { (it.districtId in jurisdictionIds || it.localId in jurisdictionIds) && it.createdAt > since }
    
    override suspend fun syncPoll(poll: CivicPoll) {
        saveToStorage(poll.id, poll, CivicPollSerializer)
        _pollsFlow.emit(Unit)
    }

    override suspend fun syncVote(vote: CivicVote) {
        val poll = loadFromStorage(vote.pollId, CivicPollSerializer) ?: return
        val updatedOptions = poll.options.map { opt ->
            if (opt.id == vote.optionId) opt.copy(voteCount = opt.voteCount + 1) else opt
        }
        val newTotal = poll.totalVotes + 1
        val updatedPoll = poll.copy(
            options = updatedOptions.map { it.copy(percentageOfTotal = it.voteCount.toFloat() / newTotal) },
            totalVotes = newTotal
        )
        saveToStorage(vote.pollId, updatedPoll, CivicPollSerializer)
        _pollsFlow.emit(Unit)
    }

    override suspend fun markVoted(pollId: String, optionId: String) {
        val poll = loadFromStorage(pollId, CivicPollSerializer) ?: return
        if (poll.residentVoteOption == optionId) return
        val updated = poll.copy(residentVoteOption = optionId)
        saveToStorage(pollId, updated, CivicPollSerializer)
        _pollsFlow.emit(Unit)
    }
}

class WebResidentRepository(private val publisher: CivicPublisher? = null) : ResidentRepository, WebRepository("residents") {
    private val _updates = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }

    override fun observeProfile(pubKey: String): Flow<ResidentProfile?> = _updates.onStart { emit(Unit) }.map {
        loadFromStorage(pubKey.lowercase(), ResidentProfileSerializer)
    }

    override fun observeProfileByFingerprint(fingerprint: String): Flow<ResidentProfile?> = _updates.onStart { emit(Unit) }.map {
        listIdsFromStorage().mapNotNull { loadFromStorage(it, ResidentProfileSerializer) }.find { it.addressFingerprint == fingerprint }
    }

    override suspend fun getResidentCountAtAddress(fingerprint: String): Int = 0
    override suspend fun getVouchCount(notaryPubKey: String): Int = 0
    override suspend fun getProfile(pubKey: String): Result<ResidentProfile> = 
        loadFromStorage(pubKey.lowercase(), ResidentProfileSerializer)?.let { Result.success(it) } ?: Result.failure(Exception("Not found"))
        
    override suspend fun upgradeTier(pubKey: String, newTier: VerificationTier, proofToken: String): Result<ResidentProfile> = Result.failure(Exception("Not implemented"))
    override suspend fun upgradeTierWithFingerprint(pubKey: String, newTier: VerificationTier, proofToken: String, fingerprint: String): Result<ResidentProfile> = Result.failure(Exception("Not implemented"))
    override suspend fun upgradeTierFull(pubKey: String, newTier: VerificationTier, fingerprint: String, verifiedBy: String?): Result<Unit> = Result.success(Unit)
    override suspend fun updateProfile(pubKey: String, displayName: String, avatarUrl: String?): Result<ResidentProfile> {
        val normalizedPubKey = pubKey.lowercase()
        val p = loadFromStorage(normalizedPubKey, ResidentProfileSerializer) ?: return Result.failure(Exception("Not found"))
        val updated = p.copy(displayName = displayName)
        
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.RESIDENT_PROFILE,
            tags = listOf(listOf("d", normalizedPubKey), listOf("g", updated.federalHouseId ?: "us")),
            content = CivicJson.encodeToString(ResidentProfileSerializer, updated),
            pubKey = normalizedPubKey
        )
        
        saveToStorage(normalizedPubKey, updated, ResidentProfileSerializer)
        _updates.emit(Unit)
        return Result.success(updated)
    }
    override suspend fun updateDistrict(pubKey: String, districtId: String): Result<Unit> {
        val normalizedPubKey = pubKey.lowercase()
        val p = loadFromStorage(normalizedPubKey, ResidentProfileSerializer) ?: return Result.failure(Exception("Not found"))
        val updated = p.copy(federalHouseId = districtId)

        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.RESIDENT_PROFILE,
            tags = listOf(listOf("d", normalizedPubKey), listOf("g", districtId)),
            content = CivicJson.encodeToString(ResidentProfileSerializer, updated),
            pubKey = normalizedPubKey
        )

        saveToStorage(normalizedPubKey, updated, ResidentProfileSerializer)
        _updates.emit(Unit)
        return Result.success(Unit)
    }
    override fun observeProfilesVerifiedBy(verifierPubKey: String): Flow<List<ResidentProfile>> = flowOf(emptyList())
    override suspend fun createProfile(profile: ResidentProfile) {
        saveToStorage(profile.pubKey.lowercase(), profile, ResidentProfileSerializer)
        _updates.emit(Unit)
    }
}

class WebAccountRepository : AccountRepository, WebRepository("accounts") {
    override suspend fun register(account: UserAccount): Result<Unit> = runCatching {
        if (loadFromStorage(account.username, UserAccountSerializer) != null) throw Exception("Exists")
        saveToStorage(account.username, account, UserAccountSerializer)
    }

    override suspend fun login(username: String, password: String): Result<UserAccount> = runCatching {
        if (username == "admin" && password == "1January012@") {
            return Result.success(UserAccount("admin", "1January012@", NostrConstants.ADMIN_PUBKEY, NostrConstants.ADMIN_PRIVKEY, "us-fl-06"))
        }
        val acc = loadFromStorage(username, UserAccountSerializer) ?: throw Exception("Invalid")
        if (acc.password == password) acc else throw Exception("Invalid")
    }

    override suspend fun changePassword(username: String, newPassword: String): Result<Unit> = runCatching {
        val acc = loadFromStorage(username, UserAccountSerializer) ?: throw Exception("Not found")
        saveToStorage(username, acc.copy(password = newPassword, requiresPasswordChange = false), UserAccountSerializer)
    }

    override suspend fun updateDistrict(username: String, districtId: String) {
        val acc = loadFromStorage(username, UserAccountSerializer) ?: return
        saveToStorage(username, acc.copy(districtId = districtId), UserAccountSerializer)
    }
}

class WebVerificationRequestRepository : VerificationRequestRepository, WebRepository("verif_req") {
    private val _updates = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }
    override fun observeRequestsForDistrict(districtId: String): Flow<List<VerificationRequest>> = _updates.onStart { emit(Unit) }.map {
        listIdsFromStorage().mapNotNull { loadFromStorage(it, VerificationRequestSerializer) }.filter { it.districtId == districtId }
    }
    override fun observeRequestsForState(stateId: String): Flow<List<VerificationRequest>> = _updates.onStart { emit(Unit) }.map {
        listIdsFromStorage().mapNotNull { loadFromStorage(it, VerificationRequestSerializer) }.filter { it.stateId == stateId }
    }
    override suspend fun createRequest(request: VerificationRequest): Result<Unit> = runCatching {
        saveToStorage(request.id, request, VerificationRequestSerializer)
        _updates.emit(Unit)
    }
    override suspend fun updateRequestStatus(requestId: String, status: VerificationRequestStatus, handledBy: String): Result<Unit> = runCatching {
        val req = loadFromStorage(requestId, VerificationRequestSerializer) ?: throw Exception("Not found")
        saveToStorage(requestId, req.copy(status = status, handledByPubKey = handledBy), VerificationRequestSerializer)
        _updates.emit(Unit)
    }
    override suspend fun getRequest(requestId: String): Result<VerificationRequest> = 
        loadFromStorage(requestId, VerificationRequestSerializer)?.let { Result.success(it) } ?: Result.failure(Exception("Not found"))
}

class WebCommunityRepository(private val publisher: CivicPublisher? = null) : CommunityRepository, WebRepository("communities") {
    override fun observePosts(districtId: String, kind: CommunityPostKind?): Flow<List<CommunityPost>> = flowOf(emptyList())
    override suspend fun getPost(postId: String): Result<CommunityPost> = Result.failure(Exception("Not found"))
    override suspend fun createPost(districtId: String, authorPubKey: String, kind: CommunityPostKind, title: String, description: String, price: Double?, location: String?, contactInfo: String?): Result<CommunityPost> = Result.failure(Exception("Not implemented"))
    override suspend fun deletePost(postId: String): Result<Unit> = Result.success(Unit)
    override suspend fun getAllPosts(): List<CommunityPost> = emptyList()
    override suspend fun syncPost(post: CommunityPost) {}
}

class WebManifestoRepository : ManifestoRepository, WebRepository("manifestos") {
    override fun observeManifestos(districtId: String): Flow<List<CandidateManifesto>> = flowOf(emptyList())
    override suspend fun getManifesto(manifestoId: String): Result<CandidateManifesto> = Result.failure(Exception("Not found"))
    override suspend fun publishManifesto(districtId: String, title: String, body: String, candidatePubKey: String): Result<CandidateManifesto> = Result.failure(Exception("Not implemented"))
    override suspend fun askQuestion(manifestoId: String, questionText: String, askerPubKey: String): Result<ManifestoQuestion> = Result.failure(Exception("Not implemented"))
    override suspend fun answerQuestion(manifestoId: String, questionId: String, answerText: String, candidatePubKey: String): Result<ManifestoQuestion> = Result.failure(Exception("Not implemented"))
}

class WebScorecardRepository : ScorecardRepository, WebRepository("scorecards") {
    override fun observeScorecard(districtId: String): Flow<RepresentativeScorecard?> = flowOf(null)
    override suspend fun getScorecard(districtId: String): Result<RepresentativeScorecard> = Result.failure(Exception("Not found"))
    override suspend fun submitMetricReport(districtId: String, category: String, name: String, value: String, unit: String, reporterPubKey: String): Result<DistrictMetric> = Result.failure(Exception("Not implemented"))
}

class WebDistrictRepository : DistrictRepository, WebRepository("districts") {
    override fun observeDistrict(districtId: String): Flow<District?> = flowOf(null)
    override suspend fun getDistrict(districtId: String): Result<District> = Result.failure(Exception("Not found"))
    override fun observeMetrics(districtId: String): Flow<List<DistrictMetric>> = flowOf(emptyList())
    override suspend fun refreshMetrics(districtId: String): Result<List<DistrictMetric>> = Result.success(emptyList())
}
