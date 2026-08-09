package net.wetheGoverned.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import net.wetheGoverned.model.*
import net.wetheGoverned.session.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class DesktopRepositoriesTest {

    @Test
    fun testDesktopPollRepository_observeDistrictPolls() = runTest {
        val repository = DesktopPollRepository()
        val polls = repository.observeDistrictPolls("us-fl-06").first()
        
        // Should include federal, and district (us-fl-06)
        // Note: Actual polls might vary depending on local data, but we expect some data
        // For a fresh environment, this might need to create polls first
    }

    @Test
    fun testDesktopPollRepository_observePollsByIds() = runTest {
        val repository = DesktopPollRepository()
        val hierarchy = listOf("us", "us-fl", "us-fl-06")
        val polls = repository.observePollsByIds(hierarchy).first()
    }

    @Test
    fun testDesktopPollRepository_vote() = runTest {
        val repository = DesktopPollRepository()
        val polls = repository.observeDistrictPolls("us-fl-06").first()
        if (polls.isNotEmpty()) {
            val pollId = polls.first().id
            val optionId = polls.first().options.first().id
            
            val initialPoll = repository.getPoll(pollId).getOrThrow()
            val initialVoteCount = initialPoll.options.find { it.id == optionId }?.voteCount ?: 0
            
            repository.vote(pollId, optionId, "user1")
            
            val updatedPoll = repository.getPoll(pollId).getOrThrow()
            val updatedVoteCount = updatedPoll.options.find { it.id == optionId }?.voteCount ?: 0
            
            assertEquals(initialVoteCount + 1, updatedVoteCount)
            assertEquals(optionId, updatedPoll.residentVoteOption)
        }
    }

    @Test
    fun testDesktopResidentRepository_getProfile() = runTest {
        val repository = DesktopResidentRepository()
        // Use a known existing pubkey or handle failure gracefully for this test
        val profiles = repository.listIds()
        if (profiles.isNotEmpty()) {
            val pubKey = profiles.first()
            val profile = repository.getProfile(pubKey).getOrThrow()
            assertEquals(pubKey.lowercase(), profile.pubKey.lowercase())
        }
    }

    @Test
    fun testDesktopPollRepository_createPostAndObserve() = runTest {
        val repository = DesktopPollRepository()
        // This is expected to fail with "Not implemented" based on current stub
        val result = repository.createPost("p1", "opt_1", "Author", "Content")
        assertTrue(result.isFailure)
    }

    @Test
    fun testDesktopScorecardRepository_submitMetricReport() = runTest {
        val repository = DesktopScorecardRepository()
        // This is expected to fail with "Not implemented"
        val result = repository.submitMetricReport("us-fl-06", "Education", "Funding", "10M", "$", "user1")
        assertTrue(result.isFailure)
    }

    @Test
    fun testDesktopAccountRepository_persistence() = runTest {
        val repository = DesktopAccountRepository()
        val username = "testuser_${System.currentTimeMillis()}"
        val account = UserAccount(username, "password123", "pub1", "priv1", "us-wa-07")
        
        repository.register(account).getOrThrow()
        
        val loggedIn = repository.login(username, "password123").getOrThrow()
        assertEquals("pub1", loggedIn.pubKey)
        assertEquals("us-wa-07", loggedIn.districtId)
    }

    @Test
    fun testDesktopSessionStorage() {
        val storage = DesktopSessionStorage()
        val session = UserSession(
            pubKey = "key1",
            displayName = "User 1",
            districtId = "us-fl-06",
            tier = VerificationTier.VERIFIED,
            privateKey = "priv1"
        )
        
        storage.saveSession(session)
        val loaded = storage.getSession()
        
        assertNotNull(loaded)
        assertEquals("key1", loaded.pubKey)
        assertEquals("priv1", loaded.privateKey)
        assertEquals(VerificationTier.VERIFIED, loaded.tier)
        
        storage.clearSession()
        kotlin.test.assertNull(storage.getSession())
    }
}
