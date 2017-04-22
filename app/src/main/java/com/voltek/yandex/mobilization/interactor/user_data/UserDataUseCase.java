package com.voltek.yandex.mobilization.interactor.user_data;

import com.voltek.yandex.mobilization.entity.presentation.SelectedLanguages;

public interface UserDataUseCase {

    SelectedLanguages getSelectedLangs();

    void updateSelectedLangs(SelectedLanguages selectedLanguages);
}
