package com.voltek.yandex.mobilization.ui.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.voltek.yandex.mobilization.R;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int backColor = Color.parseColor("#6EBEC4");

        addSlide(AppIntroFragment.newInstance("Test1", "test1 desc", R.drawable.ic_translate_24dp, backColor));
        addSlide(AppIntroFragment.newInstance("Test2", "test2 desc", R.drawable.ic_history_24dp, backColor));
        addSlide(AppIntroFragment.newInstance("Test3", "test3 desc", R.drawable.ic_info_outline_24dp, backColor));

        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
