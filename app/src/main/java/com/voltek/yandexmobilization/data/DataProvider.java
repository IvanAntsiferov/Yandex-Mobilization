package com.voltek.yandexmobilization.data;

import com.voltek.yandexmobilization.data.entity.Language;

import java.util.List;

/**
 * Set of interfaces, that provides access to data.
 */
public final class DataProvider {

    private DataProvider() {}

    public interface languages {

        List<Language> get();
    }
}
