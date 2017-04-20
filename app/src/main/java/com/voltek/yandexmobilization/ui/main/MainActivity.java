package com.voltek.yandexmobilization.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.navigation.proxy.Navigator;
import com.voltek.yandexmobilization.navigation.proxy.NavigatorCommand;

public class MainActivity extends AppCompatActivity implements Navigator {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TranslatorApp.getNetworkComponent().inject(this);
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
