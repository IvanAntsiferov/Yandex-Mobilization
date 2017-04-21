package com.voltek.yandexmobilization.ui;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void attachInputListeners();

    @StateStrategyType(SkipStrategy.class)
    void detachInputListeners();
}
