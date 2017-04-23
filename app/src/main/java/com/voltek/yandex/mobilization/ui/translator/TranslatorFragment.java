package com.voltek.yandex.mobilization.ui.translator;

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
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.ui.BaseFragment;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
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
    ImageButton mButtonFavorite;
    @BindView(R.id.ib_fullscreen)
    ImageButton mButtonFullscreen;
    @BindView(R.id.ib_clear_input)
    ImageButton mButtonClearInput;
    @BindView(R.id.ib_translate)
    ImageButton mButtonTranslate;

    // Lifecycle
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
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

        Disposable favoriteButton = RxView.clicks(mButtonFavorite)
                .subscribe(o -> mPresenter.favoritePressed(), Timber::e);

        Disposable fullscreenButton = RxView.clicks(mButtonFullscreen)
                .subscribe(o -> mPresenter.fullscreenPressed(), Timber::e);

        Disposable clearInputButton = RxView.clicks(mButtonClearInput)
                .subscribe(o -> mPresenter.clearInputPressed(), Timber::e);

        Disposable translationRequest = Observable
                .mergeDelayError(RxView.clicks(mButtonTranslate), RxTextView.editorActions(mEditText))
                .subscribe(o -> mPresenter.translateAction(), Timber::e);

        mDisposable.addAll(
                swapLangsButton, spinnerFrom, spinnerTo, inputChanges, translationRequest,
                favoriteButton, fullscreenButton, clearInputButton);
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
    public void showTranslationResult(String result, boolean isFavorite) {
        mResult.setText(result);
        mResult.setVisibility(View.VISIBLE);
        setFavoriteIcon(isFavorite);
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
    public void showMessage(String message) {
        mErrorView.setText(message);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessage() {
        mErrorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void fillTextFields(String from, String to, boolean isFavorite) {
        Timber.d("fillTextFields; from: " + from + "; to: " + to);
        mEditText.setText(from);
        mResult.setText(to);
        mResult.setVisibility(View.VISIBLE);
        setFavoriteIcon(isFavorite);
    }

    @Override
    public void hideResults() {
        mResult.setVisibility(GONE);
        setFavoriteIcon(false);
    }

    @Override
    public void openResultInDialog(String result) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        TranslationResultDialog dialog = TranslationResultDialog.newInstance(result);
        dialog.show(fm, "dialog_translation_result");
        Timber.d(result);
    }

    @Override
    public void setFavoriteIcon(boolean isFavorite) {
        if (isFavorite) {
            mButtonFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_bookmark_24dp));
        } else {
            mButtonFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_bookmark_border_24dp));
        }
    }
}
