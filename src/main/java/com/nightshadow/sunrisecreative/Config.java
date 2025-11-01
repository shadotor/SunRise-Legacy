package com.nightshadow.sunrisecreative;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;

@Deprecated(forRemoval = false,since = "0.2")
@ApiStatus.Experimental
public class Config {
    static File configFile;
    static YamlConfiguration config;
    public static Object getConfig(String field) {
        return config.get(field, null);
    }
    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public static void createConfig() {
        configFile = new File(Sunrise_Creative.getInstance().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
                Sunrise_Creative.getInstance().saveResource("config.yml", false);
        }
        reloadConfig();
    }
}
