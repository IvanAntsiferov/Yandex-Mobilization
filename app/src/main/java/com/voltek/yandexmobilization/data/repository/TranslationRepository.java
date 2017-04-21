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
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

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
                    String outText = response.body().text.get(0);
                    Translation translation =
                            new Translation(response.body().lang, text, outText, false);
                    emitter.onNext(translation);
                } else {
                    // Get error message corresponding to response code
                    String error = mContext.getString(R.string.error_request_failed);
                    if (response.code() == 401) {
                        error = mContext.getString(R.string.error_api_key_wrong);
                    } else if (response.code() == 402) {
                        error = mContext.getString(R.string.error_api_key_blocked);
                    } else if (response.code() == 404) {
                        error = mContext.getString(R.string.error_request_limit_exceeded);
                    } else if (response.code() == 413) {
                        error = mContext.getString(R.string.error_text_limit_exceeded);
                    } else if (response.code() == 422) {
                        error = mContext.getString(R.string.error_cant_translate);
                    } else if (response.code() == 501) {
                        error = mContext.getString(R.string.error_unsupported_translation_direction);
                    }
                    emitter.onError(new Exception(error));
                }
            } else {
                emitter.onError(new Exception(mContext.getString(R.string.error_no_connection)));
            }
            emitter.onComplete();
        });
    }

    @Override
    public Translation searchTranslationInCache(String text, String langs) {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Translation> results = realm.where(Translation.class)
                .equalTo("langs", langs)
                .equalTo("fromText", text)
                .findAll();

        if (results.size() > 0) {
            Translation translation = realm.copyFromRealm(results).get(0);
            realm.close();
            return translation;
        } else {
            realm.close();
            return null;
        }
    }

    @Override
    public void addTranslationToCache(Translation translation) {
        Realm realm = Realm.getDefaultInstance();
        // Check, if same translation already exists in cache
        RealmResults<Translation> results = realm.where(Translation.class)
                .equalTo("langs", translation.getLangs())
                .equalTo("fromText", translation.getFromText())
                .equalTo("toText", translation.getToText())
                .findAll();
        // If not, add to cache
        if (results.isEmpty()) {
            realm.beginTransaction();
            realm.copyToRealm(translation);
            realm.commitTransaction();
        }
        realm.close();
    }
}
