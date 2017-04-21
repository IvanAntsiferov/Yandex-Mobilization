package com.voltek.yandexmobilization.ui.translator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.entity.SelectedLanguages;
import com.voltek.yandexmobilization.interactor.language.LanguageUseCase;
import com.voltek.yandexmobilization.interactor.translation.TranslationUseCase;
import com.voltek.yandexmobilization.interactor.user_data.UserDataUseCase;
import com.voltek.yandexmobilization.navigation.proxy.RouterBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class TranslatorPresenter extends MvpPresenter<TranslatorView> {

    @Inject
    UserDataUseCase mUserData;

    @Inject
    LanguageUseCase mLanguages;

    @Inject
    TranslationUseCase mTranslation;

    private RouterBus mRouter;

    private int mSelectedFrom;
    private int mSelectedTo;

    private String mInput = "";

    public TranslatorPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
        mRouter = TranslatorApp.getRouterBus();
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
    }

    public void editTextAction() {
        if (isInputCorrect()) {
            mTranslation.translate(mInput, mSelectedFrom, mSelectedTo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(translation -> {
                        getViewState().showTranslationResult(translation.getToText().toString());
                    }, Timber::e);
        }
    }

    // Private logic
    private void swapSelection() {
        Timber.d("swapSelection");
        int temp = mSelectedFrom;
        mSelectedFrom = mSelectedTo;
        mSelectedTo = temp;
        getViewState().changeLanguagesSelected(mSelectedFrom, mSelectedTo);
        updateSelectedLangs();
    }

    private void updateSelectedLangs() {
        SelectedLanguages newValue = new SelectedLanguages(mSelectedFrom, mSelectedTo);
        mUserData.updateSelectedLangs(newValue);
    }

    private boolean isInputCorrect() {
        return !mInput.isEmpty();
    }
}
