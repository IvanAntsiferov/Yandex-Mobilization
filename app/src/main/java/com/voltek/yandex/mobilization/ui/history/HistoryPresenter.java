package com.voltek.yandex.mobilization.ui.history;

import android.content.Context;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.entity.general.Translation;
import com.voltek.yandex.mobilization.interactor.translation.TranslationUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    @Inject
    Context mContext;

    @Inject
    TranslationUseCase mTranslations;

    private List<Translation> mItems = new ArrayList<>();
    private int mLastId = -1;
    private boolean mFilterFavorite = false;
    private String mSearchQuery = "";

    public HistoryPresenter() {
        TranslatorApp.getPresenterComponent().inject(this);
        loadData();
    }

    // View lifecycle
    @Override
    public void attachView(HistoryView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
        checkIfDataWasUpdated();
        getViewState().changeFilterFavoriteIcon(mFilterFavorite);
        getViewState().changeSearchFieldText(mSearchQuery);
    }

    @Override
    public void detachView(HistoryView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications
    public void deleteButtonPressed() {
        if (mLastId == -1) {
            getViewState().showToast(
                    mContext.getString(R.string.error_nothing_to_delete), Toast.LENGTH_SHORT);
        } else {
            getViewState().showWipeHistoryDialog();
        }
    }

    public void wipeHistoryDialogConfirm() {
        wipeData();
    }

    public void filterFavoritePressed() {
        getViewState().hideEmpty();
        mFilterFavorite = !mFilterFavorite;
        getViewState().changeFilterFavoriteIcon(mFilterFavorite);
        loadData();
    }

    public void searchButtonPressed() {
        if (!mSearchQuery.isEmpty())
            loadData();
    }

    public void clearSearchButtonPressed() {
        if (!mSearchQuery.isEmpty()) {
            mSearchQuery = "";
            getViewState().changeSearchFieldText(mSearchQuery);
            loadData();
        }
    }

    public void searchQueryChanges(String newValue) {
        mSearchQuery = newValue;
    }

    public void onItemClick(Translation translation) {
        // TODO open dialog with details
    }

    public void onItemFavoriteClick(Translation translation) {
        translation.setFavorite(!translation.getFavorite());
        mTranslations.updateFavorites(translation);
    }

    // Private logic
    private void loadData() {
        getViewState().hideEmpty();
        mItems.clear();
        mItems = mTranslations.getHistory(-1, mFilterFavorite, mSearchQuery);
        Timber.d("loadData, size " + mItems.size());
        if (mItems.isEmpty()) {
            mLastId = -1;
            chooseError();
        } else {
            mLastId = mItems.get(0).getId();
        }
        getViewState().replaceData(mItems);
    }

    private void checkIfDataWasUpdated() {
        List<Translation> translations = mTranslations.getHistory(mLastId, mFilterFavorite, mSearchQuery);
        if (!translations.isEmpty()) {
            mLastId = translations.get(0).getId();
            mItems.addAll(0, translations);
            getViewState().hideEmpty();
            getViewState().replaceData(mItems);
        } else if (mLastId == -1) {
            chooseError();
        }
    }

    private void wipeData() {
        mLastId = -1;
        mSearchQuery = "";
        mItems.clear();
        mTranslations.wipeHistory();
        getViewState().replaceData(new ArrayList<>());
        getViewState().changeSearchFieldText(mSearchQuery);
        getViewState().showEmpty(mContext.getString(R.string.error_no_history));
    }

    private void chooseError() {
        if (!mSearchQuery.isEmpty()) {
            getViewState().showEmpty(mContext.getString(R.string.error_nothing_found));
        } else if (mFilterFavorite) {
            getViewState().showEmpty(mContext.getString(R.string.error_no_favorites));
        } else {
            getViewState().showEmpty(mContext.getString(R.string.error_no_history));
        }
    }
}
