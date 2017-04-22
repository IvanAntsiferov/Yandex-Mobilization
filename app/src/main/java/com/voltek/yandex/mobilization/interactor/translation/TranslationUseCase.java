package com.voltek.yandex.mobilization.interactor.translation;

import com.voltek.yandex.mobilization.data.entity.Translation;

import io.reactivex.Observable;

public interface TranslationUseCase {

    Observable<Translation> translate(String text, int fromId, int toId);

    void updateFavorites(Translation translation);
}
