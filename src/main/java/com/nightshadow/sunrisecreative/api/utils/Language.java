package com.nightshadow.sunrisecreative.api.utils;

import java.io.File;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;

public enum Language {
    RU("ru"),EN("en"),UA("ua");
    final File file;
    Language(String name) {
        this.file = new File(getInstance().getDataFolder(), "locales/"+name+".yml");
    }
    public static Language findLang(String lang) {
        return Language.valueOf(lang.toUpperCase());
    }
    public File getLocalizationFile() {
        return file;
    }
}
