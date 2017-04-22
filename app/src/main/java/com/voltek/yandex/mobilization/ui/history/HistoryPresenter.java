package com.voltek.yandex.mobilization.ui.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.entity.general.Translation;
import com.voltek.yandex.mobilization.interactor.translation.TranslationUseCase;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    @Inject
    TranslationUseCase mTranslations;

    private int mLastId = -1;

    public HistoryPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
        loadData();
    }

    // View lifecycle
    @Override
    public void attachView(HistoryView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
        checkIfDataWasUpdated();
    }

    @Override
    public void detachView(HistoryView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications

    // Private logic
    private void loadData() {
        List<Translation> translations = mTranslations.getHistory();
        if (translations.isEmpty()) {
            getViewState().showEmpty();
        } else {
            mLastId = translations.get(0).getId();
            getViewState().replaceData(translations);
        }
    }

    private void checkIfDataWasUpdated() {
        List<Translation> translations = mTranslations.getHistoryNewerThan(mLastId);
        if (!translations.isEmpty()) {
            mLastId = translations.get(0).getId();
            getViewState().hideEmpty();
            getViewState().addData(translations);
        } else if (mLastId == -1) {
            getViewState().showEmpty();
        }
    }
}
