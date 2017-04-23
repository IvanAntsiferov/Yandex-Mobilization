package com.voltek.yandex.mobilization.ui.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.interactor.user_data.UserDataUseCase;

import javax.inject.Inject;

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    @Inject
    UserDataUseCase mUserData;

    public SettingsPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
    }

    // View lifecycle
    @Override
    public void attachView(SettingsView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
    }

    @Override
    public void detachView(SettingsView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications


    // Private logic
}
