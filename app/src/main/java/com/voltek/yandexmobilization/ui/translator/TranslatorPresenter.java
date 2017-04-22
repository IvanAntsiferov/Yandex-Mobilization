package com.voltek.yandexmobilization.ui.translator;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.entity.SelectedLanguages;
import com.voltek.yandexmobilization.data.entity.Translation;
import com.voltek.yandexmobilization.interactor.language.LanguageUseCase;
import com.voltek.yandexmobilization.interactor.translation.TranslationUseCase;
import com.voltek.yandexmobilization.interactor.user_data.UserDataUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class TranslatorPresenter extends MvpPresenter<TranslatorView> {

    @Inject
    Context mContext;

    @Inject
    UserDataUseCase mUserData;

    @Inject
    LanguageUseCase mLanguages;

    @Inject
    TranslationUseCase mTranslation;

    private int mSelectedFrom;
    private int mSelectedTo;

    private String mInput = "";
    private String mOutput = "";
    private boolean mIsFavorite = false;

    private Translation mLastLoadedTranslation;

    public TranslatorPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
        SelectedLanguages selectedLanguages = mUserData.getSelectedLangs();
        mSelectedFrom = selectedLanguages.from();
        mSelectedTo = selectedLanguages.to();
        Timber.d("TranslatorPresenter: from " + mSelectedFrom + " to " + mSelectedTo);
    }

    // View lifecycle
    @Override
    public void attachView(TranslatorView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
        getViewState().setupSpinners(mLanguages.getLangsNames(), mSelectedFrom, mSelectedTo);
        getViewState().fillTextFields(mInput, mOutput, mIsFavorite);
    }

    @Override
    public void detachView(TranslatorView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications
    public void swapLanguages() {
        swapSelection();
    }

    public void selectorFrom(int index) {
        Timber.d("selectorFrom: " + index);
        // Prevent from selected same language in both fields
        if (index == mSelectedTo) {
            swapSelection();
        } else {
            mSelectedFrom = index;
            updateSelectedLangs();
        }
    }

    public void selectorTo(int index) {
        Timber.d("selectorTo: " + index);
        // Swap langs, if user tries to select same langs in both fields
        if (index == mSelectedFrom) {
            swapSelection();
        } else {
            mSelectedTo = index;
            updateSelectedLangs();
        }
    }

    public void inputChanges(String newValue) {
        mInput = newValue;
        getViewState().hideMessage();
    }

    public void editTextAction() {
        getViewState().hideResults();

        if (isInputCorrect()) {
            translationRequest();
        } else {
            wipeTextFields();
            getViewState().showMessage(mContext.getString(R.string.error_input_short));
        }
    }

    public void favoritePressed() {
        if (mLastLoadedTranslation != null) {
            mIsFavorite = !mLastLoadedTranslation.getFavorite();
            mLastLoadedTranslation.setFavorite(mIsFavorite);
            mTranslation.updateFavorites(mLastLoadedTranslation);
            getViewState().setFavoriteIcon(mIsFavorite);
            if (mIsFavorite) {
                getViewState().showMessage(mContext.getString(R.string.msg_added_to_favorites));
            } else {
                getViewState().showMessage(mContext.getString(R.string.msg_removed_from_favorites));
            }
        } else {
            getViewState().showMessage(mContext.getString(R.string.error_nothing_to_favorite));
        }
    }

    public void fullscreenPressed() {
        if (!mOutput.isEmpty())
            getViewState().openResultInDialog(mOutput);
    }

    public void clearInputPressed() {
        if (!mInput.isEmpty() || !mOutput.isEmpty()) {
            wipeTextFields();
        }
    }

    // Private logic
    private void translationRequest() {
        mTranslation.translate(mInput, mSelectedFrom, mSelectedTo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .doFinally(() -> getViewState().hideLoading())
                .subscribe(translation -> {
                    mLastLoadedTranslation = translation;
                    mOutput = translation.getToText();
                    mIsFavorite = translation.getFavorite();
                    getViewState().showTranslationResult(mOutput, mIsFavorite);
                }, throwable -> getViewState().showMessage(throwable.getMessage()));
    }

    private void wipeTextFields() {
        mIsFavorite = false;
        mInput = mOutput = "";
        getViewState().fillTextFields(mInput, mOutput, mIsFavorite);
    }

    private void swapSelection() {
        Timber.d("swapSelection");
        int temp = mSelectedFrom;
        mSelectedFrom = mSelectedTo;
        mSelectedTo = temp;
        getViewState().changeLanguagesSelected(mSelectedFrom, mSelectedTo);

        if (!mOutput.isEmpty()) {
            String tempStr = mInput;
            mInput = mOutput;
            mOutput = tempStr;
            getViewState().fillTextFields(mInput, mOutput, mIsFavorite);
        }
        updateSelectedLangs();
    }

    private void updateSelectedLangs() {
        SelectedLanguages newValue = new SelectedLanguages(mSelectedFrom, mSelectedTo);
        mUserData.updateSelectedLangs(newValue);
    }

    private boolean isInputCorrect() {
        return !mInput.isEmpty() && mInput.length() < 10000;
    }
}
