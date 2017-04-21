package com.voltek.yandexmobilization.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.voltek.yandexmobilization.BuildConfig;
import com.voltek.yandexmobilization.R;
import com.voltek.yandexmobilization.TranslatorApp;
import com.voltek.yandexmobilization.data.DataProvider;
import com.voltek.yandexmobilization.data.entity.Translation;
import com.voltek.yandexmobilization.networking.YandexTranslateAPI;
import com.voltek.yandexmobilization.networking.entity.TranslateResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
import timber.log.Timber;

public class TranslationRepository implements DataProvider.Translations {

    @Inject
    Context mContext;

    @Inject
    YandexTranslateAPI mApi;

    public TranslationRepository() {
        TranslatorApp.getNetworkComponent().inject(this);
    }

    @Override
    public Observable<Translation> translateApiRequest(String text, String langs) {
        return Observable.create(emitter -> {
            ConnectivityManager cm =
                    (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                Response<TranslateResponse> response =
                        mApi.translate(BuildConfig.API_KEY, text, langs).execute();

                if (response.isSuccessful()) {
                    Translation translation =
                            new Translation(response.body().lang, text, response.body().text);
                    Timber.d(response.raw().request().url().toString());
                    emitter.onNext(translation);
                } else {
                    // TODO обрабатывать все коды ошибок 401, 402, 403, 404, 413, 422, 501
                    Timber.e(response.message());
                    Timber.e(response.toString());
                    Timber.e(response.code() + "");
                    Timber.e(response.raw().request().url().toString());
                    Timber.e(response.errorBody().string());
                    emitter.onError(
                            new Exception(mContext.getString(R.string.error_request_failed)));
                }
            } else {
                emitter.onError(new Exception(mContext.getString(R.string.error_no_connection)));
            }
            emitter.onComplete();
        });
    }
}
