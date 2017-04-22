package com.voltek.yandexmobilization.ui.translator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voltek.yandexmobilization.R;

import butterknife.ButterKnife;

public class TranslationResultDialog extends DialogFragment {

    private static final String ARG_CONTENT = "ARG_CONTENT";

    public TranslationResultDialog() {}

    public static TranslationResultDialog newInstance(String content) {
        TranslationResultDialog frag = new TranslationResultDialog();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_traslation_result, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String content = getArguments().getString(ARG_CONTENT);
        TextView contentView = ButterKnife.findById(view, R.id.tv_result);
        contentView.setText(content);
    }
}
