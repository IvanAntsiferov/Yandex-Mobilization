package com.voltek.yandexmobilization.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.navigation.command.CommandReplaceFragment;
import com.voltek.yandexmobilization.navigation.proxy.Navigator;
import com.voltek.yandexmobilization.navigation.proxy.NavigatorCommand;
import com.voltek.yandexmobilization.ui.favorites.FavoritesFragment;
import com.voltek.yandexmobilization.ui.history.HistoryFragment;
import com.voltek.yandexmobilization.ui.translator.TranslatorFragment;

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
        if (command instanceof CommandReplaceFragment) {
            CommandReplaceFragment c = (CommandReplaceFragment) command;
            replaceFragment(c.getFragmentIndex());
            return true;
        } else {
            return false;
        }
    }

    private void replaceFragment(int index) {
        Fragment fragment;

        if (index == 1) {
            fragment = new FavoritesFragment();
        } else if (index == 2) {
            fragment = new HistoryFragment();
        } else {
            fragment = new TranslatorFragment();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
