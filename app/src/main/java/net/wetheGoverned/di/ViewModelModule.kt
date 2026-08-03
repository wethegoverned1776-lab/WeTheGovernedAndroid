package net.wetheGoverned.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import net.wetheGoverned.repository.*
import net.wetheGoverned.session.SessionManager
import net.wetheGoverned.ui.HomeViewModel
import net.wetheGoverned.ui.PollViewModel
import net.wetheGoverned.ui.ResidentProfileViewModel

import net.wetheGoverned.ui.*

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideHomeViewModel(
        pollRepository: PollRepository,
        residentRepository: ResidentRepository,
        sessionManager: SessionManager,
        relayManager: net.wetheGoverned.data.NostrRelayManager
    ): HomeViewModel = HomeViewModel(pollRepository, residentRepository, sessionManager, relayManager)

    @Provides
    @ViewModelScoped
    fun providePollViewModel(
        pollRepository: PollRepository,
        sessionManager: SessionManager
    ): PollViewModel = PollViewModel(pollRepository, sessionManager)

    @Provides
    @ViewModelScoped
    fun provideResidentProfileViewModel(
        residentRepository: ResidentRepository,
        accountRepository: AccountRepository,
        sessionManager: SessionManager,
        requestRepository: VerificationRequestRepository,
    ): ResidentProfileViewModel = ResidentProfileViewModel(residentRepository, accountRepository, sessionManager, requestRepository)

    @Provides
    @ViewModelScoped
    fun provideManifestoViewModel(
        manifestoRepository: ManifestoRepository,
        pollRepository: PollRepository,
        sessionManager: SessionManager
    ): ManifestoViewModel = ManifestoViewModel(manifestoRepository, pollRepository, sessionManager)

    @Provides
    @ViewModelScoped
    fun provideScorecardViewModel(
        scorecardRepository: ScorecardRepository,
        sessionManager: SessionManager
    ): ScorecardViewModel = ScorecardViewModel(scorecardRepository, sessionManager)

    @Provides
    @ViewModelScoped
    fun provideCommunityBoardViewModel(
        communityRepository: CommunityRepository,
        sessionManager: SessionManager
    ): CommunityBoardViewModel = CommunityBoardViewModel(communityRepository, sessionManager)
}

