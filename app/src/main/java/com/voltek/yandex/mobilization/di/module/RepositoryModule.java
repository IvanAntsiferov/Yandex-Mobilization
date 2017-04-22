package com.voltek.yandex.mobilization.di.module;

import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.data.repository.LanguagesRepository;
import com.voltek.yandex.mobilization.data.repository.TranslationRepository;
import com.voltek.yandex.mobilization.data.repository.UserDataRepository;

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
