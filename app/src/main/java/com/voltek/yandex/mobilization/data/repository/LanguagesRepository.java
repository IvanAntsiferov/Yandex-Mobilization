package com.voltek.yandex.mobilization.data.repository;

import android.support.annotation.Nullable;

import com.voltek.yandex.mobilization.data.DataProvider;
import com.voltek.yandex.mobilization.entity.data.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguagesRepository implements DataProvider.Languages {

    @Override
    public List<Language> get() {
        return hardcodedLanguages();
    }

    @Override
    public String getLangCodeByIndex(int index) throws IndexOutOfBoundsException {
        return hardcodedLanguages().get(index).getCode();
    }

    @Override
    @Nullable
    public String getLangNameByCode(String code) {
        for (Language language : hardcodedLanguages()) {
            if (language.getCode().equals(code)) {
                return language.getName();
            }
        }
        return null;
    }

    private List<Language> hardcodedLanguages() {
        List<Language> languages = new ArrayList<>();

        languages.add(new Language("азербайджанский", "az"));
        languages.add(new Language("албанский", "sq"));
        languages.add(new Language("амхарский", "am"));
        languages.add(new Language("английский", "en"));
        languages.add(new Language("арабский", "ar"));
        languages.add(new Language("армянский", "hy"));
        languages.add(new Language("африкаанс", "af"));
        languages.add(new Language("баскский", "eu"));
        languages.add(new Language("башкирский", "ba"));
        languages.add(new Language("белорусский", "be"));
        languages.add(new Language("бенгальский", "bn"));
        languages.add(new Language("болгарский", "bg"));
        languages.add(new Language("боснийский", "bs"));
        languages.add(new Language("валлийский", "cy"));
        languages.add(new Language("венгерский", "hu"));
        languages.add(new Language("вьетнамский", "vi"));
        languages.add(new Language("гаитянский (креольский)", "ht"));
        languages.add(new Language("галисийский", "gl"));
        languages.add(new Language("голландский", "nl"));
        languages.add(new Language("горномарийский", "mrj"));
        languages.add(new Language("греческий", "el"));
        languages.add(new Language("грузинский", "ka"));
        languages.add(new Language("гуджарати", "gu"));
        languages.add(new Language("датский", "da"));
        languages.add(new Language("иврит", "he"));
        languages.add(new Language("идиш", "yi"));
        languages.add(new Language("индонезийский", "id"));
        languages.add(new Language("ирландский", "ga"));
        languages.add(new Language("итальянский", "it"));
        languages.add(new Language("исландский", "is"));
        languages.add(new Language("испанский", "es"));
        languages.add(new Language("казахский", "kk"));
        languages.add(new Language("каннада", "kn"));
        languages.add(new Language("каталанский", "ca"));
        languages.add(new Language("киргизский", "ky"));
        languages.add(new Language("китайский", "zh"));
        languages.add(new Language("корейский", "ko"));
        languages.add(new Language("коса", "xh"));
        languages.add(new Language("латынь", "la"));
        languages.add(new Language("латышский", "lv"));
        languages.add(new Language("литовский", "lt"));
        languages.add(new Language("люксембургский", "lb"));
        languages.add(new Language("малагасийский", "mg"));
        languages.add(new Language("малайский", "ms"));
        languages.add(new Language("малаялам", "ml"));
        languages.add(new Language("мальтийский", "mt"));
        languages.add(new Language("македонский", "mk"));
        languages.add(new Language("маори", "mi"));
        languages.add(new Language("маратхи", "mr"));
        languages.add(new Language("марийский", "mhr"));
        languages.add(new Language("монгольский", "mn"));
        languages.add(new Language("немецкий", "de"));
        languages.add(new Language("непальский", "ne"));
        languages.add(new Language("норвежский", "no"));
        languages.add(new Language("панджаби", "pa"));
        languages.add(new Language("папьяменто", "pap"));
        languages.add(new Language("персидский", "fa"));
        languages.add(new Language("польский", "pl"));
        languages.add(new Language("португальский", "pt"));
        languages.add(new Language("румынский", "ro"));
        languages.add(new Language("русский", "ru"));
        languages.add(new Language("себуанский", "ceb"));
        languages.add(new Language("сербский", "sr"));
        languages.add(new Language("сингальский", "si"));
        languages.add(new Language("словацкий", "sk"));
        languages.add(new Language("словенский", "sl"));
        languages.add(new Language("суахили", "sw"));
        languages.add(new Language("сунданский", "su"));
        languages.add(new Language("таджикский", "tg"));
        languages.add(new Language("тайский", "th"));
        languages.add(new Language("тагальский", "tl"));
        languages.add(new Language("тамильский", "ta"));
        languages.add(new Language("татарский", "tt"));
        languages.add(new Language("телугу", "te"));
        languages.add(new Language("турецкий", "tr"));
        languages.add(new Language("удмуртский", "udm"));
        languages.add(new Language("узбекский", "uz"));
        languages.add(new Language("украинский", "uk"));
        languages.add(new Language("урду", "ur"));
        languages.add(new Language("финский", "fi"));
        languages.add(new Language("французский", "fr"));
        languages.add(new Language("хинди", "hi"));
        languages.add(new Language("хорватский", "hr"));
        languages.add(new Language("чешский", "cs"));
        languages.add(new Language("шведский", "sv"));
        languages.add(new Language("шотландский", "gd"));
        languages.add(new Language("эстонский", "et"));
        languages.add(new Language("эсперанто", "eo"));
        languages.add(new Language("яванский", "jv"));
        languages.add(new Language("японский", "ja"));

        return languages;
    }
}
