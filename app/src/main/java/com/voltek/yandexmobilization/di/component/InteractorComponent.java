package com.voltek.yandexmobilization.di.component;

import com.voltek.yandexmobilization.di.module.RepositoryModule;
import com.voltek.yandexmobilization.interactor.language.LanguageInteractor;
import com.voltek.yandexmobilization.interactor.user_data.UserDataInteractor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                RepositoryModule.class
        }
)
public interface InteractorComponent {

    void inject(UserDataInteractor interactor);

    void inject(LanguageInteractor interactor);
}
