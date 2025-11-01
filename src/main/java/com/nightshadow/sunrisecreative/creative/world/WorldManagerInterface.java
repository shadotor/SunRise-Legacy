package com.nightshadow.sunrisecreative.creative.world;

import com.nightshadow.sunrisecreative.creative.world.generator.Generator;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public interface WorldManagerInterface {

    // Загрузчик миров
    void createWorld(String name, Generator generator, Player player);
    void deleteWorld(int ID);
    void deleteWorld(World world);
    void unloadWorld(World world);
    void loadWorld(World world);
    void initializeWorlds(File directory);

    // Получение миров
    String[] getWorldNames();
    World getWorldWhere(Player p);
    World getWorldWhereIncludingDevWorld(Player p);
    World getWorldByLocation(Location loc);
    List<World> getWorldsByPlayer(Player p);
    int getPlayerWorldLimit(Player p);
    void setPlayerWorldLimit(Player p, int limit);
    World getWorldByID(int ID);
    boolean inDevelopmentWorld(Player p);

}
