package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.repository.ManifestoRepository;

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
public final class CivicRepositoryModule_ProvideManifestoRepositoryFactory implements Factory<ManifestoRepository> {
  private final Provider<AppDatabase> dbProvider;

  public CivicRepositoryModule_ProvideManifestoRepositoryFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ManifestoRepository get() {
    return provideManifestoRepository(dbProvider.get());
  }

  public static CivicRepositoryModule_ProvideManifestoRepositoryFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicRepositoryModule_ProvideManifestoRepositoryFactory(dbProvider);
  }

  public static ManifestoRepository provideManifestoRepository(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicRepositoryModule.INSTANCE.provideManifestoRepository(db));
  }
}
