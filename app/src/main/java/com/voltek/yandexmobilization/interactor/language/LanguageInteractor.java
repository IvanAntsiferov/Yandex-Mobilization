package com.voltek.yandexmobilization.interactor.language;

import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.DataProvider;

import java.util.List;

import javax.inject.Inject;

public class LanguageInteractor implements LanguageUseCase {

    @Inject
    DataProvider.languages mLangsRepo;

    public LanguageInteractor() {
        TranslatorApp.getInteractorComponent().inject(this);
    }

    @Override
    public List<String> getLangsNames() {
        return null;
    }
}
