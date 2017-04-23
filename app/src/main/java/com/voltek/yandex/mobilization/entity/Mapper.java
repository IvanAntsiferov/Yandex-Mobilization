package com.voltek.yandex.mobilization.entity;

import com.voltek.yandex.mobilization.entity.data.Language;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Static class, that handles data conversion.
 */
public final class Mapper {

    private Mapper() {}

    // Convert language list to list of language names strings
    public static List<String> extractLangsNames(List<Language> langs) {
        List<String> langsNames = new ArrayList<>();

        for (Language language : langs) {
            langsNames.add(language.getName());
        }

        return langsNames;
    }

    /**
     * Translation direction in format "fr-de".
     * Used for API request.
     */
    public static String makeTranslationDirectionString(String from, String to) {
        return from + "-" + to;
    }

    public static String stringUrlEncode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Timber.e(e);
            return null;
        }
    }
}
