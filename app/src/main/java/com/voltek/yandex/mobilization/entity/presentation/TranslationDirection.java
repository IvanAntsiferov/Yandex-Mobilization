package com.voltek.yandex.mobilization.entity.presentation;

public class TranslationDirection {

    private String fromLang;

    private String toLang;

    public TranslationDirection(String fromLang, String toLang) {
        this.fromLang = fromLang;
        this.toLang = toLang;
    }

    public String getFromLang() {
        return fromLang;
    }

    public String getToLang() {
        return toLang;
    }
}
