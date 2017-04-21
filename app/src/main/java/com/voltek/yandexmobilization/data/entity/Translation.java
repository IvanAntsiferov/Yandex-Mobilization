package com.voltek.yandexmobilization.data.entity;

import java.util.List;

public class Translation {

    private String langs;

    private String fromText;

    private String toText;

    public Translation(String langs, String fromText, String toText) {
        this.langs = langs;
        this.fromText = fromText;
        this.toText = toText;
    }

    public String getLangs() {
        return langs;
    }

    public String getFromText() {
        return fromText;
    }

    public String getToText() {
        return toText;
    }
}
