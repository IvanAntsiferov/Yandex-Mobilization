package com.voltek.yandex.mobilization.interactor.user_data;

import com.voltek.yandex.mobilization.data.entity.SelectedLanguages;

public interface UserDataUseCase {

    SelectedLanguages getSelectedLangs();

    void updateSelectedLangs(SelectedLanguages selectedLanguages);
}
