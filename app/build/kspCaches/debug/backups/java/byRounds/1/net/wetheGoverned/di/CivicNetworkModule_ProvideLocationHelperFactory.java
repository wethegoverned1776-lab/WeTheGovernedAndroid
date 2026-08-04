package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import net.wetheGoverned.LocationHelper;

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
public final class CivicNetworkModule_ProvideLocationHelperFactory implements Factory<LocationHelper> {
  @Override
  public LocationHelper get() {
    return provideLocationHelper();
  }

  public static CivicNetworkModule_ProvideLocationHelperFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LocationHelper provideLocationHelper() {
    return Preconditions.checkNotNullFromProvides(CivicNetworkModule.INSTANCE.provideLocationHelper());
  }

  private static final class InstanceHolder {
    private static final CivicNetworkModule_ProvideLocationHelperFactory INSTANCE = new CivicNetworkModule_ProvideLocationHelperFactory();
  }
}
