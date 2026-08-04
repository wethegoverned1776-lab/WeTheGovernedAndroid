package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.data.local.dao.ManifestoDao;

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
public final class CivicDatabaseModule_ProvideManifestoDaoFactory implements Factory<ManifestoDao> {
  private final Provider<AppDatabase> dbProvider;

  public CivicDatabaseModule_ProvideManifestoDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ManifestoDao get() {
    return provideManifestoDao(dbProvider.get());
  }

  public static CivicDatabaseModule_ProvideManifestoDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicDatabaseModule_ProvideManifestoDaoFactory(dbProvider);
  }

  public static ManifestoDao provideManifestoDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicDatabaseModule.INSTANCE.provideManifestoDao(db));
  }
}
