package net.wetheGoverned.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.core.CivicPublisher;
import net.wetheGoverned.data.MeshDiscoveryManager;
import net.wetheGoverned.data.NostrRelayManager;
import net.wetheGoverned.session.SessionManager;

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
public final class CivicNetworkModule_ProvideMeshDiscoveryManagerFactory implements Factory<MeshDiscoveryManager> {
  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<CivicPublisher> publisherProvider;

  private final Provider<NostrRelayManager> relayManagerProvider;

  public CivicNetworkModule_ProvideMeshDiscoveryManagerFactory(
      Provider<SessionManager> sessionManagerProvider, Provider<CivicPublisher> publisherProvider,
      Provider<NostrRelayManager> relayManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
    this.publisherProvider = publisherProvider;
    this.relayManagerProvider = relayManagerProvider;
  }

  @Override
  public MeshDiscoveryManager get() {
    return provideMeshDiscoveryManager(sessionManagerProvider.get(), publisherProvider.get(), relayManagerProvider.get());
  }

  public static CivicNetworkModule_ProvideMeshDiscoveryManagerFactory create(
      Provider<SessionManager> sessionManagerProvider, Provider<CivicPublisher> publisherProvider,
      Provider<NostrRelayManager> relayManagerProvider) {
    return new CivicNetworkModule_ProvideMeshDiscoveryManagerFactory(sessionManagerProvider, publisherProvider, relayManagerProvider);
  }

  public static MeshDiscoveryManager provideMeshDiscoveryManager(SessionManager sessionManager,
      CivicPublisher publisher, NostrRelayManager relayManager) {
    return Preconditions.checkNotNullFromProvides(CivicNetworkModule.INSTANCE.provideMeshDiscoveryManager(sessionManager, publisher, relayManager));
  }
}
