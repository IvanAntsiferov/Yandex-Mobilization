package com.voltek.yandex.mobilization.di.module;

import android.app.Application;
import android.content.Context;

import com.voltek.yandex.mobilization.TranslatorApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private TranslatorApp mApplication;

    public AppModule(TranslatorApp application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() { return mApplication; }

    @Provides
    @Singleton
    TranslatorApp provideProductHuntClientApp() {
        return mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(TranslatorApp application) {
        return application;
    }
}
