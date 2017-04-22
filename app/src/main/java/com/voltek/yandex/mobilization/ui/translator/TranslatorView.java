package com.voltek.yandex.mobilization.ui.translator;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.voltek.yandex.mobilization.ui.BaseView;

import java.util.List;

public interface TranslatorView extends BaseView {

    @StateStrategyType(SkipStrategy.class)
    void setupSpinners(List<String> languages, int from, int to);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void changeLanguagesSelected(int from, int to);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showTranslationResult(String result, boolean isFavorite);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideMessage();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void fillTextFields(String from, String to, boolean isFavorite);

    @StateStrategyType(SkipStrategy.class)
    void hideResults();

    @StateStrategyType(SkipStrategy.class)
    void openResultInDialog(String result);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setFavoriteIcon(boolean isFavorite);
}
