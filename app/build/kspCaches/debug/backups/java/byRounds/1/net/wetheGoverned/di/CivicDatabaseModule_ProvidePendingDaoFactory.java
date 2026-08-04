package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.data.local.dao.PendingEventDao;

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
public final class CivicDatabaseModule_ProvidePendingDaoFactory implements Factory<PendingEventDao> {
  private final Provider<AppDatabase> dbProvider;

  public CivicDatabaseModule_ProvidePendingDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PendingEventDao get() {
    return providePendingDao(dbProvider.get());
  }

  public static CivicDatabaseModule_ProvidePendingDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicDatabaseModule_ProvidePendingDaoFactory(dbProvider);
  }

  public static PendingEventDao providePendingDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicDatabaseModule.INSTANCE.providePendingDao(db));
  }
}
