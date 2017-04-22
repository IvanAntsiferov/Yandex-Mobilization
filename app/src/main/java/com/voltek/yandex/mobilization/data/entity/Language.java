package com.voltek.yandex.mobilization.data.entity;

public class Language {

    private String name;

    private String code;

    public Language(String name, String code) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
