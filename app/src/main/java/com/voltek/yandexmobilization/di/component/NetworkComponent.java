package com.voltek.yandexmobilization.di.component;

import com.voltek.yandexmobilization.data.repository.TranslationRepository;
import com.voltek.yandexmobilization.di.module.AppModule;
import com.voltek.yandexmobilization.di.module.NetworkModule;

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

    void inject(TranslationRepository repository);
}
