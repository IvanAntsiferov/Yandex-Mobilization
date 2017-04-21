package com.voltek.yandexmobilization.di.component;

import com.voltek.yandexmobilization.di.module.UseCaseModule;
import com.voltek.yandexmobilization.ui.translator.TranslatorPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                UseCaseModule.class
        }
)
public interface PresenterComponent {

    void inject(TranslatorPresenter presenter);
}
