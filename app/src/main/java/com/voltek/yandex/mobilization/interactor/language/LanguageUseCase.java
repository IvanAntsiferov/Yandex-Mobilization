package com.voltek.yandex.mobilization.interactor.language;

import com.voltek.yandex.mobilization.entity.presentation.TranslationDirection;

import java.util.List;

public interface LanguageUseCase {

    /**
     * @return list of all supported languages names
     */
    List<String> getLangsNames();

    /**
     * @return pair of language names (from - to)
     */
    TranslationDirection getDirectionNames(String direction);
}
