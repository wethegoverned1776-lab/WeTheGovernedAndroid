package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.data.local.dao.CommunityPostDao;

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
public final class CivicDatabaseModule_ProvideCommunityDaoFactory implements Factory<CommunityPostDao> {
  private final Provider<AppDatabase> dbProvider;

  public CivicDatabaseModule_ProvideCommunityDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CommunityPostDao get() {
    return provideCommunityDao(dbProvider.get());
  }

  public static CivicDatabaseModule_ProvideCommunityDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicDatabaseModule_ProvideCommunityDaoFactory(dbProvider);
  }

  public static CommunityPostDao provideCommunityDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicDatabaseModule.INSTANCE.provideCommunityDao(db));
  }
}
