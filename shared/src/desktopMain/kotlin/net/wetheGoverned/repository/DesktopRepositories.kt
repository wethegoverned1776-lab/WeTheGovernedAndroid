package net.wetheGoverned.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.*
import kotlinx.serialization.builtins.*
import net.wetheGoverned.model.*
import net.wetheGoverned.core.*
import net.wetheGoverned.session.*
import net.wetheGoverned.remote.api.*
import java.io.File
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock

abstract class FileBasedRepository(private val type: String) {
    protected val baseDir = File(System.getProperty("user.home"), ".wethegoverned/$type").apply { mkdirs() }
    private val indexFile = File(baseDir, "index.json")
    private val indexMutex = Mutex()
    protected val json = CivicJson

    protected suspend fun <T> save(id: String, data: T, serializer: kotlinx.serialization.KSerializer<T>) {
        val file = File(baseDir, "$id.json")
        file.writeText(json.encodeToString(serializer, data))
    }

    protected fun <T> load(id: String, serializer: kotlinx.serialization.KSerializer<T>): T? {
        val file = File(baseDir, "$id.json")
        return if (file.exists()) json.decodeFromString(serializer, file.readText()) else null
    }

    fun listIds(): List<String> = baseDir.listFiles { f -> f.extension == "json" && f.name != "index.json" }?.map { it.nameWithoutExtension } ?: emptyList()

    protected suspend fun addToIndex(key: String, value: String, id: String) = indexMutex.withLock {
        val index = loadIndex()
        val keyIndex = index.getOrPut(key) { mutableMapOf() }
        val valueSet = keyIndex.getOrPut(value) { mutableSetOf() }
        valueSet.add(id)
        saveIndex(index)
    }

    protected fun getFromIndexPaged(key: String, value: String, limit: Int, offset: Int): List<String> {
        val index = runBlocking { loadIndex() }
        return index[key]?.get(value)?.toList()?.drop(offset)?.take(limit) ?: emptyList()
    }

    private fun loadIndex(): MutableMap<String, MutableMap<String, MutableSet<String>>> {
        if (!indexFile.exists()) return mutableMapOf()
        return try {
            json.decodeFromString(indexFile.readText())
        } catch (e: Exception) {
            mutableMapOf()
        }
    }

    private fun saveIndex(index: Map<String, Map<String, Set<String>>>) {
        val serializer = MapSerializer(String.serializer(), MapSerializer(String.serializer(), SetSerializer(String.serializer())))
        indexFile.writeText(json.encodeToString(serializer, index))
    }
}

class DesktopVoteRepository(private val publisher: CivicPublisher? = null) : VoteRepository, FileBasedRepository("votes") {
    override fun observeAllVotes(): Flow<List<CivicVote>> = flow {
        emit(listIds().mapNotNull { load(it, CivicVoteSerializer) })
    }
    override fun observeVotesByUser(pubKey: String): Flow<List<CivicVote>> = flow {
        emit(listIds().mapNotNull { load(it, CivicVoteSerializer) }.filter { it.voterPubKey == pubKey })
    }
    override suspend fun flagVote(voteId: String, reason: String, expiresAt: Long): Result<Unit> = Result.success(Unit)
    override suspend fun disputeVote(voteId: String, comment: String): Result<Unit> = Result.success(Unit)
    override suspend fun resolveVote(voteId: String): Result<Unit> = Result.success(Unit)
    override suspend fun syncVote(vote: CivicVote) { save(vote.id, vote, CivicVoteSerializer) }
}

