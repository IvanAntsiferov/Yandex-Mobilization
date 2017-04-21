package com.voltek.yandexmobilization.di.module;

import com.voltek.yandexmobilization.data.DataProvider;
import com.voltek.yandexmobilization.data.repository.LanguagesRepository;
import com.voltek.yandexmobilization.data.repository.TranslationRepository;
import com.voltek.yandexmobilization.data.repository.UserDataRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    DataProvider.UserData provideUserDataRepository() {
        return new UserDataRepository();
    }

    @Provides
    DataProvider.Languages provideLanguagesRepository() {
        return new LanguagesRepository();
    }

    @Provides
    DataProvider.Translations provideTranslationsRepository() {
        return new TranslationRepository();
    }
}
