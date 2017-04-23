package com.voltek.yandex.mobilization.interactor.translation;

import com.voltek.yandex.mobilization.entity.general.Translation;

import java.util.List;

import io.reactivex.Observable;

public interface TranslationUseCase {

    /**
     * Basic translation method.
     * @param text what to translate
     * @param fromId from which language (by id)
     * @param toId to what language (by id)
     */
    Observable<Translation> translate(String text, int fromId, int toId);

    /**
     * Update existing Translation by its id.
     */
    void updateFavorites(Translation translation);

    /**
     * Get all cached translations with a few filter options.
     * @param newerThan Return translations, created only after specified id.
     *                  Pass -1 to skip this filter.
     * @param onlyFavorites If true, return translations, only marked as favorite.
     *                      Is false pass as argument, all translations will be returned
     *                      regardless of whether they were added in favorites or not.
     * @param contains Return translations, that contains specified text in "from" or "to" fields.
     *                 Pass an empty string to skip this filter.
     */
    List<Translation> getHistory(int newerThan, boolean onlyFavorites, String contains);

    /**
     * Wipe all cached translations.
     */
    void wipeHistory();
}
