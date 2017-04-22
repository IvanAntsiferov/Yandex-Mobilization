package com.voltek.yandex.mobilization.interactor.user_data;

import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.data.entity.SelectedLanguages;

import javax.inject.Inject;

import timber.log.Timber;

public class UserDataInteractor implements com.voltek.yandex.mobilization.interactor.user_data.UserDataUseCase {

    @Inject
    DataProvider.UserData mUserDataRepo;

    public UserDataInteractor()  {
        TranslatorApp.getInteractorComponent().inject(this);
    }

    @Override
    public SelectedLanguages getSelectedLangs() {
        // Default index's: translateApiRequest from 60 (russian), to 3 (english)
        SelectedLanguages defaultValue = new SelectedLanguages(60, 3);
        SelectedLanguages value = (SelectedLanguages) mUserDataRepo.getValue(com.voltek.yandex.mobilization.interactor.user_data.UserDataKey.SELECTED_LANGUAGES, defaultValue);
        Timber.d("getSelectedLangs: from " + value.from() + " to " + value.to());
        return value;
    }

    @Override
    public void updateSelectedLangs(SelectedLanguages selectedLanguages) {
        mUserDataRepo.putValue(com.voltek.yandex.mobilization.interactor.user_data.UserDataKey.SELECTED_LANGUAGES, selectedLanguages);
    }
}
