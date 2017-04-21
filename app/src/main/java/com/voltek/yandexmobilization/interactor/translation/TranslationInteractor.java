package com.voltek.yandexmobilization.interactor.translation;

import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.DataProvider;
import com.voltek.yandexmobilization.data.entity.Translation;

import javax.inject.Inject;

import io.reactivex.Observable;

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
            // TODO сохранять в кэш, искать в кэшэ, отсеивать значения, равные исходному тексту.
            mTranslationsRepo.translateApiRequest(text, translationDirection)
                    .doFinally(emitter::onComplete)
                    .subscribe(emitter::onNext, emitter::onError);
        });
    }
}
