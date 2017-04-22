package com.voltek.yandex.mobilization.di.component;

import com.voltek.yandex.mobilization.di.module.RepositoryModule;
import com.voltek.yandex.mobilization.interactor.language.LanguageInteractor;
import com.voltek.yandex.mobilization.interactor.translation.TranslationInteractor;
import com.voltek.yandex.mobilization.interactor.user_data.UserDataInteractor;

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

    void inject(TranslationInteractor interactor);
}
