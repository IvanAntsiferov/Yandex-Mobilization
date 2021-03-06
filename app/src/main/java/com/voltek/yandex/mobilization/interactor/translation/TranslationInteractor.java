package com.voltek.yandex.mobilization.interactor.translation;

import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.entity.Mapper;
import com.voltek.yandex.mobilization.entity.general.Translation;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

public class TranslationInteractor implements com.voltek.yandex.mobilization.interactor.translation.TranslationUseCase {

    @Inject
    DataProvider.Languages mLangsRepo;

    @Inject
    DataProvider.Translations mTranslationsRepo;

    public TranslationInteractor() {
        TranslatorApp.getInteractorComponent().inject(this);
    }

    @Override
    public Observable<Translation> translate(String text, int fromId, int toId) {
        return Observable.create(emitter -> {
            String from;
            String to;
            try {
                from = mLangsRepo.getLangCodeByIndex(fromId);
                to = mLangsRepo.getLangCodeByIndex(toId);
            } catch (IndexOutOfBoundsException e) {
                Timber.e(e);
                from = "";
                to = "";
            }
            String translationDirection = Mapper.makeTranslationDirectionString(from, to);

            Translation fromCache =
                    mTranslationsRepo.searchTranslationInCache(text, translationDirection);

            if (fromCache == null) {
                // Make API request
                mTranslationsRepo.translateApiRequest(text, translationDirection)
                        .doOnNext(translation -> mTranslationsRepo.addTranslationToCache(translation))
                        .doFinally(emitter::onComplete)
                        .subscribe(emitter::onNext, emitter::onError);
            } else {
                // Return cache
                emitter.onNext(fromCache);
                emitter.onComplete();
            }
        });
    }

    @Override
    public void updateFavorites(Translation translation) {
        mTranslationsRepo.updateTranslationInCache(translation);
    }

    @Override
    public List<Translation> getHistory(int newerThan, boolean onlyFavorites, String contains) {
        return mTranslationsRepo.getCache(newerThan, onlyFavorites, contains);
    }

    @Override
    public void wipeHistory() {
        mTranslationsRepo.wipeCache();
    }
}
