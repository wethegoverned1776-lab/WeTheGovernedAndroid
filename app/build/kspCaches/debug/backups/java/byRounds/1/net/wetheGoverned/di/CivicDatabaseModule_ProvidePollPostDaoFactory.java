package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.data.local.dao.PollPostDao;

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
public final class CivicDatabaseModule_ProvidePollPostDaoFactory implements Factory<PollPostDao> {
  private final Provider<AppDatabase> dbProvider;

  public CivicDatabaseModule_ProvidePollPostDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PollPostDao get() {
    return providePollPostDao(dbProvider.get());
  }

  public static CivicDatabaseModule_ProvidePollPostDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicDatabaseModule_ProvidePollPostDaoFactory(dbProvider);
  }

  public static PollPostDao providePollPostDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicDatabaseModule.INSTANCE.providePollPostDao(db));
  }
}
