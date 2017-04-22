package com.voltek.yandexmobilization.ui.translator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.ui.BaseFragment;

import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static android.view.View.GONE;

public class TranslatorFragment extends BaseFragment implements TranslatorView {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "translator")
    TranslatorPresenter mPresenter;

    @BindView(R.id.ib_swap)
    ImageButton mSwapLangs;
    @BindView(R.id.spin_from)
    Spinner mSpinFrom;
    @BindView(R.id.spin_to)
    Spinner mSpinTo;
    @BindView(R.id.et_translate)
    EditText mEditText;
    @BindView(R.id.tv_result)
    TextView mResult;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_error)
    TextView mErrorView;
    @BindView(R.id.ib_favorite)
    ImageButton mFavorite;
    @BindView(R.id.ib_fullscreen)
    ImageButton mFullscreen;

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
                .skip(1) // Skip first emmit because of spinner auto-select
                .subscribe(index -> mPresenter.selectorFrom(index), Timber::e);

        Disposable spinnerTo = RxAdapterView.itemSelections(mSpinTo)
                .skip(1) // Skip first emmit because of spinner auto-select
                .subscribe(index -> mPresenter.selectorTo(index), Timber::e);

        Disposable inputChanges = RxTextView.textChanges(mEditText)
                .skip(1) // First always empty
                .subscribe(charSequence -> mPresenter.inputChanges(charSequence.toString()), Timber::e);

        Disposable editorAction = RxTextView.editorActions(mEditText)
                .subscribe(integer -> mPresenter.editTextAction(), Timber::e);

        Disposable favoriteButton = RxView.clicks(mFavorite)
                .subscribe(o -> mPresenter.favoritePressed(), Timber::e);

        Disposable fullscreenButton = RxView.clicks(mFullscreen)
                .subscribe(o -> mPresenter.fullscreenPressed(), Timber::e);

        mDisposable.addAll(
                swapLangsButton, spinnerFrom, spinnerTo, inputChanges, editorAction,
                favoriteButton, fullscreenButton);
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

    @Override
    public void showTranslationResult(String result) {
        mResult.setText(result);
        mResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(GONE);
    }

    @Override
    public void showError(String message) {
        mErrorView.setText(message);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        mErrorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void fillTextFields(String from, String to) {
        Timber.d("fillTextFields; from: " + from + "; to: " + to);
        mEditText.setText(from);
        mResult.setText(to);
        mResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResults() {
        mResult.setVisibility(GONE);
    }

    @Override
    public void openResultInDialog(String result) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        TranslationResultDialog dialog = TranslationResultDialog.newInstance(result);
        dialog.show(fm, "dialog_translation_result");
        Timber.d(result);
    }

    // TODO кнопка "очистить" для поля ввода
}
