package com.voltek.yandexmobilization.ui.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.navigation.proxy.Navigator;
import com.voltek.yandexmobilization.navigation.proxy.NavigatorCommand;
import com.voltek.yandexmobilization.networking.YandexTranslateAPI;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements Navigator {

    @Inject
    YandexTranslateAPI mApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        TranslatorApp.getNetworkComponent().inject(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TranslatorApp.getRouterBinder().setNavigator(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TranslatorApp.getRouterBinder().removeNavigator();
    }

    @Override
    public boolean executeCommand(NavigatorCommand command) {
        // TODO implement commands realization
        return false;
    }
}
