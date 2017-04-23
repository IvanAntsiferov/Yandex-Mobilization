package com.voltek.yandex.mobilization.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.ui.BaseFragment;

// TODO сделать экран настроек: Функция мгновенного перевода. О приложении.
public class SettingsFragment extends BaseFragment implements SettingsView {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "settings")
    SettingsPresenter mPresenter;

    // Lifecycle
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}
