package com.nightshadow.sunrisecreative.api.utils;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.nightshadow.sunrisecreative.api.utils.Language.findLang;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

/**
 * Необходимо переделывать.
 */
public class LangUtils {
    static File localizationFolder;
    final static File playerLangs = new File(Sunrise_Creative.getInstance().getDataFolder(), "langs.yml");

    @ApiStatus.Experimental
    public static String localizeOnly(String key, Language lang) {
        createLocales();
        return YamlConfiguration.loadConfiguration(lang.getLocalizationFile()).get(key, key).toString();
    }
    /**
     * Получает локализованный текст в виде компонента
     * @param key ключ перевода
     * @param lang язык (String-язык устарел, используйте класс Language)
     * @return текстовый компонент
     */
    public static Component getLocale(String key, Language lang) {
        createLocales();
        return parseColor(YamlConfiguration.loadConfiguration(lang.getLocalizationFile()).get(key, key).toString());
    }
    public static Language getPlayerLang(Player player) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(playerLangs);
        return findLang(config.get(player.getName(), "en").toString());
    }
    public static void createLocales() {
        if (localizationFolder != null) return;
        localizationFolder = new File(Sunrise_Creative.getInstance().getDataFolder(), "locales");
        if (!localizationFolder.exists() && localizationFolder.mkdirs()) {
            localizationFolder = new File(Sunrise_Creative.getInstance().getDataFolder(), "locales");
            for (String lang : new String[]{"en", "ru"})
                Sunrise_Creative.getInstance().saveResource("locales/" + lang + ".yml", false);
        }
    }
    public static void reloadLocales() {
        localizationFolder = new File(Sunrise_Creative.getInstance().getDataFolder(), "locales");
        if (!localizationFolder.exists() && localizationFolder.mkdirs()) {
            localizationFolder = new File(Sunrise_Creative.getInstance().getDataFolder(), "locales");
            for (String lang : new String[]{"en", "ru"})
                Sunrise_Creative.getInstance().saveResource("locales/" + lang + ".yml", false);
        }
    }
    public static void setPlayerLang(Player player, String lang) throws Exception {
        FileConfiguration config = YamlConfiguration.loadConfiguration(playerLangs);
        if (getAvailableLangs().contains(lang+".yml")) {
            config.set(player.getName(),lang);
            config.save(playerLangs);
        }
        else throw new Exception("Not found"+lang+".yml");
    }
    public static List<String> getAvailableLangs() {
        Plugin plugin = Sunrise_Creative.getInstance();
        File directory = new File(plugin.getDataFolder(), "locales");
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(directory.list())));
    }
}
