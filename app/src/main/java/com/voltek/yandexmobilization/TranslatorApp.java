package com.voltek.yandexmobilization;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import io.realm.Realm;
import timber.log.Timber;

public class TranslatorApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Hawk.init(this).build();
        Timber.plant(new Timber.DebugTree());
    }
}
