package com.voltek.yandexmobilization.data;

import com.voltek.yandexmobilization.data.model.Language;

import java.util.List;

public final class DataProvider {

    private DataProvider() {}

    public interface languages {

        List<Language> get();
    }
}
