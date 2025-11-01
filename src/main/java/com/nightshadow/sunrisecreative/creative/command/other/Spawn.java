package com.nightshadow.sunrisecreative.creative.command.other;

import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world.PLAYER_QUIT_EVENT;
import com.nightshadow.sunrisecreative.api.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;
import static com.nightshadow.sunrisecreative.api.menu.Items.*;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.PlayerUtils.setItems;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;

/* Команда для возвращения на спавн */
public class Spawn implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            teleportSpawn(player);
        }
        else if (args[0].equals("create")) {
            if (player.isOp()) {
                setSpawn(player.getLocation());
                player.sendMessage(getLocale("command.spawn.message.success",getPlayerLang(player)));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        List<String> tabCompleter = new ArrayList<>();
        if (args.length <= 1 && commandSender.isOp()) {
            tabCompleter.add("create");
        }
        return tabCompleter;
    }
    public static void createSpawn() {
        new File(getInstance().getDataFolder(), "spawn.yml");
    }
    public static Location findSpawn() {
        Plugin plugin = getInstance();
        File spawn = new File(plugin.getDataFolder(), "spawn.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(spawn);
        Object point = config.get("point", null);
        if (point != null) return (Location) point;
        else return null;
    }
    public void setSpawn(Location loc) {
        Plugin plugin = getInstance();
        File spawn = new File(plugin.getDataFolder(), "spawn.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(spawn);
        config.set("point", loc);
        try {
            config.save(spawn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void teleportSpawn(Player player) {
        new PLAYER_QUIT_EVENT().onExecute(getWorldWhere(player),player);
        Bukkit.getScheduler().runTaskLater(getInstance(),() -> {
            createSpawn();
            if (findSpawn() != null) {
                player.clearActivePotionEffects();
                player.setFoodLevel(20);
                player.getInventory().clear();
                player.setGameMode(GameMode.ADVENTURE);
                player.setHealth(20);
                player.setExp(0);
                player.setLevel(0);
                player.sendMessage(getLocale("command.spawn.message.teleported", getPlayerLang(player)));
                player.teleport(findSpawn());
                Map<Integer, Item> items = new HashMap<>();
                // items.put(8, suffixes);
                items.put(4, WORLD_BROWSER.getItem(getPlayerLang(player)));
                items.put(6,MY_WORLDS.getItem(getPlayerLang(player)));
                setItems(items, player);
            } else player.sendMessage(getLocale("command.spawn.message.not-found",getPlayerLang(player)));
        },1);

    }
}
