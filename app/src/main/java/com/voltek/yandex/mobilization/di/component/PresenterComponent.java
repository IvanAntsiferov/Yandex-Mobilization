package com.voltek.yandex.mobilization.di.component;

import com.voltek.yandex.mobilization.di.module.AppModule;
import com.voltek.yandex.mobilization.di.module.UseCaseModule;
import com.voltek.yandex.mobilization.ui.history.HistoryPresenter;
import com.voltek.yandex.mobilization.ui.translator.TranslatorPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                UseCaseModule.class
        }
)
public interface PresenterComponent {

    void inject(TranslatorPresenter presenter);

    void inject(HistoryPresenter presenter);
}
