package com.voltek.yandex.mobilization.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.navigation.command.CommandReplaceFragment;
import com.voltek.yandex.mobilization.navigation.proxy.Navigator;
import com.voltek.yandex.mobilization.navigation.proxy.NavigatorCommand;
import com.voltek.yandex.mobilization.ui.history.HistoryFragment;
import com.voltek.yandex.mobilization.ui.translator.TranslatorFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends MvpAppCompatActivity implements MainView, Navigator {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "main")
    MainPresenter mPresenter;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNav;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    // Lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

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

    // View interface related
    @Override
    public void attachInputListeners() {
        Timber.d("attachInputListeners");
        Disposable bottomNavigation = RxBottomNavigationView.itemSelections(mBottomNav)
                .skip(1) // Skipping first emitted element because of auto select of position 0
                .subscribe(menuItem -> mPresenter.bottomNavigationClick(menuItem), Timber::e);

        mDisposable.addAll(bottomNavigation);
    }

    @Override
    public void detachInputListeners() {
        Timber.d("detachInputListeners");
        mDisposable.clear();
    }

    @Override
    public void selectFragmentId(int id) {
        Timber.d("selectFragmentAtIndex " + id);
        mBottomNav.setSelectedItemId(id);
    }

    // App navigation
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
        Timber.d("replaceFragment, index: " + index);
        Fragment fragment;
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();

        if (index == 1) {
            fts.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragment = new HistoryFragment();
        } else {
            fts.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            fragment = new TranslatorFragment();
        }

        fts.replace(R.id.fragment_container, fragment).commit();
    }
}
