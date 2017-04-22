package com.voltek.yandex.mobilization.interactor.language;

import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.data.entity.Language;

import java.util.ArrayList;
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
        return extractLangsNames(mLangsRepo.get());
    }

    // Convert language list to list of language names strings
    private List<String> extractLangsNames(List<Language> langs) {
        List<String> langsNames = new ArrayList<>();

        for (Language language : langs) {
            langsNames.add(language.getName());
        }

        return langsNames;
    }
}
