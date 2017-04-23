package com.voltek.yandex.mobilization.ui.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.ui.BaseFragment;

import butterknife.BindView;

public class InfoFragment extends BaseFragment implements InfoView {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "info")
    InfoPresenter mPresenter;

    @BindView(R.id.tv_app)
    TextView mTvApp;
    @BindView(R.id.tv_developer)
    TextView mTvDeveloper;
    @BindView(R.id.tv_yandex_translate_api)
    TextView mTvYandexApi;

    // Lifecycle
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvDeveloper.setMovementMethod(LinkMovementMethod.getInstance());
        mTvDeveloper.setClickable(true);
        mTvYandexApi.setMovementMethod(LinkMovementMethod.getInstance());
        mTvYandexApi.setClickable(true);
    }

    // View interface
    @Override
    public void attachInputListeners() {
        mDisposable.addAll();
    }

    @Override
    public void detachInputListeners() {
        mDisposable.clear();
    }

    @Override
    public void showInfo(String app, String developer, String yandexApi) {
        mTvApp.setText(app);
        mTvDeveloper.setText(Html.fromHtml(developer));
        mTvYandexApi.setText(Html.fromHtml(yandexApi));
    }
}
