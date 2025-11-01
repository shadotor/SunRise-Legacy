package com.nightshadow.sunrisecreative;

import com.nightshadow.sunrisecreative.creative.command.creative.*;
import com.nightshadow.sunrisecreative.creative.command.other.*;
import com.nightshadow.sunrisecreative.creative.command.scripting.Dev;
import com.nightshadow.sunrisecreative.creative.command.scripting.Log;
import com.nightshadow.sunrisecreative.creative.command.vanilla.Gamemode;
import com.nightshadow.sunrisecreative.creative.command.vanilla.Give;
import com.nightshadow.sunrisecreative.creative.listener.*;
import com.nightshadow.sunrisecreative.creative.world.Manager;
import com.nightshadow.sunrisecreative.creative.world.WorldManagerInterface;
import com.nightshadow.sunrisecreative.creative.world.worldManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static com.nightshadow.sunrisecreative.Config.createConfig;
import static com.nightshadow.sunrisecreative.creative.coding.handler.Actions.loadActions;
import static com.nightshadow.sunrisecreative.creative.coding.handler.Events.loadEvents;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.createLocales;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

import java.util.Objects;

public final class Sunrise_Creative extends JavaPlugin {
    private static Sunrise_Creative instance;
    private static WorldManagerInterface currentlyWorldManager;

    @Override
    public void onEnable() {
        Bukkit.getServer().broadcast(parseColor("&6&l[SunRise Creative+] &r&eStarting server "));
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.setGameMode(GameMode.ADVENTURE);
            onlinePlayer.getInventory().clear();
            onlinePlayer.teleport(Objects.requireNonNull(Bukkit.getWorld("spawn")).getSpawnLocation());
            onlinePlayer.sendMessage(parseColor("&6&l[SunRise Creative+] &r&eServer is loading, please wait..."));
        }
        // Plugin startup logic
        currentlyWorldManager = new Manager();
        instance = this;
        getLogger().info("Loading plugin...");

        // Server Commands
        Bukkit.getServer().broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoading server commands "));

        Objects.requireNonNull(getServer().getPluginCommand("gamemode")).setExecutor(new Gamemode());
        Objects.requireNonNull(getServer().getPluginCommand("give")).setExecutor(new Give());
        Objects.requireNonNull(getServer().getPluginCommand("locate")).setExecutor(new Locate());
        Objects.requireNonNull(getServer().getPluginCommand("join")).setExecutor(new Join());
        Objects.requireNonNull(getServer().getPluginCommand("myworlds")).setExecutor(new MyWorlds());
        Objects.requireNonNull(getServer().getPluginCommand("locale")).setExecutor(new Locale());
        Objects.requireNonNull(getServer().getPluginCommand("creative")).setExecutor(new Creative());
        Objects.requireNonNull(getServer().getPluginCommand("spawn")).setExecutor(new Spawn());
        Objects.requireNonNull(getServer().getPluginCommand("worlds")).setExecutor(new Worlds());
        Objects.requireNonNull(getServer().getPluginCommand("dev")).setExecutor(new Dev());
        Objects.requireNonNull(getServer().getPluginCommand("plot")).setExecutor(new Plot());
        // Objects.requireNonNull(getServer().getPluginCommand("donate")).setExecutor(new donate());
        // Objects.requireNonNull(getServer().getPluginCommand("playsound")).setExecutor(new Playsound());
        // Objects.requireNonNull(getServer().getPluginCommand("news")).setExecutor(new News());
        Objects.requireNonNull(getServer().getPluginCommand("build")).setExecutor(new WorldStatementsCommands());
        Objects.requireNonNull(getServer().getPluginCommand("play")).setExecutor(new WorldStatementsCommands());
        Objects.requireNonNull(getServer().getPluginCommand("log")).setExecutor(new Log());
        Objects.requireNonNull(getServer().getPluginCommand("staffchat")).setExecutor(new StaffChat());
        // Objects.requireNonNull(getServer().getPluginCommand("SetPlayerLimit")).setExecutor(new SetLimitPlayers());
        // Server Listeners
        Bukkit.getServer().broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoading event listeners... "));

        getServer().getPluginManager().registerEvents(new DevelopmentWorldListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new CreativeEventListener(),this);
        getServer().getPluginManager().registerEvents(new CreativeEntityEventListener(),this);
        getServer().getPluginManager().registerEvents(new CreativeWorldEventListener(),this);

        // Loading Worlds
        Bukkit.getServer().broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoading Creative+ worlds... "));
        worldManager.loadWorlds();
        Bukkit.getServer().broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoaded " + worldManager.worldList.size() + " worlds... "));

        // Loading localization files and config
        Bukkit.getServer().broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoading localization files "));
        createLocales();
        createConfig();

        // Loading coding
        Bukkit.getServer().broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoading coding"));
        loadEvents();
        loadActions();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static WorldManagerInterface getWorldManager() {
        return currentlyWorldManager;
    }
    public static void sendConsole(String msg) {
        instance.getLogger().info(msg);
    }
    public static Sunrise_Creative getInstance() {
        return instance;
    }
}
