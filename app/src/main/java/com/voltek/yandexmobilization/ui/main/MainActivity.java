package com.voltek.yandexmobilization.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView;
import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.navigation.command.CommandReplaceFragment;
import com.voltek.yandexmobilization.navigation.proxy.Navigator;
import com.voltek.yandexmobilization.navigation.proxy.NavigatorCommand;
import com.voltek.yandexmobilization.ui.favorites.FavoritesFragment;
import com.voltek.yandexmobilization.ui.history.HistoryFragment;
import com.voltek.yandexmobilization.ui.translator.TranslatorFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView, Navigator {

    @InjectPresenter(type = PresenterType.GLOBAL)
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
        TranslatorApp.getNetworkComponent().inject(this);
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
