package com.voltek.yandexmobilization.di.module;

import com.voltek.yandexmobilization.data.DataProvider;
import com.voltek.yandexmobilization.data.repository.LanguagesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    DataProvider.languages provideLanguagesRepository() {
        return new LanguagesRepository();
    }
}
