package com.voltek.yandexmobilization.ui.favorites;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritesView> {

    public FavoritesPresenter() {}

    // View lifecycle
    @Override
    public void attachView(FavoritesView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
    }

    @Override
    public void detachView(FavoritesView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications

    // Private logic
}
