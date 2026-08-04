package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.repository.VoteRepository;

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
public final class CivicRepositoryModule_ProvideVoteRepositoryFactory implements Factory<VoteRepository> {
  private final Provider<AppDatabase> dbProvider;

  public CivicRepositoryModule_ProvideVoteRepositoryFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public VoteRepository get() {
    return provideVoteRepository(dbProvider.get());
  }

  public static CivicRepositoryModule_ProvideVoteRepositoryFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicRepositoryModule_ProvideVoteRepositoryFactory(dbProvider);
  }

  public static VoteRepository provideVoteRepository(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicRepositoryModule.INSTANCE.provideVoteRepository(db));
  }
}
