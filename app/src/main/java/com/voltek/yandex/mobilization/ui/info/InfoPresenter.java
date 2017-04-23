package com.voltek.yandex.mobilization.ui.info;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.BuildConfig;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.TranslatorApp;

import javax.inject.Inject;

@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> {

    @Inject
    Context mContext;

    public InfoPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
        String app = mContext.getString(R.string.app_name) +
                mContext.getString(R.string.inf_version) + BuildConfig.VERSION_NAME;
        String developer = mContext.getString(R.string.inf_developer);
        String yandexApi = mContext.getString(R.string.inf_yandex_translate_api);
        getViewState().showInfo(app, developer, yandexApi);
    }

    // View lifecycle
    @Override
    public void attachView(InfoView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
    }

    @Override
    public void detachView(InfoView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications

    // Private logic
}
