package com.voltek.yandexmobilization.networking;

import com.voltek.yandexmobilization.networking.entity.TranslateResponse;

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
