package com.voltek.yandexmobilization.data.model;

public class Language {

    private String code;

    private String name;

    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
