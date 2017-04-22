package com.voltek.yandex.mobilization.ui.main;

import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.navigation.command.CommandReplaceFragment;
import com.voltek.yandex.mobilization.navigation.proxy.RouterBus;

import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private RouterBus mRouter;

    public MainPresenter() {
        mRouter = TranslatorApp.getRouterBus();
    }

    // View lifecycle
    @Override
    protected void onFirstViewAttach() {
        mRouter.execute(new CommandReplaceFragment(0));
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        Timber.d("attachView");
        getViewState().attachInputListeners();
    }

    @Override
    public void detachView(MainView view) {
        super.detachView(view);
        Timber.d("detachView");
        getViewState().detachInputListeners();
    }

    // View notifications
    public void bottomNavigationClick(MenuItem menuItem) {
        Timber.d("bottomNavigationClick, index: " + menuItem.getOrder());
        mRouter.execute(new CommandReplaceFragment(menuItem.getOrder()));
    }

    // Private logic
}
