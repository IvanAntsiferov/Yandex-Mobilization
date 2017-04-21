package com.voltek.yandexmobilization.di.module;

import com.voltek.yandexmobilization.interactor.language.LanguageInteractor;
import com.voltek.yandexmobilization.interactor.language.LanguageUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

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
}
