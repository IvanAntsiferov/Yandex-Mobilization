package com.voltek.yandex.mobilization.ui.translation_details_dialog;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.voltek.yandex.mobilization.ui.BaseView;

public interface TranslationDetailsDialogView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showContent(String fromText, String toText, String fromLang, String toLang);
}
