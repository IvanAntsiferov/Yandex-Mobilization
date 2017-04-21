package com.voltek.yandexmobilization.ui.translator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.view.RxView;
import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.ui.BaseFragment;

import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class TranslatorFragment extends BaseFragment implements TranslatorView {

    // TODO при выборе двух одинаковых языков в обеих полях, менять их местами

    @InjectPresenter
    TranslatorPresenter mPresenter;

    @BindView(R.id.ib_swap)
    ImageButton mSwapLangs;
    @BindView(R.id.spin_from)
    Spinner mSpinFrom;
    @BindView(R.id.spin_to)
    Spinner mSpinTo;

    // Lifecycle
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
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
        Disposable swapLangsButton = RxView.clicks(mSwapLangs)
                .subscribe(o -> { /* TODO notify presenter */ }, Timber::e);

        mDisposable.addAll(swapLangsButton);
    }

    @Override
    public void detachInputListeners() {
        mDisposable.clear();
    }

    @Override
    public void setupSpinners(List<String> languages, int selectFrom, int selectTo) {
        ArrayAdapter langAdapter = new ArrayAdapter<>(
                getContext(), R.layout.item_spinner_lang, languages);

        mSpinFrom.setAdapter(langAdapter);
        mSpinTo.setAdapter(langAdapter);

        mSpinFrom.setSelection(selectFrom);
        mSpinTo.setSelection(selectTo);
    }
}
