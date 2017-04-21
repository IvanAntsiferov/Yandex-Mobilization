package com.voltek.yandexmobilization.interactor.user_data;

import com.voltek.yandexmobilization.data.entity.SelectedLanguages;

public interface UserDataUseCase {

    SelectedLanguages getSelectedLangs();

    void updateSelectedLangs(SelectedLanguages selectedLanguages);
}
