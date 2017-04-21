package com.voltek.yandexmobilization.data.entity;

import java.util.List;

public class Translation {

    private String langs;

    private String fromText;

    private List<String> toText;

    public Translation(String langs, String fromText, List<String> toText) {
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

    public List<String> getToText() {
        return toText;
    }
}
