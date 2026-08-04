package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.repository.ScorecardRepository;
import net.wetheGoverned.session.SessionManager;
import net.wetheGoverned.ui.ScorecardViewModel;

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
public final class ViewModelModule_ProvideScorecardViewModelFactory implements Factory<ScorecardViewModel> {
  private final Provider<ScorecardRepository> scorecardRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ViewModelModule_ProvideScorecardViewModelFactory(
      Provider<ScorecardRepository> scorecardRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.scorecardRepositoryProvider = scorecardRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ScorecardViewModel get() {
    return provideScorecardViewModel(scorecardRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static ViewModelModule_ProvideScorecardViewModelFactory create(
      Provider<ScorecardRepository> scorecardRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ViewModelModule_ProvideScorecardViewModelFactory(scorecardRepositoryProvider, sessionManagerProvider);
  }

  public static ScorecardViewModel provideScorecardViewModel(
      ScorecardRepository scorecardRepository, SessionManager sessionManager) {
    return Preconditions.checkNotNullFromProvides(ViewModelModule.INSTANCE.provideScorecardViewModel(scorecardRepository, sessionManager));
  }
}
