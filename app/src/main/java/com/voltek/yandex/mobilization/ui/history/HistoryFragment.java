package com.voltek.yandex.mobilization.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.entity.general.Translation;
import com.voltek.yandex.mobilization.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "history")
    HistoryPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.rl_empty_state)
    RelativeLayout mEmptyState;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        DividerItemDecoration divider =
                new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(divider);
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

    @Override
    public void replaceData(List<Translation> translations) {
        mAdapter.replaceData(translations);
    }

    @Override
    public void addData(List<Translation> translations) {
        mAdapter.addData(translations);
    }

    @Override
    public void showEmpty() {
        mEmptyState.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        mEmptyState.setVisibility(View.GONE);
    }
}
