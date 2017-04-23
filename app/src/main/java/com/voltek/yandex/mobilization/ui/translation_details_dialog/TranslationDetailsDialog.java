package com.voltek.yandex.mobilization.ui.translation_details_dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.ui.BaseDialogFragment;

import butterknife.BindView;

public class TranslationDetailsDialog extends BaseDialogFragment implements TranslationDetailsDialogView {

    private final static String ARG_FROM_TEXT = "ARG_FROM_TEXT";
    private final static String ARG_TO_TEXT = "ARG_TO_TEXT";
    private final static String ARG_FROM_LANG = "ARG_FROM_LANG";
    private final static String ARG_TO_LANG = "ARG_TO_LANG";

    @InjectPresenter(type = PresenterType.LOCAL, tag = "TranslationDetailsDialog")
    TranslationDetailsDialogPresenter mPresenter;

    @BindView(R.id.tv_from)
    TextView mTvFromText;
    @BindView(R.id.tv_to)
    TextView mTvToText;
    @BindView(R.id.tv_lang_from)
    TextView mTvLangFrom;
    @BindView(R.id.tv_lang_to)
    TextView mTvLangTo;

    public TranslationDetailsDialog() {}

    public static TranslationDetailsDialog newInstance(
            String fromText, String toText, String fromLang, String toLang) {

        TranslationDetailsDialog fragment = new TranslationDetailsDialog();
        Bundle args = new Bundle();
        args.putString(ARG_FROM_TEXT, fromText);
        args.putString(ARG_TO_TEXT, toText);
        args.putString(ARG_FROM_LANG, fromLang);
        args.putString(ARG_TO_LANG, toLang);
        fragment.setArguments(args);
        return fragment;
    }

    // Lifecycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_translation_details, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String fromText = getArguments().getString(ARG_FROM_TEXT);
        String toText = getArguments().getString(ARG_TO_TEXT);
        String fromLang = getArguments().getString(ARG_FROM_LANG);
        String toLang = getArguments().getString(ARG_TO_LANG);
        mTvFromText.setText(fromText);
        mTvToText.setText(toText);
        mTvLangFrom.setText(fromLang);
        mTvLangTo.setText(toLang);
    }

    // View interface
    @Override
    public void attachInputListeners() {}

    @Override
    public void detachInputListeners() {}
}
