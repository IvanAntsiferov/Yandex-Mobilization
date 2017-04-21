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
import com.jakewharton.rxbinding2.widget.RxAdapterView;
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
                .subscribe(o -> mPresenter.swapLanguages(), Timber::e);

        Disposable spinnerFrom = RxAdapterView.itemSelections(mSpinFrom)
                .subscribe(index -> mPresenter.selectorFrom(index), Timber::e);

        Disposable spinnerTo = RxAdapterView.itemSelections(mSpinTo)
                .subscribe(index -> mPresenter.selectorTo(index), Timber::e);

        mDisposable.addAll(swapLangsButton, spinnerFrom, spinnerTo);
    }

    @Override
    public void detachInputListeners() {
        mDisposable.clear();
    }

    @Override
    public void setupSpinners(List<String> languages, int from, int to) {
        ArrayAdapter langAdapter = new ArrayAdapter<>(
                getContext(), R.layout.item_spinner_lang, languages);

        mSpinFrom.setAdapter(langAdapter);
        mSpinTo.setAdapter(langAdapter);

        changeLanguagesSelected(from, to);
    }

    @Override
    public void changeLanguagesSelected(int from, int to) {
        Timber.d("changeLanguagesSelected: from " + from + " to " + to);
        mSpinFrom.setSelection(from);
        mSpinTo.setSelection(to);
    }
}
