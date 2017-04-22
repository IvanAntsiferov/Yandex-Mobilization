package com.voltek.yandex.mobilization.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.jakewharton.rxbinding2.view.RxView;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.entity.general.Translation;
import com.voltek.yandex.mobilization.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "history")
    HistoryPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.rl_empty_state)
    RelativeLayout mEmptyState;
    @BindView(R.id.ib_delete)
    ImageButton mButtonDelete;

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
        Disposable buttonDelete = RxView.clicks(mButtonDelete)
                .subscribe(o -> mPresenter.deleteButtonPressed(), Timber::e);

        mDisposable.addAll(buttonDelete);
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

    @Override
    public void showToast(String message, int length) {
        Toast.makeText(getContext(), message, length).show();
    }

    @Override
    public void showWipeHistoryDialog() {
        Disposable wipeDialog = Observable.create(subscriber -> {
            final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                    .setMessage(R.string.dlg_wipe_cache_warning)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        subscriber.onNext(new Object());
                        subscriber.onComplete();
                    })
                    .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                        dialog.dismiss();
                        subscriber.onComplete();
                    })
                    .create();
            alertDialog.show();
        }).subscribe(o -> mPresenter.wipeHistoryDialogConfirm(), Timber::e);

        mDisposable.add(wipeDialog);
    }
}
