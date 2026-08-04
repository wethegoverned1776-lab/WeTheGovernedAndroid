package net.wetheGoverned;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.ktor.client.HttpClient;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import net.wetheGoverned.core.CivicPublisher;
import net.wetheGoverned.core.DispatcherProvider;
import net.wetheGoverned.data.NostrRelayManager;
import net.wetheGoverned.data.P2PService;
import net.wetheGoverned.data.P2PService_MembersInjector;
import net.wetheGoverned.data.P2PSyncEngine;
import net.wetheGoverned.data.local.AppDatabase;
import net.wetheGoverned.data.local.dao.PendingEventDao;
import net.wetheGoverned.di.CivicBindingModule_Companion_ProvideDispatcherProviderFactory;
import net.wetheGoverned.di.CivicDatabaseModule_ProvideDatabaseFactory;
import net.wetheGoverned.di.CivicDatabaseModule_ProvidePendingDaoFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideCivicApiFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideHttpClientFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideLocationHelperFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideNostrRelayManagerFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideP2PSyncEngineFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideRelayUrlsFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideSessionManagerFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideWsCivicPublisherFactory;
import net.wetheGoverned.di.CivicNetworkModule_ProvideWtgBackendApiFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideAccountRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideCommunityRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideDistrictRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideManifestoRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvidePollRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideResidentRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideScorecardRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideVerificationRequestRepositoryFactory;
import net.wetheGoverned.di.CivicRepositoryModule_ProvideVoteRepositoryFactory;
import net.wetheGoverned.remote.api.CivicApi;
import net.wetheGoverned.remote.api.WtgBackendApi;
import net.wetheGoverned.repository.AccountRepository;
import net.wetheGoverned.repository.CommunityRepository;
import net.wetheGoverned.repository.DistrictRepository;
import net.wetheGoverned.repository.ManifestoRepository;
import net.wetheGoverned.repository.PollRepository;
import net.wetheGoverned.repository.ResidentRepository;
import net.wetheGoverned.repository.ScorecardRepository;
import net.wetheGoverned.repository.VerificationRequestRepository;
import net.wetheGoverned.repository.VoteRepository;
import net.wetheGoverned.session.AndroidPendingEventQueue;
import net.wetheGoverned.session.CredentialsManager;
import net.wetheGoverned.session.SessionManager;
import net.wetheGoverned.zk.NativeZkProver;

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
public final class DaggerWeTheGovernedApplication_HiltComponents_SingletonC {
  private DaggerWeTheGovernedApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public WeTheGovernedApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements WeTheGovernedApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public WeTheGovernedApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements WeTheGovernedApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public WeTheGovernedApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements WeTheGovernedApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public WeTheGovernedApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements WeTheGovernedApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public WeTheGovernedApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements WeTheGovernedApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public WeTheGovernedApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements WeTheGovernedApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public WeTheGovernedApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements WeTheGovernedApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public WeTheGovernedApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends WeTheGovernedApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends WeTheGovernedApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends WeTheGovernedApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends WeTheGovernedApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(Collections.<Class<?>, Boolean>emptyMap(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return Collections.<Class<?>, Boolean>emptyMap();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }
  }

