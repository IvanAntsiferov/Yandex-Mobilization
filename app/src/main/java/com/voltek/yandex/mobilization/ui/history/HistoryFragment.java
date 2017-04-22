package com.voltek.yandex.mobilization.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.ui.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "history")
    HistoryPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private TranslationAdapter mAdapter;

    // Lifecycle
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAdapter = new TranslationAdapter(getContext(), new ArrayList<>());
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
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