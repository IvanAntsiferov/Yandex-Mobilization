package com.voltek.yandex.mobilization.data;

import com.voltek.yandex.mobilization.entity.data.Language;
import com.voltek.yandex.mobilization.entity.general.Translation;

import java.util.List;

import io.reactivex.Observable;

/**
 * Set of interfaces, that provides access to data.
 */
public final class DataProvider {

    private DataProvider() {}

    public interface Languages {

        /**
         * @return list of language name-code pairs.
         */
        List<Language> get();

        /**
         * @return lang code by provided index, e.g. "en", "de", "fr"
         */
        String getLangCodeByIndex(int index);
    }

    public interface UserData {

        /**
         * Put key-value pair to data storage.
         * @param key unique string id
         * @param value object of any type
         */
        void putValue(String key, Object value);

        /**
         * Get a value, that matches requested key.
         * @param key unique string id
         * @param defaultValue if value does not exist, default value will be saved and returned
         * @return value of type, that matches unique od (first param)
         */
        Object getValue(String key, Object defaultValue);
    }

    public interface Translations {

        /**
         * Request to server API
         * @param text string, that need to be translated
         * @param langs from-to languages in format "en-ru"
         */
        Observable<Translation> translateApiRequest(String text, String langs);

        /**
         * Search, if translation already exists in cache
         * by its text and translation direction languages.
         */
        Translation searchTranslationInCache(String text, String langs);

        /**
         * Adds translation, passed as argument, to cache.
         * Before caching checks if it already exists in DB.
         */
        void addTranslationToCache(Translation translation);

        /**
         * Updates currently existing translation by its unique id
         */
        void updateTranslationInCache(Translation translation);

        /**
         * @param newerThanId filters cache with ids only created after this argument
         *                    if -1 passed, this filter get ignored.
         */
        List<Translation> getCache(int newerThanId);

        /**
         * Remove all cached translations.
         */
        void wipeCache();
    }
}
