package com.voltek.yandexmobilization.networking;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface YandexTranslateAPI {

    @POST("v1.5/tr.json/translate")
    Call<String> translate(
            @Query("key") String apiKey,
            @Query("text") String text,
            @Query("lang") String lang);
}
