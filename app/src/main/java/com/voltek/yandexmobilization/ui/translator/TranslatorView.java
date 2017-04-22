package com.voltek.yandexmobilization.ui.translator;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.voltek.yandexmobilization.ui.BaseView;

import java.util.List;

public interface TranslatorView extends BaseView {

    @StateStrategyType(SkipStrategy.class)
    void setupSpinners(List<String> languages, int from, int to);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void changeLanguagesSelected(int from, int to);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showTranslationResult(String result);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideError();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void fillTextFields(String from, String to);

    @StateStrategyType(SkipStrategy.class)
    void hideResults();

    @StateStrategyType(SkipStrategy.class)
    void openResultInDialog(String result);
}
