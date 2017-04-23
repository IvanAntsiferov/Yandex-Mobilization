package com.voltek.yandex.mobilization.di.component;

import com.voltek.yandex.mobilization.data.repository.TranslationRepository;
import com.voltek.yandex.mobilization.di.module.AppModule;
import com.voltek.yandex.mobilization.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)
public interface RepositoryComponent {

    void inject(TranslationRepository repository);
}
