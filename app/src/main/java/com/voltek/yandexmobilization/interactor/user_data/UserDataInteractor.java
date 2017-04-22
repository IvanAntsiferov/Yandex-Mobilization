package com.voltek.yandexmobilization.interactor.user_data;

import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.DataProvider;
import com.voltek.yandexmobilization.data.entity.SelectedLanguages;

import javax.inject.Inject;

import timber.log.Timber;

public class UserDataInteractor implements UserDataUseCase {

    @Inject
    DataProvider.UserData mUserDataRepo;

    public UserDataInteractor()  {
        TranslatorApp.getInteractorComponent().inject(this);
    }

    @Override
    public SelectedLanguages getSelectedLangs() {
        // Default index's: translateApiRequest from 60 (russian), to 3 (english)
        SelectedLanguages defaultValue = new SelectedLanguages(60, 3);
        SelectedLanguages value = (SelectedLanguages) mUserDataRepo.getValue(UserDataKey.SELECTED_LANGUAGES, defaultValue);
        Timber.d("getSelectedLangs: from " + value.from() + " to " + value.to());
        return value;
    }

    @Override
    public void updateSelectedLangs(SelectedLanguages selectedLanguages) {
        mUserDataRepo.putValue(UserDataKey.SELECTED_LANGUAGES, selectedLanguages);
    }
}
