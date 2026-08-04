package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import net.wetheGoverned.core.DispatcherProvider;

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
public final class CivicBindingModule_Companion_ProvideDispatcherProviderFactory implements Factory<DispatcherProvider> {
  @Override
  public DispatcherProvider get() {
    return provideDispatcherProvider();
  }

  public static CivicBindingModule_Companion_ProvideDispatcherProviderFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DispatcherProvider provideDispatcherProvider() {
    return Preconditions.checkNotNullFromProvides(CivicBindingModule.Companion.provideDispatcherProvider());
  }

  private static final class InstanceHolder {
    private static final CivicBindingModule_Companion_ProvideDispatcherProviderFactory INSTANCE = new CivicBindingModule_Companion_ProvideDispatcherProviderFactory();
  }
}
