package com.voltek.yandex.mobilization.interactor.language;

import com.voltek.yandex.mobilization.entity.presentation.TranslationDirection;

import java.util.List;

public interface LanguageUseCase {

    List<String> getLangsNames();

    TranslationDirection getDirectionNames(String direction);
}
