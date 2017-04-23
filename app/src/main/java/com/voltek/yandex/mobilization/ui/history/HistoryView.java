package com.voltek.yandex.mobilization.ui.history;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.voltek.yandex.mobilization.entity.general.Translation;
import com.voltek.yandex.mobilization.ui.BaseView;

import java.util.List;

public interface HistoryView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void replaceData(List<Translation> translations);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showEmpty(String message);

    @StateStrategyType(SkipStrategy.class)
    void hideEmpty();

    @StateStrategyType(SkipStrategy.class)
    void showToast(String message, int length);

    @StateStrategyType(SkipStrategy.class)
    void showWipeHistoryDialog();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void changeFilterFavoriteIcon(boolean isChecked);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void changeSearchFieldText(String text);

    @StateStrategyType(SkipStrategy.class)
    void hideSoftKeyboard();

    @StateStrategyType(SkipStrategy.class)
    void openTranslationInDialog(String fromText, String toText, String fromLang, String toLang);
}
