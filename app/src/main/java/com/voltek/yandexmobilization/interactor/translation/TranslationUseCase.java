package com.voltek.yandexmobilization.interactor.translation;

import com.voltek.yandexmobilization.data.entity.Translation;

import io.reactivex.Observable;

public interface TranslationUseCase {

    Observable<Translation> translate(String text, int fromId, int toId);
}