class DesktopPollRepository(private val publisher: CivicPublisher? = null) : PollRepository, FileBasedRepository("polls") {
    private val _pollsFlow = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }
    private val samplingPollsFlow = _pollsFlow.sample(200L)

    override fun observeDistrictPolls(districtId: String): Flow<List<CivicPoll>> = samplingPollsFlow.flatMapLatest {
        flow {
            val stateId = districtId.substringBeforeLast('-', "us")
            val ids = getFromIndexPaged("district", districtId, 100, 0) + getFromIndexPaged("district", stateId, 100, 0) + getFromIndexPaged("district", "us", 100, 0)
            emit(ids.distinct().mapNotNull { load(it, CivicPollSerializer) })
        }
    }

    override fun observePollsByIds(districtIds: List<String>): Flow<List<CivicPoll>> = samplingPollsFlow.flatMapLatest {
        flow {
            val allIds = districtIds.flatMap { getFromIndexPaged("district", it, 50, 0) }
            emit(allIds.distinct().mapNotNull { load(it, CivicPollSerializer) })
        }
    }

    override fun observePollsByScope(scope: CivicScope, districtId: String): Flow<List<CivicPoll>> = samplingPollsFlow.flatMapLatest {
        flow {
            val key = when(scope) {
                CivicScope.FEDERAL -> "us"
                CivicScope.STATE -> districtId.substringBeforeLast('-', "us")
                else -> districtId
            }
            emit(getFromIndexPaged("district", key, 100, 0).mapNotNull { load(it, CivicPollSerializer) })
        }
    }

    override suspend fun getPoll(pollId: String): Result<CivicPoll> =
        load(pollId, CivicPollSerializer)?.let { Result.success(it) } ?: Result.failure(Exception("Not found"))

    override suspend fun createPoll(districtId: String, question: String, options: List<String>, closesAt: Long?, scope: CivicScope, authorPubKey: String, localId: String?): Result<CivicPoll> {
        val normalizedPubKey = authorPubKey.lowercase()
        val now = Clock.System.now().toEpochMilliseconds()
        val id = sha256("poll_${question}_${normalizedPubKey}_$now").take(64)
        val newPoll = CivicPoll(id = id, scope = scope, districtId = districtId, localId = localId, authorPubKey = normalizedPubKey, question = question, options = options.mapIndexed { i, s -> PollOption("opt_$i", label = s, 0, 0f) }, status = PollStatus.ACTIVE, createdAt = now, closesAt = closesAt ?: (now + 86400000), totalVotes = 0)
        save(id, newPoll, CivicPollSerializer)
        addToIndex("district", districtId, id)
        localId?.let { addToIndex("district", it, id) }
        
        publisher?.signPublishImportCivicEvent(
            kind = when(scope) {
                CivicScope.FEDERAL -> CivicEventKind.FEDERAL_POLL
                CivicScope.STATE -> CivicEventKind.STATE_POLL
                CivicScope.LOCAL -> CivicEventKind.LOCAL_POLL
                else -> CivicEventKind.DISTRICT_POLL
            },
            tags = listOf(listOf("d", id), listOf("g", districtId)),
            content = json.encodeToString(CivicPollSerializer, newPoll),
            pubKey = normalizedPubKey
        )
        
        _pollsFlow.emit(Unit)
        return Result.success(newPoll)
    }

    override suspend fun vote(pollId: String, optionId: String, voterPubKey: String): Result<Unit> {
        val normalizedPubKey = voterPubKey.lowercase()
        val poll = load(pollId, CivicPollSerializer) ?: return Result.failure(Exception("Poll not found"))
        val updatedOptions = poll.options.map { opt -> if (opt.id == optionId) opt.copy(voteCount = opt.voteCount + 1) else opt }
        val newTotal = poll.totalVotes + 1
        val updatedPoll = poll.copy(options = updatedOptions.map { it.copy(percentageOfTotal = it.voteCount.toFloat() / newTotal) }, totalVotes = newTotal, residentVoteOption = optionId)
        save(pollId, updatedPoll, CivicPollSerializer)
        
        val now = Clock.System.now().toEpochMilliseconds()
        val vote = CivicVote(
            id = sha256("vote_${pollId}_${normalizedPubKey}_$now").take(64),
            pollId = pollId,
            optionId = optionId,
            voterPubKey = normalizedPubKey,
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
            content = json.encodeToString(CivicVoteSerializer, vote),
            pubKey = normalizedPubKey
        )
        
        _pollsFlow.emit(Unit)
        return Result.success(Unit)
    }

    override suspend fun voteImportance(pollId: String, delta: Int, voterPubKey: String): Result<Unit> {
        val normalizedPubKey = voterPubKey.lowercase()
        val poll = load(pollId, CivicPollSerializer) ?: return Result.failure(Exception("Poll not found"))
        val updatedPoll = poll.copy(
            importanceScore = poll.importanceScore + delta,
            userImportanceVote = delta
        )
        save(pollId, updatedPoll, CivicPollSerializer)
        
        publisher?.signPublishImportCivicEvent(
            kind = CivicEventKind.IMPORTANCE_VOTE,
            tags = listOf(listOf("d", "${pollId}_$normalizedPubKey"), listOf("g", poll.districtId)),
            content = "$pollId:$delta",
            pubKey = normalizedPubKey
        )

        _pollsFlow.emit(Unit)
        return Result.success(Unit)
    }

    override fun observePollsPaged(districtId: String, limit: Int, offset: Int): Flow<List<CivicPoll>> = samplingPollsFlow.flatMapLatest {
        flow { emit(getFromIndexPaged("district", districtId, limit, offset).mapNotNull { load(it, CivicPollSerializer) }) }
    }

    override fun observePollPosts(pollId: String): Flow<List<PollPost>> = flow { emit(emptyList()) }
    override fun observeOptionPosts(pollId: String, optionId: String): Flow<List<PollPost>> = flow { emit(emptyList()) }
    override fun observeThreadedPosts(parentPostId: String): Flow<List<PollPost>> = flow { emit(emptyList()) }
    override suspend fun createPost(pollId: String, optionId: String, authorName: String, content: String, headline: String?, parentPostId: String?): Result<PollPost> = Result.failure(Exception("Not implemented"))
    override suspend fun voteOnPost(postId: String, delta: Int): Result<Unit> = Result.success(Unit)
    override suspend fun getPost(postId: String): Result<PollPost> = Result.failure(Exception("Stub"))
    override suspend fun getAllPolls(): List<CivicPoll> = listIds().mapNotNull { runBlocking { load(it, CivicPollSerializer) } }
    override suspend fun getPollsForJurisdictions(jurisdictionIds: List<String>, since: Long): List<CivicPoll> = getAllPolls().filter { (it.districtId in jurisdictionIds || it.localId in jurisdictionIds) && it.createdAt > since }
    
    override suspend fun syncPoll(poll: CivicPoll) {
        save(poll.id, poll, CivicPollSerializer)
        addToIndex("district", poll.districtId, poll.id)
        poll.localId?.let { addToIndex("district", it, poll.id) }
        _pollsFlow.emit(Unit)
    }

    override suspend fun syncVote(vote: CivicVote) {
        val poll = load(vote.pollId, CivicPollSerializer) ?: return
        val updatedOptions = poll.options.map { opt ->
            if (opt.id == vote.optionId) opt.copy(voteCount = opt.voteCount + 1) else opt
        }
        val newTotal = poll.totalVotes + 1
        val updatedPoll = poll.copy(
            options = updatedOptions.map { it.copy(percentageOfTotal = it.voteCount.toFloat() / newTotal) },
            totalVotes = newTotal
        )
        save(vote.pollId, updatedPoll, CivicPollSerializer)
        _pollsFlow.emit(Unit)
    }

    override suspend fun markVoted(pollId: String, optionId: String) {
        val poll = load(pollId, CivicPollSerializer) ?: return
        if (poll.residentVoteOption == optionId) return
        val updated = poll.copy(residentVoteOption = optionId)
        save(pollId, updated, CivicPollSerializer)
        _pollsFlow.emit(Unit)
    }
}

