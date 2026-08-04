package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.data.local.dao.DistrictDao;

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
public final class CivicDatabaseModule_ProvideDistrictDaoFactory implements Factory<DistrictDao> {
  private final Provider<AppDatabase> dbProvider;

  public CivicDatabaseModule_ProvideDistrictDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DistrictDao get() {
    return provideDistrictDao(dbProvider.get());
  }

  public static CivicDatabaseModule_ProvideDistrictDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicDatabaseModule_ProvideDistrictDaoFactory(dbProvider);
  }

  public static DistrictDao provideDistrictDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicDatabaseModule.INSTANCE.provideDistrictDao(db));
  }
}
