package com.voltek.yandex.mobilization.interactor.language;

import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.entity.Mapper;

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
}
