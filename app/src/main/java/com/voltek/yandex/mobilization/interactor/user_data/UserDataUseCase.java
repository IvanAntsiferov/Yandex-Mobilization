package com.voltek.yandex.mobilization.interactor.user_data;

import com.voltek.yandex.mobilization.entity.presentation.SelectedLanguages;

public interface UserDataUseCase {

    /**
     * @return pair of ids of lastly selected languages
     */
    SelectedLanguages getSelectedLangs();

    /**
     * Update selected languages as user changes them in
     * {@link com.voltek.yandex.mobilization.ui.translator.TranslatorFragment}
     */
    void updateSelectedLangs(SelectedLanguages selectedLanguages);
}
