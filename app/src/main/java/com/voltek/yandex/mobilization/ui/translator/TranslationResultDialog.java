package com.voltek.yandex.mobilization.ui.translator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voltek.yandex.mobilization.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TranslationResultDialog extends DialogFragment {

    private static final String ARG_CONTENT = "ARG_CONTENT";

    private Unbinder mUnbinder;

    public TranslationResultDialog() {}

    public static TranslationResultDialog newInstance(String content) {
        TranslationResultDialog fragment = new TranslationResultDialog();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
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
        String content = getArguments().getString(ARG_CONTENT);
        TextView contentView = ButterKnife.findById(view, R.id.tv_result);
        contentView.setText(content);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
