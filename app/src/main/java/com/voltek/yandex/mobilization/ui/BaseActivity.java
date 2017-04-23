package com.voltek.yandex.mobilization.ui;

import android.content.Context;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.navigation.proxy.Navigator;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends MvpAppCompatActivity implements Navigator {

    protected CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");
        TranslatorApp.getRouterBinder().setNavigator(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause");
        TranslatorApp.getRouterBinder().removeNavigator();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
