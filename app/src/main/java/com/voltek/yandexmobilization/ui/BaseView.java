package com.voltek.yandexmobilization.ui;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {

    void attachInputListeners();

    void detachInputListeners();
}
