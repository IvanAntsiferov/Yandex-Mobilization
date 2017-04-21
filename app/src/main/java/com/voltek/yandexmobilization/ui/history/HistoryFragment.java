package com.voltek.yandexmobilization.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.ui.BaseFragment;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @InjectPresenter
    HistoryPresenter mPresenter;

    // Lifecycle
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
