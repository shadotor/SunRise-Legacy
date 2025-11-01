package com.nightshadow.sunrisecreative.creative.world;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Конфигурация мира
 */
public abstract class WorldConfig {
    public static File getFile(int ID, String category) {
        return new File(Sunrise_Creative.getInstance().getDataFolder(), "worlds/world_"+ID+"/"+category+".yml");
    }
    public static File getFile(World world, String category) {
        return new File(Sunrise_Creative.getInstance().getDataFolder(), "worlds/world_"+world.getID()+"/"+category+".yml");
    }
    public static YamlConfiguration getConfig(World world, String category) {
        File directory = new File(Sunrise_Creative.getInstance().getDataFolder(), "worlds/world_"+world.getID()+"/");
        return YamlConfiguration.loadConfiguration(new File(directory, category+".yml"));
    }

    /**
     * Getting value of field in config.
     * @param world
     * @param category
     * @param key
     * @return Object value
     */
    public static Object getKey(World world, String category, String key) {
        YamlConfiguration config = getConfig(world,category);
        return config.get(key, null);
    }
    public static Object getKey(World world, String category, String key, Object def) {
        YamlConfiguration config = getConfig(world,category);
        return config.get(key, def);
    }

    /** Set value in config
     *
     * @param world
     * @param category
     * @param key
     * @param value
     */
    public static void setKey(World world, String category, String key, Object value) {
        YamlConfiguration config = getConfig(world,category);
        config.set(key,value);
        try {
            config.save(getFile(world, category));
        } catch (Exception e) {
            Sunrise_Creative.sendConsole("ERROR DURING SAVE DATA WORLD WITH ID" + world.getID() + "CATEGORY" + category );
            e.printStackTrace();
        }
    }

    /**
     * Saves config of world
     * @param world
     * @param category
     * @param config
     */
    public static void saveConfig(World world, String category, YamlConfiguration config) {
        try {
            config.save(getFile(world, category));
        } catch (Exception e) {
            Sunrise_Creative.sendConsole("ERROR DURING SAVE DATA WORLD WITH ID" + world.getID() + "CATEGORY" + category );
            e.printStackTrace();
        }
    }

    /**
     * Create config of world
     * @param world
     */
    public static void createConfig(World world) {
        getFile(world, "data");
        getFile(world, "builder");
        getFile(world, "developer");
        getFile(world, "whitelist");
        getFile(world,"admin" );
        getFile(world,"blacklist");
    }
}
