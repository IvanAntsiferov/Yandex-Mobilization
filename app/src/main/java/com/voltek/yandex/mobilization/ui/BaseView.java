package com.voltek.yandex.mobilization.ui;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseView extends MvpView {

    // Subscribe presenter to input events
    @StateStrategyType(SkipStrategy.class)
    void attachInputListeners();

    // Unsubscribe presenter from input events
    @StateStrategyType(SkipStrategy.class)
    void detachInputListeners();
}
