package com.voltek.yandex.mobilization.ui.main;

import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.navigation.command.CommandReplaceFragment;
import com.voltek.yandex.mobilization.navigation.proxy.RouterBus;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private RouterBus mRouter;

    private int mCurrentFragmentIndex = 0;
    private int mCurrentFragmentId = -1;

    private int mPreviousFragmentIndex = 0;

    public MainPresenter() {
        mRouter = TranslatorApp.getRouterBus();
    }

    // View lifecycle
    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        getViewState().attachInputListeners();
        if (mCurrentFragmentId > 0) {
            getViewState().selectFragmentId(mCurrentFragmentId);
        }
        mRouter.execute(new CommandReplaceFragment(mCurrentFragmentIndex, mPreviousFragmentIndex));
    }

    @Override
    public void detachView(MainView view) {
        super.detachView(view);
        getViewState().detachInputListeners();
    }

    // View notifications
    public void bottomNavigationClick(MenuItem menuItem) {
        mPreviousFragmentIndex = mCurrentFragmentIndex;
        mCurrentFragmentIndex = menuItem.getOrder();
        mCurrentFragmentId = menuItem.getItemId();
        mRouter.execute(new CommandReplaceFragment(mCurrentFragmentIndex, mPreviousFragmentIndex));
    }

    // Private logic
}
