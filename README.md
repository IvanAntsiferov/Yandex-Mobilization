# Переводчик
Тестовое задание для проекта Яндекс «Мобилизация» 2017. Использует сервис [Яндекс.Переводчик](https://translate.yandex.ru/).

- Поддерживает 90 языков
- Сохраняет историю переводов
- Переводы можно добавлять в закладки
- В истории можно выполнять поиск по тексту
- Работает в портретной и альбомной ориентации

Скачать .apk можно [в списке релизов](https://github.com/IvanAntsiferov/Yandex-Mobilization/releases).

## Скриншоты
![Screenshot](https://raw.githubusercontent.com/IvanAntsiferov/Yandex-Mobilization/master/docs/images/Screenshot1.png)
![Screenshot](https://raw.githubusercontent.com/IvanAntsiferov/Yandex-Mobilization/master/docs/images/Screenshot2.png)
![Screenshot](https://raw.githubusercontent.com/IvanAntsiferov/Yandex-Mobilization/master/docs/images/Screenshot3.png)

## Сборка проекта
Чтобы собрать проект, нужно получить [API-ключ Яндекс переводчика](https://tech.yandex.ru/translate/) и записать его в ```gradle.properties``` проекта, как переменную ```YandexTranslateApiKey```.

## Используемые библиотеки

* [Dagger 2](https://github.com/google/dagger)
* [Moxy](https://github.com/Arello-Mobile/Moxy)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [RxBinding](https://github.com/JakeWharton/RxBinding)
* [Realm](https://github.com/realm/realm-java)
* [Retrofit](https://github.com/square/retrofit)
* [Timber](https://github.com/JakeWharton/timber)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Retrolambda](https://github.com/evant/gradle-retrolambda)
* [Calligraphy](https://github.com/chrisjenx/Calligraphy)
* [Hawk](https://github.com/orhanobut/hawk)
