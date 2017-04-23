package com.voltek.yandex.mobilization.ui.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.interactor.user_data.UserDataUseCase;

import javax.inject.Inject;

@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> {

    @Inject
    UserDataUseCase mUserData;

    public InfoPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
    }

    // View lifecycle
    @Override
    public void attachView(InfoView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
    }

    @Override
    public void detachView(InfoView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications


    // Private logic
}