class DesktopResidentRepository(private val publisher: CivicPublisher? = null) : ResidentRepository, FileBasedRepository("residents") {
    override fun observeProfile(pubKey: String): Flow<ResidentProfile?> = flow { emit(load(pubKey.lowercase(), ResidentProfileSerializer)) }
    override fun observeProfileByFingerprint(fingerprint: String): Flow<ResidentProfile?> = flow { emit(null) }
    override suspend fun getResidentCountAtAddress(fingerprint: String): Int = 0
    override suspend fun getVouchCount(notaryPubKey: String): Int = 0
    override suspend fun getProfile(pubKey: String): Result<ResidentProfile> = load(pubKey.lowercase(), ResidentProfileSerializer)?.let { Result.success(it) } ?: Result.failure(Exception("Not found"))
    override suspend fun upgradeTier(pubKey: String, newTier: VerificationTier, proofToken: String): Result<ResidentProfile> = Result.failure(Exception("Not implemented"))
    override suspend fun upgradeTierWithFingerprint(pubKey: String, newTier: VerificationTier, proofToken: String, fingerprint: String): Result<ResidentProfile> = Result.failure(Exception("Not implemented"))
    override suspend fun upgradeTierFull(pubKey: String, newTier: VerificationTier, fingerprint: String, verifiedBy: String?): Result<Unit> = Result.success(Unit)
    override suspend fun updateProfile(pubKey: String, displayName: String, avatarUrl: String?): Result<ResidentProfile> = Result.failure(Exception("Not implemented"))
    override suspend fun updateDistrict(pubKey: String, districtId: String): Result<Unit> = Result.success(Unit)
    override fun observeProfilesVerifiedBy(verifierPubKey: String): Flow<List<ResidentProfile>> = flowOf(emptyList())
    override suspend fun createProfile(profile: ResidentProfile) { save(profile.pubKey.lowercase(), profile, ResidentProfileSerializer) }
}

