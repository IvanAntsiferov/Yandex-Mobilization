package com.voltek.yandex.mobilization.ui.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    public HistoryPresenter() {}

    // View lifecycle
    @Override
    public void attachView(HistoryView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
    }

    @Override
    public void detachView(HistoryView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications

    // Private logic
}
