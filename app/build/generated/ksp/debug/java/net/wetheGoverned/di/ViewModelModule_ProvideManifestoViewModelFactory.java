package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.repository.ManifestoRepository;
import net.wetheGoverned.repository.PollRepository;
import net.wetheGoverned.session.SessionManager;
import net.wetheGoverned.ui.ManifestoViewModel;

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
public final class ViewModelModule_ProvideManifestoViewModelFactory implements Factory<ManifestoViewModel> {
  private final Provider<ManifestoRepository> manifestoRepositoryProvider;

  private final Provider<PollRepository> pollRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ViewModelModule_ProvideManifestoViewModelFactory(
      Provider<ManifestoRepository> manifestoRepositoryProvider,
      Provider<PollRepository> pollRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.manifestoRepositoryProvider = manifestoRepositoryProvider;
    this.pollRepositoryProvider = pollRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ManifestoViewModel get() {
    return provideManifestoViewModel(manifestoRepositoryProvider.get(), pollRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static ViewModelModule_ProvideManifestoViewModelFactory create(
      Provider<ManifestoRepository> manifestoRepositoryProvider,
      Provider<PollRepository> pollRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ViewModelModule_ProvideManifestoViewModelFactory(manifestoRepositoryProvider, pollRepositoryProvider, sessionManagerProvider);
  }

  public static ManifestoViewModel provideManifestoViewModel(
      ManifestoRepository manifestoRepository, PollRepository pollRepository,
      SessionManager sessionManager) {
    return Preconditions.checkNotNullFromProvides(ViewModelModule.INSTANCE.provideManifestoViewModel(manifestoRepository, pollRepository, sessionManager));
  }
}
