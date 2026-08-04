package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.core.CivicPublisher;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.repository.PollRepository;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CivicRepositoryModule_ProvidePollRepositoryFactory implements Factory<PollRepository> {
  private final Provider<AppDatabase> dbProvider;

  private final Provider<CivicPublisher> publisherProvider;

  public CivicRepositoryModule_ProvidePollRepositoryFactory(Provider<AppDatabase> dbProvider,
      Provider<CivicPublisher> publisherProvider) {
    this.dbProvider = dbProvider;
    this.publisherProvider = publisherProvider;
  }

  @Override
  public PollRepository get() {
    return providePollRepository(dbProvider.get(), publisherProvider.get());
  }

  public static CivicRepositoryModule_ProvidePollRepositoryFactory create(
      Provider<AppDatabase> dbProvider, Provider<CivicPublisher> publisherProvider) {
    return new CivicRepositoryModule_ProvidePollRepositoryFactory(dbProvider, publisherProvider);
  }

  public static PollRepository providePollRepository(AppDatabase db, CivicPublisher publisher) {
    return Preconditions.checkNotNullFromProvides(CivicRepositoryModule.INSTANCE.providePollRepository(db, publisher));
  }
}
