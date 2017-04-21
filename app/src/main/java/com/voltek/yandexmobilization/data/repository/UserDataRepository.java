package com.voltek.yandexmobilization.data.repository;

import com.orhanobut.hawk.Hawk;
import com.voltek.yandexmobilization.data.DataProvider;

public class UserDataRepository implements DataProvider.UserData {

    @Override
    public void putValue(String key, Object value) {
        Hawk.put(key, value);
    }

    @Override
    public Object getValue(String key, Object defaultValue) {
        if (!Hawk.contains(key))
            putValue(key, defaultValue);
        return Hawk.get(key);
    }
}
