package com.voltek.yandexmobilization.interactor.translation;

import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.DataProvider;
import com.voltek.yandexmobilization.data.entity.Translation;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

public class TranslationInteractor implements TranslationUseCase {

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
                        .doOnNext(translation -> {
                            // Cache translation, if it is different from input text
                            if (!translation.getToText().equals(text)) {
                                Timber.d("translate, caching");
                                mTranslationsRepo.addTranslationToCache(translation);
                            }
                        })
                        .doFinally(emitter::onComplete)
                        .subscribe(emitter::onNext, emitter::onError);
            } else {
                Timber.d("translate, return cache");
                // Return cache
                emitter.onNext(fromCache);
                emitter.onComplete();
            }
        });
    }
}
