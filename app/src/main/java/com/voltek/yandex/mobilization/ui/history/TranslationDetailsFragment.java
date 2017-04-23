package com.voltek.yandex.mobilization.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voltek.yandex.mobilization.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TranslationDetailsFragment extends DialogFragment {

    private final static String ARG_FROM = "ARG_FROM";
    private final static String ARG_TO = "ARG_TO";
    private final static String ARG_DIRECTION = "ARG_DIRECTION";

    private Unbinder mUnbinder;

    public TranslationDetailsFragment() {}

    public static TranslationDetailsFragment newInstance(String from, String to, String direction) {
        TranslationDetailsFragment fragment = new TranslationDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FROM, from);
        args.putString(ARG_TO, to);
        args.putString(ARG_DIRECTION, direction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_traslation_result, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);
        String from = getArguments().getString(ARG_FROM);
        String to = getArguments().getString(ARG_TO);
        String direction = getArguments().getString(ARG_DIRECTION);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
