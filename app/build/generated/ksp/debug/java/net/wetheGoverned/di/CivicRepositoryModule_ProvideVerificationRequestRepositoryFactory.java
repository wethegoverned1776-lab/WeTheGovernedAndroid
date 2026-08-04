package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.repository.VerificationRequestRepository;

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
public final class CivicRepositoryModule_ProvideVerificationRequestRepositoryFactory implements Factory<VerificationRequestRepository> {
  private final Provider<AppDatabase> dbProvider;

  public CivicRepositoryModule_ProvideVerificationRequestRepositoryFactory(
      Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public VerificationRequestRepository get() {
    return provideVerificationRequestRepository(dbProvider.get());
  }

  public static CivicRepositoryModule_ProvideVerificationRequestRepositoryFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicRepositoryModule_ProvideVerificationRequestRepositoryFactory(dbProvider);
  }

  public static VerificationRequestRepository provideVerificationRequestRepository(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicRepositoryModule.INSTANCE.provideVerificationRequestRepository(db));
  }
}
