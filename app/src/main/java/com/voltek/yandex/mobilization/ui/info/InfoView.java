package com.voltek.yandex.mobilization.ui.info;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.voltek.yandex.mobilization.ui.BaseView;

public interface InfoView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showInfo(String app, String developer, String yandexApi);
}
