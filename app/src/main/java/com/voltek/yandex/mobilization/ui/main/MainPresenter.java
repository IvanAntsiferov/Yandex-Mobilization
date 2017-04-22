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

    private int mCurrentFragmentIndex = 0;
    private int mCurrentFragmentId = -1;

    public MainPresenter() {
        mRouter = TranslatorApp.getRouterBus();
    }

    // View lifecycle
    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        Timber.d("attachView");
        getViewState().attachInputListeners();
        if (mCurrentFragmentId > 0) {
            getViewState().selectFragmentId(mCurrentFragmentId);
        }
        mRouter.execute(new CommandReplaceFragment(mCurrentFragmentIndex));
    }

    @Override
    public void detachView(MainView view) {
        super.detachView(view);
        Timber.d("detachView");
        getViewState().detachInputListeners();
    }

    // View notifications
    public void bottomNavigationClick(MenuItem menuItem) {
        mCurrentFragmentIndex = menuItem.getOrder();
        mCurrentFragmentId = menuItem.getItemId();
        mRouter.execute(new CommandReplaceFragment(mCurrentFragmentIndex));
        Timber.d("bottomNavigationClick, index: " + mCurrentFragmentIndex);
        Timber.d("bottomNavigationClick, id" + menuItem.getItemId());
    }

    // Private logic
}
