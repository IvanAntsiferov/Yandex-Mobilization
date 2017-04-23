package com.voltek.yandex.mobilization.interactor.language;

import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.entity.Mapper;
import com.voltek.yandex.mobilization.entity.presentation.TranslationDirection;

import java.util.List;

import javax.inject.Inject;

public class LanguageInteractor implements com.voltek.yandex.mobilization.interactor.language.LanguageUseCase {

    @Inject
    DataProvider.Languages mLangsRepo;

    public LanguageInteractor() {
        TranslatorApp.getInteractorComponent().inject(this);
    }

    @Override
    public List<String> getLangsNames() {
        return Mapper.extractLangsNames(mLangsRepo.get());
    }

    @Override
    public TranslationDirection getDirectionNames(String direction) {
        String fromLang = mLangsRepo.getLangNameByCode(
                direction.substring(0, direction.indexOf('-')));
        String toLang = mLangsRepo.getLangNameByCode(
                direction.substring(direction.indexOf('-') + 1));
        if (fromLang == null) fromLang = "";
        if (toLang == null) fromLang = "";
        return new TranslationDirection(fromLang, toLang);
    }
}
