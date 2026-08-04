package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.repository.CommunityRepository;
import net.wetheGoverned.session.SessionManager;
import net.wetheGoverned.ui.CommunityBoardViewModel;

@ScopeMetadata("dagger.hilt.android.scopes.ViewModelScoped")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ViewModelModule_ProvideCommunityBoardViewModelFactory implements Factory<CommunityBoardViewModel> {
  private final Provider<CommunityRepository> communityRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ViewModelModule_ProvideCommunityBoardViewModelFactory(
      Provider<CommunityRepository> communityRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.communityRepositoryProvider = communityRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public CommunityBoardViewModel get() {
    return provideCommunityBoardViewModel(communityRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static ViewModelModule_ProvideCommunityBoardViewModelFactory create(
      Provider<CommunityRepository> communityRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ViewModelModule_ProvideCommunityBoardViewModelFactory(communityRepositoryProvider, sessionManagerProvider);
  }

  public static CommunityBoardViewModel provideCommunityBoardViewModel(
      CommunityRepository communityRepository, SessionManager sessionManager) {
    return Preconditions.checkNotNullFromProvides(ViewModelModule.INSTANCE.provideCommunityBoardViewModel(communityRepository, sessionManager));
  }
}
