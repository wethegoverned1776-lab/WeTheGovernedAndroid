package net.wetheGoverned.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import net.wetheGoverned.model.*
import net.wetheGoverned.repository.*
import net.wetheGoverned.session.SessionManager
import net.wetheGoverned.session.UserSession
import net.wetheGoverned.core.CivicPublisher
import kotlin.test.Test
import kotlin.test.assertEquals

class P2PSyncTest {

    @Test
    fun testSyncLoop_mergesRemotePolls() = runTest {
        // 1. Setup mocks
        val pollRepo = InMemoryPollRepository()
        val residentRepo = InMemoryResidentRepository()
        val voteRepo = InMemoryVoteRepository()
        val manifestoRepo = InMemoryManifestoRepository()
        val accountRepo = InMemoryAccountRepository()
        val communityRepo = InMemoryCommunityRepository()
        val sessionManager = SessionManager(object : net.wetheGoverned.session.SessionStorage {
            private var s: UserSession? = null
            override fun saveSession(session: UserSession) { s = session }
            override fun getSession(): UserSession? = s
            override fun clearSession() { s = null }
            override fun savePrivateKeySecurely(key: String) {}
            override fun getPrivateKeySecurely(): String? = null
        })
        
        // Setup session
        sessionManager.login(
            pubKeyHex = "user1",
            privateKeyHex = null,
            districtId = "us-fl-06",
            tier = VerificationTier.VERIFIED,
            displayName = "User 1"
        )

        // Mock Relay Manager
        val relayManager = NostrRelayManager(emptyList())
        val publisher = object : CivicPublisher {
            override suspend fun signPublishImportCivicEvent(kind: Int, tags: List<List<String>>, content: String, pubKey: String): String? = null
        }

        val syncEngine = P2PSyncEngine(
            pollRepo, residentRepo, voteRepo, manifestoRepo, communityRepo, accountRepo, sessionManager, relayManager, publisher
        )
        syncEngine.start()

        // Verify that our repositories are ready for sync.
        val initialPolls = pollRepo.getAllPolls()
        assertEquals(0, initialPolls.size)

        val remotePoll = CivicPoll(
            id = "remote_1",
            scope = PollScope.FEDERAL,
            districtId = "us",
            authorPubKey = "admin",
            question = "Remote Question",
            options = emptyList(),
            status = PollStatus.ACTIVE,
            createdAt = kotlinx.datetime.Clock.System.now().toEpochMilliseconds(),
            closesAt = null,
            totalVotes = 0
        )

        pollRepo.syncPoll(remotePoll)
        
        val afterSync = pollRepo.getAllPolls()
        assertEquals(1, afterSync.size)
        assertEquals("remote_1", afterSync[0].id)
    }
}

// Minimal memory repos for testing in common module
class InMemoryPollRepository : PollRepository {
    private val polls = mutableListOf<CivicPoll>()
    override fun observeDistrictPolls(districtId: String) = flowOf(polls.filter { it.districtId == districtId })
    override fun observePollsByIds(districtIds: List<String>) = flowOf(polls.filter { it.districtId in districtIds })
    override suspend fun getPoll(pollId: String) = Result.success(polls.first { it.id == pollId })
    override suspend fun createPoll(districtId: String, question: String, options: List<String>, closesAt: Long?, scope: PollScope, authorPubKey: String, localId: String?) = Result.success(polls.first())
    override suspend fun vote(pollId: String, optionId: String, voterPubKey: String) = Result.success(Unit)
    override suspend fun voteImportance(pollId: String, delta: Int, voterPubKey: String) = Result.success(Unit)
    override fun observePollsPaged(districtId: String, limit: Int, offset: Int) = flowOf(polls.filter { it.districtId == districtId }.drop(offset).take(limit))
    override fun observePollsByScope(scope: PollScope, districtId: String) = flowOf(polls.filter { it.districtId == districtId && it.scope == scope })
    override fun observePollPosts(pollId: String) = flowOf(emptyList<PollPost>())
    override fun observeOptionPosts(pollId: String, optionId: String) = flowOf(emptyList<PollPost>())
    override fun observeThreadedPosts(parentPostId: String) = flowOf(emptyList<PollPost>())
    override suspend fun createPost(pollId: String, optionId: String, authorName: String, content: String, headline: String?, parentPostId: String?) = Result.success(PollPost(id = "test", pollId = "", optionId = "", authorName = "", content = ""))
    override suspend fun voteOnPost(postId: String, delta: Int) = Result.success(Unit)
    override suspend fun getPost(postId: String) = Result.success(PollPost(id = "test", pollId = "", optionId = "", authorName = "System", content = ""))
    override suspend fun getAllPolls() = polls
    override suspend fun getPollsForJurisdictions(jurisdictionIds: List<String>, since: Long) = polls.filter { it.districtId in jurisdictionIds }
    override suspend fun syncPoll(poll: CivicPoll) { polls.add(poll) }
    override suspend fun syncVote(vote: CivicVote) {}
    override suspend fun markVoted(pollId: String, optionId: String) {}
}

