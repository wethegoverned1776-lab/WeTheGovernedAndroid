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
import net.wetheGoverned.repository.ResidentRepository;

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
public final class CivicRepositoryModule_ProvideResidentRepositoryFactory implements Factory<ResidentRepository> {
  private final Provider<AppDatabase> dbProvider;

  private final Provider<CivicPublisher> publisherProvider;

  public CivicRepositoryModule_ProvideResidentRepositoryFactory(Provider<AppDatabase> dbProvider,
      Provider<CivicPublisher> publisherProvider) {
    this.dbProvider = dbProvider;
    this.publisherProvider = publisherProvider;
  }

  @Override
  public ResidentRepository get() {
    return provideResidentRepository(dbProvider.get(), publisherProvider.get());
  }

  public static CivicRepositoryModule_ProvideResidentRepositoryFactory create(
      Provider<AppDatabase> dbProvider, Provider<CivicPublisher> publisherProvider) {
    return new CivicRepositoryModule_ProvideResidentRepositoryFactory(dbProvider, publisherProvider);
  }

  public static ResidentRepository provideResidentRepository(AppDatabase db,
      CivicPublisher publisher) {
    return Preconditions.checkNotNullFromProvides(CivicRepositoryModule.INSTANCE.provideResidentRepository(db, publisher));
  }
}
