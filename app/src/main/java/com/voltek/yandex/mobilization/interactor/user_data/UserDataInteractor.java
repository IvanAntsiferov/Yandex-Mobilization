package com.voltek.yandex.mobilization.interactor.user_data;

import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.entity.presentation.SelectedLanguages;

import javax.inject.Inject;

public class UserDataInteractor implements com.voltek.yandex.mobilization.interactor.user_data.UserDataUseCase {

    @Inject
    DataProvider.UserData mUserDataRepo;

    public UserDataInteractor() {
        TranslatorApp.getInteractorComponent().inject(this);
    }

    @Override
    public SelectedLanguages getSelectedLangs() {
        // Default languages index's: from 60 (russian), to 3 (english)
        SelectedLanguages defaultValue = new SelectedLanguages(60, 3);
        return (SelectedLanguages) mUserDataRepo
                .getValue(UserDataKey.SELECTED_LANGUAGES, defaultValue);
    }

    @Override
    public void updateSelectedLangs(SelectedLanguages selectedLanguages) {
        mUserDataRepo.putValue(UserDataKey.SELECTED_LANGUAGES, selectedLanguages);
    }
}