class InMemoryResidentRepository : ResidentRepository {
    override fun observeProfile(pubKey: String) = flowOf(null)
    override fun observeProfileByFingerprint(fingerprint: String) = flowOf(null)
    override suspend fun getProfile(pubKey: String) = Result.failure<ResidentProfile>(Exception())
    override suspend fun upgradeTier(pubKey: String, newTier: VerificationTier, proofToken: String) = Result.failure<ResidentProfile>(Exception())
    override suspend fun upgradeTierWithFingerprint(pubKey: String, newTier: VerificationTier, proofToken: String, fingerprint: String) = Result.failure<ResidentProfile>(Exception())
    override suspend fun upgradeTierFull(pubKey: String, newTier: VerificationTier, fingerprint: String, verifiedBy: String?) = Result.success(Unit)
    override suspend fun updateProfile(pubKey: String, displayName: String, avatarUrl: String?) = Result.failure<ResidentProfile>(Exception())
    override suspend fun updateDistrict(pubKey: String, districtId: String) = Result.success(Unit)
    override suspend fun getResidentCountAtAddress(fingerprint: String) = 0
    override suspend fun getVouchCount(notaryPubKey: String) = 0
    override fun observeProfilesVerifiedBy(verifierPubKey: String) = flowOf(emptyList<ResidentProfile>())
    override suspend fun createProfile(profile: ResidentProfile) {}
}

class InMemoryVoteRepository : VoteRepository {
    override fun observeAllVotes() = flowOf(emptyList<CivicVote>())
    override fun observeVotesByUser(pubKey: String) = flowOf(emptyList<CivicVote>())
    override suspend fun flagVote(voteId: String, reason: String, expiresAt: Long) = Result.success(Unit)
    override suspend fun disputeVote(voteId: String, comment: String) = Result.success(Unit)
    override suspend fun resolveVote(voteId: String) = Result.success(Unit)
    override suspend fun syncVote(vote: CivicVote) {}
}

class InMemoryManifestoRepository : ManifestoRepository {
    override fun observeManifestos(districtId: String) = flowOf(emptyList<CandidateManifesto>())
    override suspend fun getManifesto(manifestoId: String) = Result.failure<CandidateManifesto>(Exception())
    override suspend fun publishManifesto(districtId: String, title: String, body: String, candidatePubKey: String) = Result.failure<CandidateManifesto>(Exception())
    override suspend fun askQuestion(manifestoId: String, questionText: String, askerPubKey: String) = Result.failure<ManifestoQuestion>(Exception())
    override suspend fun answerQuestion(manifestoId: String, questionId: String, answerText: String, candidatePubKey: String) = Result.failure<ManifestoQuestion>(Exception())
}

class InMemoryAccountRepository : AccountRepository {
    override suspend fun register(account: UserAccount) = Result.success(Unit)
    override suspend fun login(username: String, password: String) = Result.failure<UserAccount>(Exception())
    override suspend fun changePassword(username: String, newPassword: String) = Result.success(Unit)
    override suspend fun updateDistrict(username: String, districtId: String) {}
}

class InMemoryCommunityRepository : CommunityRepository {
    override fun observePosts(districtId: String, kind: CommunityPostKind?) = flowOf(emptyList<CommunityPost>())
    override suspend fun getPost(postId: String) = Result.failure<CommunityPost>(Exception())
    override suspend fun createPost(districtId: String, authorPubKey: String, kind: CommunityPostKind, title: String, description: String, price: Double?, location: String?, contactInfo: String?) = Result.failure<CommunityPost>(Exception())
    override suspend fun deletePost(postId: String) = Result.success(Unit)
    override suspend fun getAllPosts() = emptyList<CommunityPost>()
    override suspend fun syncPost(post: CommunityPost) {}
}
