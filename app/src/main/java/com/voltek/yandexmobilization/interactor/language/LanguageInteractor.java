package com.voltek.yandexmobilization.interactor.language;

import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.DataProvider;
import com.voltek.yandexmobilization.data.entity.Language;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LanguageInteractor implements LanguageUseCase {

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
