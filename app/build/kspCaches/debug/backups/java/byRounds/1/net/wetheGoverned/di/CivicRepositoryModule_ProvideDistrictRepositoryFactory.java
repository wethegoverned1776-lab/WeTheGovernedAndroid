package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.repository.DistrictRepository;

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
public final class CivicRepositoryModule_ProvideDistrictRepositoryFactory implements Factory<DistrictRepository> {
  private final Provider<AppDatabase> dbProvider;

  public CivicRepositoryModule_ProvideDistrictRepositoryFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DistrictRepository get() {
    return provideDistrictRepository(dbProvider.get());
  }

  public static CivicRepositoryModule_ProvideDistrictRepositoryFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicRepositoryModule_ProvideDistrictRepositoryFactory(dbProvider);
  }

  public static DistrictRepository provideDistrictRepository(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicRepositoryModule.INSTANCE.provideDistrictRepository(db));
  }
}
