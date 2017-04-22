package com.voltek.yandex.mobilization.data.networking;

import com.voltek.yandex.mobilization.entity.network.TranslateResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface YandexTranslateAPI {

    @POST("/api/v1.5/tr.json/translate")
    Call<TranslateResponse> translate(
            @Query("key") String apiKey,
            @Query("text") String text,
            @Query("lang") String langToLang);
}
