package com.voltek.yandexmobilization.data;

import com.voltek.yandexmobilization.data.entity.Language;
import com.voltek.yandexmobilization.data.entity.Translation;
import com.voltek.yandexmobilization.networking.entity.TranslateResponse;

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

        String makeTranslationDirectionStr(int from, int to);
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
    }
}
