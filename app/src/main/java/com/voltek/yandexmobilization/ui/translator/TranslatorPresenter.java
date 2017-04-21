package com.voltek.yandexmobilization.ui.translator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.interactor.language.LanguageUseCase;
import com.voltek.yandexmobilization.navigation.proxy.RouterBus;

import javax.inject.Inject;

@InjectViewState
public class TranslatorPresenter extends MvpPresenter<TranslatorView> {

    @Inject
    LanguageUseCase mLanguages;

    private RouterBus mRouter;

    private int mSelectedFrom;
    private int mSelectedTo;

    public TranslatorPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
        mRouter = TranslatorApp.getRouterBus();
        mSelectedFrom = 0;
        mSelectedTo = 2;
    }

    // View lifecycle
    @Override
    public void attachView(TranslatorView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
        getViewState().setupSpinners(mLanguages.getLangsNames(), mSelectedFrom, mSelectedTo);
    }

    @Override
    public void detachView(TranslatorView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications
    public void swapLanguages() {
        swapSelection();
    }

    public void selectorFrom(int index) {
        // Prevent from selected same language in both fields
        if (index == mSelectedTo) {
            swapSelection();
        } else {
            mSelectedFrom = index;
        }
    }

    public void selectorTo(int index) {
        // Swap langs, if user tries to select same langs in both fields
        if (index == mSelectedFrom) {
            swapSelection();
        } else {
            mSelectedTo = index;
        }
    }

    // Private logic
    private void swapSelection() {
        int temp = mSelectedFrom;
        mSelectedFrom = mSelectedTo;
        mSelectedTo = temp;
        getViewState().changeLanguagesSelected(mSelectedFrom, mSelectedTo);
    }
}
