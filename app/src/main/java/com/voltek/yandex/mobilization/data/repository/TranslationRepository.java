package com.voltek.yandex.mobilization.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.voltek.yandex.mobilization.BuildConfig;
import com.voltek.yandex.mobilization.R;
import com.voltek.yandex.mobilization.TranslatorApp;
import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.entity.general.Translation;
import com.voltek.yandex.mobilization.data.networking.YandexTranslateAPI;
import com.voltek.yandex.mobilization.entity.network.TranslateResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
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
                    Timber.d("translateApiRequest: " + response.body().text.toString());
                    String outText = response.body().text.get(0);
                    Translation translation =
                            new Translation(-1, response.body().lang, text, outText, false);
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
        Translation results = realm.where(Translation.class)
                // If it was loaded from cache, it has same id
                .equalTo("id", translation.getId())
                .or()
                // If not, may have same parameters
                .equalTo("langs", translation.getLangs())
                .equalTo("fromText", translation.getFromText())
                .equalTo("toText", translation.getToText())
                .findFirst();
        // If not, add to cache
        if (results == null) {
            realm.beginTransaction();

            int nextID;
            try {
                // Incrementing primary key manually
                nextID = realm.where(Translation.class).max("id").intValue() + 1;
            } catch (NullPointerException e) {
                // If there is first item, being added to cache, give it id = 0
                nextID = 0;
            }
            Timber.d("addTranslationToCache, with id " + nextID);

            translation.setId(nextID);
            realm.copyToRealm(translation);
            realm.commitTransaction();
        }
        realm.close();
    }

    @Override
    public void updateTranslationInCache(Translation translation) {
        Realm realm = Realm.getDefaultInstance();

        Translation cached = realm.where(Translation.class)
                // If it was loaded from cache, it has same id
                .equalTo("id", translation.getId())
                .or()
                // If not, may have same parameters
                .equalTo("langs", translation.getLangs())
                .equalTo("fromText", translation.getFromText())
                .equalTo("toText", translation.getToText())
                .findFirst();

        if (cached != null) {
            realm.beginTransaction();
            cached.setFavorite(translation.getFavorite());
            realm.commitTransaction();
            Timber.d("updateTranslationInCache, with id " + translation.getId() + " set favorite to " + translation.getFavorite());
        } else {
            Timber.e("Update failed: Translation was not found in Realm");
        }
        realm.close();
    }

    @Override
    public List<Translation> getCache(int newerThanId) {
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Translation> query = realm
                .where(Translation.class);

        if (newerThanId >= 0) {
            query.greaterThan("id", newerThanId);
        }

        RealmResults<Translation> results = query.findAll();
        results = results.sort("id", Sort.DESCENDING);
        List<Translation> cache = realm.copyFromRealm(results);

        Timber.d("getCache, size: " + cache.size());

        realm.close();
        return cache;
    }

    @Override
    public void wipeCache() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }
}
