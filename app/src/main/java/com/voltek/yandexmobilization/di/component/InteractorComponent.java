package com.voltek.yandexmobilization.di.component;

import com.voltek.yandexmobilization.di.module.AppModule;
import com.voltek.yandexmobilization.di.module.RepositoryModule;
import com.voltek.yandexmobilization.interactor.language.LanguageInteractor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                RepositoryModule.class
        }
)
public interface InteractorComponent {

    void inject(LanguageInteractor interactor);
}
