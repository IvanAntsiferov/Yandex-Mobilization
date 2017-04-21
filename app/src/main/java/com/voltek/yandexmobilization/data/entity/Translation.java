package com.voltek.yandexmobilization.data.entity;

import io.realm.RealmObject;

public class Translation extends RealmObject {

    private String langs;

    private String fromText;

    private String toText;

    private Boolean favorite;

    public Translation() {}

    public Translation(String langs, String fromText, String toText, Boolean favorite) {
        this.langs = langs;
        this.fromText = fromText;
        this.toText = toText;
        this.favorite = favorite;
    }

    public String getLangs() {
        return langs;
    }

    public void setLangs(String langs) {
        this.langs = langs;
    }

    public String getFromText() {
        return fromText;
    }

    public void setFromText(String fromText) {
        this.fromText = fromText;
    }

    public String getToText() {
        return toText;
    }

    public void setToText(String toText) {
        this.toText = toText;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
