package com.voltek.yandex.mobilization.interactor.translation;

import com.voltek.yandex.mobilization.entity.general.Translation;

import java.util.List;

import io.reactivex.Observable;

public interface TranslationUseCase {

    Observable<Translation> translate(String text, int fromId, int toId);

    void updateFavorites(Translation translation);

    List<Translation> getHistory(int newerThan, boolean onlyFavorites);

    void wipeHistory();
}
