package com.voltek.yandex.mobilization.ui.main;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.voltek.yandex.mobilization.ui.BaseView;

public interface MainView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void selectFragmentId(int id);
}
