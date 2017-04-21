package com.voltek.yandexmobilization.ui.translator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.interactor.language.LanguageUseCase;
import com.voltek.yandexmobilization.navigation.proxy.RouterBus;

import java.util.ArrayList;

import javax.inject.Inject;

@InjectViewState
public class TranslatorPresenter extends MvpPresenter<TranslatorView> {

    @Inject
    LanguageUseCase mLanguages;

    private RouterBus mRouter;

    private int selectedFrom = 0;
    private int selectedTo = 1;

    public TranslatorPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
        mRouter = TranslatorApp.getRouterBus();
    }

    // View lifecycle
    @Override
    public void attachView(TranslatorView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
        getViewState().setupSpinners(mLanguages.getLangsNames(), selectedFrom, selectedTo);
    }

    @Override
    public void detachView(TranslatorView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications

    // Private logic
}
