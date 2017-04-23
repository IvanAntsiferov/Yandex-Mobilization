package com.voltek.yandex.mobilization;

import com.voltek.yandex.mobilization.data.repository.LanguagesRepository;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class LanguagesRepositoryTest {

    private LanguagesRepository repo = new LanguagesRepository();

    @Test
    public void testRepository() {
        Assert.assertEquals("русский", repo.getLangNameByCode("ru"));
        Assert.assertEquals("английский", repo.getLangNameByCode("en"));
        Assert.assertEquals("болгарский", repo.getLangNameByCode("bg"));
        assertNull(repo.getLangNameByCode("qwerty"));

        Assert.assertEquals("az", repo.getLangCodeByIndex(0));
        Assert.assertEquals("en", repo.getLangCodeByIndex(3));
        Assert.assertEquals("ja", repo.getLangCodeByIndex(89));

        Assert.assertEquals(false, repo.get().isEmpty());
        Assert.assertEquals(90, repo.get().size());
    }
}
