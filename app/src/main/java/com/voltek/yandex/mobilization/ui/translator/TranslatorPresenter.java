package com.voltek.yandex.mobilization.ui.translator;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.entity.presentation.SelectedLanguages;
import com.voltek.yandex.mobilization.entity.general.Translation;
import com.voltek.yandex.mobilization.entity.presentation.TranslationDirection;
import com.voltek.yandex.mobilization.interactor.language.LanguageUseCase;
import com.voltek.yandex.mobilization.interactor.translation.TranslationUseCase;
import com.voltek.yandex.mobilization.interactor.user_data.UserDataUseCase;

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
        // TODO сделать поиск в реальном времени
        mInput = newValue;
        getViewState().hideMessage();
    }

    public void translateAction() {
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
            updateTranslationFavoriteState();
        } else {
            getViewState().showMessage(mContext.getString(R.string.error_nothing_to_favorite));
        }
    }

    public void fullscreenPressed() {
        if (!mOutput.isEmpty() && mLastLoadedTranslation != null) {
            TranslationDirection direction =
                    mLanguages.getDirectionNames(mLastLoadedTranslation.getLangs());
            getViewState().openResultInDialog(
                    mLastLoadedTranslation.getFromText(), mLastLoadedTranslation.getToText(),
                    direction.getFromLang(), direction.getToLang());
        }
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
                    getViewState().showMessage(mContext.getString(R.string.msg_translated_by_yandex));
                }, throwable -> getViewState().showMessage(throwable.getMessage()));
    }

    private void updateTranslationFavoriteState() {
        mIsFavorite = !mLastLoadedTranslation.getFavorite();
        mLastLoadedTranslation.setFavorite(mIsFavorite);
        mTranslation.updateFavorites(mLastLoadedTranslation);
        getViewState().setFavoriteIcon(mIsFavorite);
        if (mIsFavorite) {
            getViewState().showMessage(mContext.getString(R.string.msg_added_to_favorites));
        } else {
            getViewState().showMessage(mContext.getString(R.string.msg_removed_from_favorites));
        }
    }

    private void wipeTextFields() {
        mLastLoadedTranslation = null;
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
