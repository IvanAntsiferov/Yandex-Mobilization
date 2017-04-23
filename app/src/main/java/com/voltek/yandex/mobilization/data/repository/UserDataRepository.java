package com.voltek.yandex.mobilization.data.repository;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.voltek.yandex.mobilization.data.DataProvider;

public class UserDataRepository implements DataProvider.UserData {

    @Override
    public void putValue(String key, @NonNull Object value) {
        Hawk.put(key, value);
    }

    @Override
    public Object getValue(String key, @NonNull Object defaultValue) {
        if (!Hawk.contains(key))
            putValue(key, defaultValue);
        return Hawk.get(key);
    }
}