  private static final class ViewModelCImpl extends WeTheGovernedApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public Map<Class<?>, Provider<ViewModel>> getHiltViewModelMap() {
      return Collections.<Class<?>, Provider<ViewModel>>emptyMap();
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }
  }

  private static final class ActivityRetainedCImpl extends WeTheGovernedApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private dagger.internal.Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements dagger.internal.Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends WeTheGovernedApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectP2PService(P2PService arg0) {
      injectP2PService2(arg0);
    }

    @CanIgnoreReturnValue
    private P2PService injectP2PService2(P2PService instance) {
      P2PService_MembersInjector.injectP2pSyncEngine(instance, singletonCImpl.provideP2PSyncEngineProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends WeTheGovernedApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private dagger.internal.Provider<AppDatabase> provideDatabaseProvider;

    private dagger.internal.Provider<List<String>> provideRelayUrlsProvider;

    private dagger.internal.Provider<NostrRelayManager> provideNostrRelayManagerProvider;

    private dagger.internal.Provider<CredentialsManager> credentialsManagerProvider;

    private dagger.internal.Provider<SessionManager> provideSessionManagerProvider;

    private dagger.internal.Provider<PendingEventDao> providePendingDaoProvider;

    private dagger.internal.Provider<AndroidPendingEventQueue> androidPendingEventQueueProvider;

    private dagger.internal.Provider<DispatcherProvider> provideDispatcherProvider;

    private dagger.internal.Provider<NativeZkProver> nativeZkProverProvider;

    private dagger.internal.Provider<CivicPublisher> provideWsCivicPublisherProvider;

    private dagger.internal.Provider<PollRepository> providePollRepositoryProvider;

    private dagger.internal.Provider<ResidentRepository> provideResidentRepositoryProvider;

    private dagger.internal.Provider<VoteRepository> provideVoteRepositoryProvider;

    private dagger.internal.Provider<ManifestoRepository> provideManifestoRepositoryProvider;

    private dagger.internal.Provider<CommunityRepository> provideCommunityRepositoryProvider;

    private dagger.internal.Provider<AccountRepository> provideAccountRepositoryProvider;

    private dagger.internal.Provider<P2PSyncEngine> provideP2PSyncEngineProvider;

    private dagger.internal.Provider<ScorecardRepository> provideScorecardRepositoryProvider;

    private dagger.internal.Provider<DistrictRepository> provideDistrictRepositoryProvider;

    private dagger.internal.Provider<VerificationRequestRepository> provideVerificationRequestRepositoryProvider;

    private dagger.internal.Provider<CivicApi> provideCivicApiProvider;

    private dagger.internal.Provider<HttpClient> provideHttpClientProvider;

    private dagger.internal.Provider<WtgBackendApi> provideWtgBackendApiProvider;

    private dagger.internal.Provider<LocationHelper> provideLocationHelperProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.provideRelayUrlsProvider = DoubleCheck.provider(new SwitchingProvider<List<String>>(singletonCImpl, 5));
      this.provideNostrRelayManagerProvider = DoubleCheck.provider(new SwitchingProvider<NostrRelayManager>(singletonCImpl, 4));
      this.credentialsManagerProvider = DoubleCheck.provider(new SwitchingProvider<CredentialsManager>(singletonCImpl, 7));
      this.provideSessionManagerProvider = DoubleCheck.provider(new SwitchingProvider<SessionManager>(singletonCImpl, 6));
      this.providePendingDaoProvider = DoubleCheck.provider(new SwitchingProvider<PendingEventDao>(singletonCImpl, 9));
      this.androidPendingEventQueueProvider = DoubleCheck.provider(new SwitchingProvider<AndroidPendingEventQueue>(singletonCImpl, 8));
      this.provideDispatcherProvider = DoubleCheck.provider(new SwitchingProvider<DispatcherProvider>(singletonCImpl, 11));
      this.nativeZkProverProvider = DoubleCheck.provider(new SwitchingProvider<NativeZkProver>(singletonCImpl, 10));
      this.provideWsCivicPublisherProvider = DoubleCheck.provider(new SwitchingProvider<CivicPublisher>(singletonCImpl, 3));
      this.providePollRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<PollRepository>(singletonCImpl, 1));
      this.provideResidentRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ResidentRepository>(singletonCImpl, 12));
      this.provideVoteRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<VoteRepository>(singletonCImpl, 13));
      this.provideManifestoRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ManifestoRepository>(singletonCImpl, 14));
      this.provideCommunityRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CommunityRepository>(singletonCImpl, 15));
      this.provideAccountRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AccountRepository>(singletonCImpl, 16));
      this.provideP2PSyncEngineProvider = DoubleCheck.provider(new SwitchingProvider<P2PSyncEngine>(singletonCImpl, 0));
      this.provideScorecardRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ScorecardRepository>(singletonCImpl, 17));
      this.provideDistrictRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<DistrictRepository>(singletonCImpl, 18));
      this.provideVerificationRequestRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<VerificationRequestRepository>(singletonCImpl, 19));
      this.provideCivicApiProvider = DoubleCheck.provider(new SwitchingProvider<CivicApi>(singletonCImpl, 20));
      this.provideHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<HttpClient>(singletonCImpl, 22));
      this.provideWtgBackendApiProvider = DoubleCheck.provider(new SwitchingProvider<WtgBackendApi>(singletonCImpl, 21));
      this.provideLocationHelperProvider = DoubleCheck.provider(new SwitchingProvider<LocationHelper>(singletonCImpl, 23));
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @Override
    public void injectWeTheGovernedApplication(WeTheGovernedApplication arg0) {
      injectWeTheGovernedApplication2(arg0);
    }

    @Override
    public PollRepository pollRepository() {
      return providePollRepositoryProvider.get();
    }

    @Override
    public AccountRepository accountRepository() {
      return provideAccountRepositoryProvider.get();
    }

    @Override
    public ResidentRepository residentRepository() {
      return provideResidentRepositoryProvider.get();
    }

    @Override
    public ManifestoRepository manifestoRepository() {
      return provideManifestoRepositoryProvider.get();
    }

    @Override
    public ScorecardRepository scorecardRepository() {
      return provideScorecardRepositoryProvider.get();
    }

    @Override
    public DistrictRepository districtRepository() {
      return provideDistrictRepositoryProvider.get();
    }

    @Override
    public CommunityRepository communityRepository() {
      return provideCommunityRepositoryProvider.get();
    }

    @Override
    public VerificationRequestRepository requestRepository() {
      return provideVerificationRequestRepositoryProvider.get();
    }

    @Override
    public SessionManager sessionManager() {
      return provideSessionManagerProvider.get();
    }

    @Override
    public CivicApi civicApi() {
      return provideCivicApiProvider.get();
    }

    @Override
    public WtgBackendApi wtgBackendApi() {
      return provideWtgBackendApiProvider.get();
    }

    @Override
    public LocationHelper locationHelper() {
      return provideLocationHelperProvider.get();
    }

    @Override
    public NostrRelayManager relayManager() {
      return provideNostrRelayManagerProvider.get();
    }

    @Override
    public P2PSyncEngine syncEngine() {
      return provideP2PSyncEngineProvider.get();
    }

    @CanIgnoreReturnValue
    private WeTheGovernedApplication injectWeTheGovernedApplication2(
        WeTheGovernedApplication instance) {
      WeTheGovernedApplication_MembersInjector.injectP2pSyncEngine(instance, provideP2PSyncEngineProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements dagger.internal.Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // net.wetheGoverned.data.P2PSyncEngine 
          return (T) CivicNetworkModule_ProvideP2PSyncEngineFactory.provideP2PSyncEngine(singletonCImpl.providePollRepositoryProvider.get(), singletonCImpl.provideResidentRepositoryProvider.get(), singletonCImpl.provideVoteRepositoryProvider.get(), singletonCImpl.provideManifestoRepositoryProvider.get(), singletonCImpl.provideCommunityRepositoryProvider.get(), singletonCImpl.provideAccountRepositoryProvider.get(), singletonCImpl.provideSessionManagerProvider.get(), singletonCImpl.provideNostrRelayManagerProvider.get(), singletonCImpl.provideWsCivicPublisherProvider.get());

          case 1: // net.wetheGoverned.repository.PollRepository 
          return (T) CivicRepositoryModule_ProvidePollRepositoryFactory.providePollRepository(singletonCImpl.provideDatabaseProvider.get(), singletonCImpl.provideWsCivicPublisherProvider.get());

          case 2: // net.wetheGoverned.data.local.AppDatabase 
          return (T) CivicDatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // net.wetheGoverned.core.CivicPublisher 
          return (T) CivicNetworkModule_ProvideWsCivicPublisherFactory.provideWsCivicPublisher(singletonCImpl.provideNostrRelayManagerProvider.get(), singletonCImpl.provideSessionManagerProvider.get(), singletonCImpl.androidPendingEventQueueProvider.get(), singletonCImpl.nativeZkProverProvider.get());

          case 4: // net.wetheGoverned.data.NostrRelayManager 
          return (T) CivicNetworkModule_ProvideNostrRelayManagerFactory.provideNostrRelayManager(singletonCImpl.provideRelayUrlsProvider.get());

          case 5: // java.util.List<java.lang.String> 
          return (T) CivicNetworkModule_ProvideRelayUrlsFactory.provideRelayUrls();

          case 6: // net.wetheGoverned.session.SessionManager 
          return (T) CivicNetworkModule_ProvideSessionManagerFactory.provideSessionManager(singletonCImpl.credentialsManagerProvider.get());

          case 7: // net.wetheGoverned.session.CredentialsManager 
          return (T) new CredentialsManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 8: // net.wetheGoverned.session.AndroidPendingEventQueue 
          return (T) new AndroidPendingEventQueue(singletonCImpl.providePendingDaoProvider.get());

          case 9: // net.wetheGoverned.data.local.dao.PendingEventDao 
          return (T) CivicDatabaseModule_ProvidePendingDaoFactory.providePendingDao(singletonCImpl.provideDatabaseProvider.get());

          case 10: // net.wetheGoverned.zk.NativeZkProver 
          return (T) new NativeZkProver(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideDispatcherProvider.get());

          case 11: // net.wetheGoverned.core.DispatcherProvider 
          return (T) CivicBindingModule_Companion_ProvideDispatcherProviderFactory.provideDispatcherProvider();

          case 12: // net.wetheGoverned.repository.ResidentRepository 
          return (T) CivicRepositoryModule_ProvideResidentRepositoryFactory.provideResidentRepository(singletonCImpl.provideDatabaseProvider.get(), singletonCImpl.provideWsCivicPublisherProvider.get());

          case 13: // net.wetheGoverned.repository.VoteRepository 
          return (T) CivicRepositoryModule_ProvideVoteRepositoryFactory.provideVoteRepository(singletonCImpl.provideDatabaseProvider.get());

          case 14: // net.wetheGoverned.repository.ManifestoRepository 
          return (T) CivicRepositoryModule_ProvideManifestoRepositoryFactory.provideManifestoRepository(singletonCImpl.provideDatabaseProvider.get());

          case 15: // net.wetheGoverned.repository.CommunityRepository 
          return (T) CivicRepositoryModule_ProvideCommunityRepositoryFactory.provideCommunityRepository(singletonCImpl.provideDatabaseProvider.get(), singletonCImpl.provideWsCivicPublisherProvider.get());

          case 16: // net.wetheGoverned.repository.AccountRepository 
          return (T) CivicRepositoryModule_ProvideAccountRepositoryFactory.provideAccountRepository(singletonCImpl.provideDatabaseProvider.get());

          case 17: // net.wetheGoverned.repository.ScorecardRepository 
          return (T) CivicRepositoryModule_ProvideScorecardRepositoryFactory.provideScorecardRepository(singletonCImpl.provideDatabaseProvider.get());

          case 18: // net.wetheGoverned.repository.DistrictRepository 
          return (T) CivicRepositoryModule_ProvideDistrictRepositoryFactory.provideDistrictRepository(singletonCImpl.provideDatabaseProvider.get());

          case 19: // net.wetheGoverned.repository.VerificationRequestRepository 
          return (T) CivicRepositoryModule_ProvideVerificationRequestRepositoryFactory.provideVerificationRequestRepository(singletonCImpl.provideDatabaseProvider.get());

          case 20: // net.wetheGoverned.remote.api.CivicApi 
          return (T) CivicNetworkModule_ProvideCivicApiFactory.provideCivicApi();

          case 21: // net.wetheGoverned.remote.api.WtgBackendApi 
          return (T) CivicNetworkModule_ProvideWtgBackendApiFactory.provideWtgBackendApi(singletonCImpl.provideHttpClientProvider.get());

          case 22: // io.ktor.client.HttpClient 
          return (T) CivicNetworkModule_ProvideHttpClientFactory.provideHttpClient();

          case 23: // net.wetheGoverned.LocationHelper 
          return (T) CivicNetworkModule_ProvideLocationHelperFactory.provideLocationHelper();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
