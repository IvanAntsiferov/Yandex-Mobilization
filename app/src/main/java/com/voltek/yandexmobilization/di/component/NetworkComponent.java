package com.voltek.yandexmobilization.di.component;

import com.voltek.yandexmobilization.di.module.AppModule;
import com.voltek.yandexmobilization.di.module.NetworkModule;
import com.voltek.yandexmobilization.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)
public interface NetworkComponent {

    void inject(MainActivity activity);
}
