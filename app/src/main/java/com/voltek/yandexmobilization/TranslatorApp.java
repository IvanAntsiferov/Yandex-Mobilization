package com.voltek.yandexmobilization;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.voltek.yandexmobilization.di.component.*;
import com.voltek.yandexmobilization.di.module.*;
import com.voltek.yandexmobilization.navigation.RouterHolder;
import com.voltek.yandexmobilization.navigation.proxy.RouterBinder;
import com.voltek.yandexmobilization.navigation.proxy.RouterBus;

import io.realm.Realm;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TranslatorApp extends Application {

    public static final String BASE_URL = "https://translate.yandex.net/";
    public static final String BASE_FONT = "fonts/Roboto-Regular.ttf";

    private static RouterHolder sRouterHolder;

    private static PresenterComponent sPresenterComponent;
    private static InteractorComponent sInteractorComponent;
    private static NetworkComponent sNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // DI components/modules and app singletones init
        sRouterHolder = new RouterHolder();

        AppModule appModule = new AppModule(this);
        UseCaseModule useCaseModule = new UseCaseModule();
        RepositoryModule repositoryModule = new RepositoryModule();
        NetworkModule networkModule = new NetworkModule(BASE_URL);

        sPresenterComponent = DaggerPresenterComponent.builder()
                .appModule(appModule)
                .useCaseModule(useCaseModule)
                .build();

        sInteractorComponent = DaggerInteractorComponent.builder()
                .repositoryModule(repositoryModule)
                .build();

        sNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(appModule)
                .networkModule(networkModule)
                .build();

        // Libraries init
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(BASE_FONT)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Realm.init(this);
        Hawk.init(this).build();
        Timber.plant(new Timber.DebugTree());
    }

    public static RouterBinder getRouterBinder() {
        return sRouterHolder;
    }

    public static RouterBus getRouterBus() {
        return sRouterHolder;
    }

    public static PresenterComponent getPresenterComponent() {
        return sPresenterComponent;
    }

    public static InteractorComponent getInteractorComponent() {
        return sInteractorComponent;
    }

    public static NetworkComponent getNetworkComponent() {
        return sNetworkComponent;
    }
}