class DesktopCommunityRepository(private val publisher: CivicPublisher? = null) : CommunityRepository, FileBasedRepository("community") {
    override fun observePosts(districtId: String, kind: CommunityPostKind?): Flow<List<CommunityPost>> = flowOf(emptyList())
    override suspend fun getPost(postId: String): Result<CommunityPost> = Result.failure(Exception("Not found"))
    override suspend fun createPost(districtId: String, authorPubKey: String, kind: CommunityPostKind, title: String, description: String, price: Double?, location: String?, contactInfo: String?): Result<CommunityPost> = Result.failure(Exception("Not implemented"))
    override suspend fun deletePost(postId: String): Result<Unit> = Result.success(Unit)
    override suspend fun getAllPosts(): List<CommunityPost> = emptyList()
    override suspend fun syncPost(post: CommunityPost) { save(post.id, post, CommunityPostSerializer) }
}

class DesktopAccountRepository : AccountRepository, FileBasedRepository("accounts") {
    override suspend fun login(username: String, password: String): Result<UserAccount> = load(username, UserAccountSerializer)?.let { Result.success(it) } ?: Result.failure(Exception("Login failed"))
    override suspend fun register(account: UserAccount): Result<Unit> {
        save(account.username, account, UserAccountSerializer)
        return Result.success(Unit)
    }
    override suspend fun changePassword(username: String, newPassword: String): Result<Unit> = Result.success(Unit)
    override suspend fun updateDistrict(username: String, districtId: String) {}
}

class DesktopVerificationRequestRepository : VerificationRequestRepository {
    override fun observeRequestsForDistrict(districtId: String): Flow<List<VerificationRequest>> = flowOf(emptyList())
    override fun observeRequestsForState(stateId: String): Flow<List<VerificationRequest>> = flowOf(emptyList())
    override suspend fun createRequest(request: VerificationRequest): Result<Unit> = Result.success(Unit)
    override suspend fun updateRequestStatus(requestId: String, status: VerificationRequestStatus, handledBy: String): Result<Unit> = Result.success(Unit)
    override suspend fun getRequest(requestId: String): Result<VerificationRequest> = Result.failure(Exception("Not found"))
}
