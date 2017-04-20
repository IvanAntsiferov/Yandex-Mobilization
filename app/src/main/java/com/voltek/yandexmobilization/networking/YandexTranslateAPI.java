package com.voltek.yandexmobilization.networking;

import com.voltek.yandexmobilization.networking.entity.TranslateResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface YandexTranslateAPI {

    @POST("v1.5/tr.json/translate")
    Call<TranslateResponse> translate(
            @Query("key") String apiKey,
            @Query("lang") String langToLang, // В формате en-ru
            @Query("text") String text);
}
