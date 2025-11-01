package com.nightshadow.sunrisecreative.creative.world;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.world.WORLD_RUNNING_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.world.generator.VoidGenerator;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import net.kyori.adventure.util.TriState;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;
import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static java.lang.Integer.valueOf;

@Deprecated(forRemoval = false, since = "0.5")
public abstract class worldManager {
    public static List <World> worldList = new ArrayList<>();
    public static World spawn = new World("Spawn", -1);
    public static List <org.bukkit.World> worlds = new ArrayList<>();

    /**
     * Gets names of all worlds
     * @return array of Strings, where string is a name of world
     */
    public static String[] getWorldNames()
    {
        String[] worldNames;
        Plugin plugin = Sunrise_Creative.getInstance();
        File directory = new File(plugin.getDataFolder(), "worlds");
        if (directory.list() == null) return new String[0];
        else {
            worldNames = new String[Objects.requireNonNull(directory.list()).length];
            for (int i = 0; i < Objects.requireNonNull(directory.list()).length; i++) {
                File configFile = WorldConfig.getFile(i, "data");
                FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                worldNames[i] = config.get("world-name", "Not found").toString();
            }
            return worldNames;
        }
    }

    /**
     * Gets next free ID
     * @return next ID
     */
    public static int getNextID() {
        return getWorldNames().length;
    }

    /**
     * Gets array of all worlds
     * @return World[],
     */
    public static World[] getWorldList() {
        return worldList.toArray(new World[worldList.size()]);
    }

    /**
     *
     * @param name
     * @param ID
     * @return World with this name and ID
     */
    public static World getWorldByName(String name, int ID){
        Plugin plugin = Sunrise_Creative.getInstance();
        File configFile = WorldConfig.getFile(ID, "data");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        World world = new World(name,ID,config.get("author","Not Found").toString());
        return world;
    }

    /** World loading
     * Load all worlds in worlds directory.
     */
    public static void loadWorlds() {
        Plugin plugin = Sunrise_Creative.getInstance();
        spawn.world = Bukkit.getWorld("overworld");
        File directory = new File(plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile(), "worlds");
        if (Objects.requireNonNull(directory.listFiles()).length > 0) getWorld(directory);
    }

    /** World loading function
     *
     * @param folder
     */
    public static void getWorld(File folder) {
        for (int i = 0; i < Objects.requireNonNull(folder.listFiles()).length; i++) {
            File file = Objects.requireNonNull(folder.listFiles())[i];
            if (file.isDirectory()) {
                for(File file2 : Objects.requireNonNull(file.listFiles())) {
                    if (file2.getName().equals("level.dat")) {
                        int ID = valueOf(file.getName().replaceAll("world_",""));
                        WorldCreator worldCreator = new WorldCreator("worlds/"+file.getName());
                        World w = new World(getWorldNames()[i], ID);
                        String type = WorldConfig.getKey(w,"data","generator","void").toString();
                        if (type.equals("void")) worldCreator.generator(new VoidGenerator());
                        if (type.equals("void") || type.equals("flat")) worldCreator.type(WorldType.FLAT);
                        if (type.equals("survival")) worldCreator.type(WorldType.NORMAL);worldCreator.generator(new VoidGenerator());
                        worldCreator.keepSpawnLoaded(TriState.FALSE);
                        org.bukkit.World world = Bukkit.getServer().createWorld(worldCreator);
                        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES,false);
                        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
                        worlds.add(world);
                        w.world = world;
                        w.getWorld().setDifficulty(Difficulty.valueOf(WorldConfig.getKey(w,"data","difficulty", "EASY").toString()));
                        w.getAuthor();
                        new WorldVariableData(w).clearCash();
                        worldList.add(w);
                        if (w.getStatement() == WorldStatement.PLAY) new WORLD_RUNNING_EVENT().onExecute(w, new Selector());
                        sendConsole("SUNRISE LOADER |  Loaded world: " + file.getName() + " ID: " + ID);
                    }
                    }
                }
            }
        worldList.sort(new Comparator<World>() {
            @Override
            public int compare(World o1, World o2) {
                if (o1.getID() == o2.getID()) return 0;
                if (o1.getID() < o2.getID()) return -1;
                else return 1;
            }
        });
        }

    /**
     *  Gets world where is player
     *
      * @param p
     * @return World
     */
    public static World getWorldWhere(Player p) {
        for (int i = 0; i < getWorldList().length; i++) {
            if (worldList.get(i).getWorld() == p.getLocation().getWorld()) return getWorldList()[i];
        }
        return spawn;
    }
    public static World getWorldWhere(Entity e) {
        for (int i = 0; i < getWorldList().length; i++) {
            if (worldList.get(i).getWorld() == e.getLocation().getWorld()) return getWorldList()[i];
        }
        return spawn;
    }
    public static World getWorldWhereIncludingDevWorld(Player p) {
        for (int i = 0; i < getWorldList().length; i++) {
            if (worldList.get(i).getWorld() == p.getLocation().getWorld() || worldList.get(i).getCodingWorld().getWorld() == p.getLocation().getWorld() ) return getWorldList()[i];
        }
        return spawn;
    }
    public static World getWorldByLocation(Location loc) {
        for (int i = 0; i < getWorldList().length; i++) {
            if (worldList.get(i).getWorld() == loc.getWorld() || worldList.get(i).getCodingWorld().getWorld() == loc.getWorld() ) return getWorldList()[i];
        }
        return spawn;
    }
    public static World getWorld(org.bukkit.World w) {
        for (int i = 0; i < getWorldList().length; i++) {
            if (worldList.get(i).getWorld() == w|| worldList.get(i).getCodingWorld().getWorld() == w ) return getWorldList()[i];
        }
        return spawn;
    }
    public static List<World> getWorldsByPlayer(Player p) {
        List<World> wl = new ArrayList<>();
        for (World w : getWorldList()) {
            if (w.getAuthorName().equals(p.getName())) {
                wl.add(w);
            }
        }
        return wl;
    }
    public static int getPlayerWorldLimit(Player p) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(getInstance().getDataFolder(), "worldLimits.yml"));
        return config.getInt(p.getName(),3);
    }
    public static void setPlayerWorldLimit(Player p, int limit) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(getInstance().getDataFolder(), "worldLimits.yml"));
        config.set(p.getName(),limit);
        try {
            config.save(new File(getInstance().getDataFolder(), "worldLimits.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean inDevelopmentWorld(Player p) {
    return p.getWorld() == getWorldWhereIncludingDevWorld(p).getCodingWorld().getWorld();
    }
    public static World getWorldByID(int ID) {
        for (World world : worldList) {
            if (world.getID() == ID) {
                return world;
            }
        }
        return null;
    }
}
