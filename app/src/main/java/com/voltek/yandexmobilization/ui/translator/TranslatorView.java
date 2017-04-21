package com.voltek.yandexmobilization.ui.translator;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.voltek.yandexmobilization.ui.BaseView;

import java.util.List;

public interface TranslatorView extends BaseView {

    @StateStrategyType(SkipStrategy.class)
    void setupSpinners(List<String> languages, int from, int to);

    void changeLanguagesSelected(int from, int to);
}
