package com.voltek.yandexmobilization.di.module;

import com.voltek.yandexmobilization.interactor.language.LanguageInteractor;
import com.voltek.yandexmobilization.interactor.language.LanguageUseCase;
import com.voltek.yandexmobilization.interactor.translation.TranslationInteractor;
import com.voltek.yandexmobilization.interactor.translation.TranslationUseCase;
import com.voltek.yandexmobilization.interactor.user_data.UserDataInteractor;
import com.voltek.yandexmobilization.interactor.user_data.UserDataUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    UserDataInteractor provideUserDataInteractor() {
        return new UserDataInteractor();
    }

    @Provides
    @Singleton
    UserDataUseCase provideUserDataUseCase(UserDataInteractor userDataInteractor) {
        return userDataInteractor;
    }

    @Provides
    @Singleton
    LanguageInteractor provideLanguageInteractor() {
        return new LanguageInteractor();
    }

    @Provides
    @Singleton
    LanguageUseCase provideLanguageUseCase(LanguageInteractor languageInteractor) {
        return languageInteractor;
    }

    @Provides
    @Singleton
    TranslationInteractor provideTranslationInteractor() {
        return new TranslationInteractor();
    }

    @Provides
    @Singleton
    TranslationUseCase provideTranslationUseCase(TranslationInteractor translationInteractor) {
        return translationInteractor;
    }
}
