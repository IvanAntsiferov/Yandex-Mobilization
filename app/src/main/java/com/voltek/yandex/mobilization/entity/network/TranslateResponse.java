package com.voltek.yandex.mobilization.entity.network;

import java.util.List;

/**
 * Yandex translator api response converted from Json.
 *
 * Example:
 * {
 *     "code": 200,
 *     "lang": "en-ru",
 *     "text": [
 *         "Здравствуй, Мир!"
 *     ]
 * }
 */
public class TranslateResponse {

    public String code;

    public String lang;

    public List<String> text;
}
