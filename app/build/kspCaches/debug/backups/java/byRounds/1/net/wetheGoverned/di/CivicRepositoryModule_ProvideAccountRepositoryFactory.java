package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.repository.AccountRepository;

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
public final class CivicRepositoryModule_ProvideAccountRepositoryFactory implements Factory<AccountRepository> {
  private final Provider<AppDatabase> dbProvider;

  public CivicRepositoryModule_ProvideAccountRepositoryFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public AccountRepository get() {
    return provideAccountRepository(dbProvider.get());
  }

  public static CivicRepositoryModule_ProvideAccountRepositoryFactory create(
      Provider<AppDatabase> dbProvider) {
    return new CivicRepositoryModule_ProvideAccountRepositoryFactory(dbProvider);
  }

  public static AccountRepository provideAccountRepository(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(CivicRepositoryModule.INSTANCE.provideAccountRepository(db));
  }
}
