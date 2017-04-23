package com.voltek.yandex.mobilization.ui.translation_details_dialog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class TranslationDetailsDialogPresenter extends MvpPresenter<TranslationDetailsDialogView> {

    // View lifecycle
    @Override
    public void attachView(TranslationDetailsDialogView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
    }

    @Override
    public void detachView(TranslationDetailsDialogView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }
}
