package com.voltek.yandex.mobilization.entity.presentation;

/**
 * Used in {@link com.voltek.yandex.mobilization.ui.translator.TranslatorPresenter}
 * on view attach to set correct languages.
 * Stored in {@link com.voltek.yandex.mobilization.data.repository.UserDataRepository}
 */
public class SelectedLanguages {

    private int from;

    private int to;

    public SelectedLanguages(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }
}
