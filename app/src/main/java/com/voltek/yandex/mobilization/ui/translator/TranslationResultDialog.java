package com.voltek.yandex.mobilization.ui.translator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.ui.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslationResultDialog extends BaseDialogFragment {

    private static final String ARG_CONTENT = "ARG_CONTENT";

    @BindView(R.id.tv_result)
    TextView mTextViewResult;

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
        return inflater.inflate(R.layout.dialog_translation_result, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String content = getArguments().getString(ARG_CONTENT);
        mTextViewResult.setText(content);
    }
}
