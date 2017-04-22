package com.voltek.yandex.mobilization.interactor.translation;

import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.data.entity.Translation;

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
            String translationDirection = mLangsRepo.makeTranslationDirectionStr(fromId, toId);

            Translation fromCache =
                    mTranslationsRepo.searchTranslationInCache(text, translationDirection);

            if (fromCache == null) {
                mTranslationsRepo.translateApiRequest(text, translationDirection)
                        .doOnNext(translation -> mTranslationsRepo.addTranslationToCache(translation))
                        .doFinally(emitter::onComplete)
                        .subscribe(emitter::onNext, emitter::onError);
            } else {
                Timber.d("translate, return cache with id " + fromCache.getId());
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
}
