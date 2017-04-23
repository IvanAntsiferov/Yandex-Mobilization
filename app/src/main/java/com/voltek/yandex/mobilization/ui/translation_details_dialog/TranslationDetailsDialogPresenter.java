package com.voltek.yandex.mobilization.ui.translation_details_dialog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class TranslationDetailsDialogPresenter extends MvpPresenter<TranslationDetailsDialogView> {

    private String mFromText;
    private String mToText;
    private String mFromLang;
    private String mToLang;

    public TranslationDetailsDialogPresenter(String mFromText, String mToText, String mFromLang, String mToLang) {
        this.mFromText = mFromText;
        this.mToText = mToText;
        this.mFromLang = mFromLang;
        this.mToLang = mToLang;
    }

    // View lifecycle
    @Override
    public void attachView(TranslationDetailsDialogView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
        getViewState().showContent(mFromText, mToText, mFromLang, mToLang);
    }

    @Override
    public void detachView(TranslationDetailsDialogView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }
}
