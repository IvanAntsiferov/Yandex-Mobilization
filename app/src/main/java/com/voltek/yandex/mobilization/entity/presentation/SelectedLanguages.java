package com.voltek.yandex.mobilization.entity.presentation;

/**
 * Used in translator presenter on view attach to set correct languages.
 * Stored in user data repo.
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
