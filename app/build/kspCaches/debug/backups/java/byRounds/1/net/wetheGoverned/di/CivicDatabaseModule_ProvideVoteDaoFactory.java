package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.data.local.dao.VoteDao;

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
public final class CivicDatabaseModule_ProvideVoteDaoFactory implements Factory<VoteDao> {
  private final Provider<AppDatabase> dbProvider;

  public CivicDatabaseModule_ProvideVoteDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public VoteDao get() {
    return provideVoteDao(dbProvider.get());
  }

  public static CivicDatabaseModule_ProvideVoteDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new CivicDatabaseModule_ProvideVoteDaoFactory(dbProvider);
  }

  public static VoteDao provideVoteDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicDatabaseModule.INSTANCE.provideVoteDao(db));
  }
}
