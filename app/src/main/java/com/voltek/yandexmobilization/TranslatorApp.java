package com.voltek.yandexmobilization;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.voltek.yandexmobilization.navigation.RouterHolder;
import com.voltek.yandexmobilization.navigation.proxy.RouterBinder;
import com.voltek.yandexmobilization.navigation.proxy.RouterBus;

import io.realm.Realm;
import timber.log.Timber;

public class TranslatorApp extends Application {

    public static final String BASE_URL = "https://translate.yandex.net/api/";

    private static RouterHolder sRouterHolder;

    @Override
    public void onCreate() {
        super.onCreate();

        sRouterHolder = new RouterHolder();

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
}
