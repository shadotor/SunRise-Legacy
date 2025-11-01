package com.nightshadow.sunrisecreative.creative.listener;


import com.nightshadow.sunrisecreative.api.NewsAPI;
import com.nightshadow.sunrisecreative.api.menu.Items;
import com.nightshadow.sunrisecreative.creative.command.other.Spawn;
import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.menus.MyWorlds;
import com.nightshadow.sunrisecreative.api.menu.menus.WorldBrowser;
import com.nightshadow.sunrisecreative.creative.world.World;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.nightshadow.sunrisecreative.creative.command.other.Spawn.findSpawn;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.PlayerUtils.setItems;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.*;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.*;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Spawn.createSpawn();
        if (findSpawn() != null) {
            if (! NewsAPI.isRead(p)) {
                if (NewsAPI.DialogExists()) {
                    NewsAPI.setRead(p, true);
                    NewsAPI.showDialog(p);
                }
            }
            p.clearActivePotionEffects();
            p.setFoodLevel(20);
            p.getInventory().clear();
            p.setGameMode(GameMode.ADVENTURE);
            p.setHealth(20);
            p.setExp(0);
            p.setLevel(0);
            p.sendMessage(getLocale("command.spawn.message.teleported", getPlayerLang(p)));
            p.teleport(Objects.requireNonNull(findSpawn()));
            Map<Integer, Item> items = new HashMap<>();
            items.put(4, Items.WORLD_BROWSER.getItem(getPlayerLang(p)));
            items.put(6,Items.MY_WORLDS.getItem(getPlayerLang(p)));
            setItems(items, p);
        }
        Bukkit.broadcast(parseColor("&f[&a+&f] " + parsePlaceholders(p, "&f%luckperms_prefix%" + p.getDisplayName() + "%luckperms_suffix%")));
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Bukkit.broadcast(parseColor("&f[&c-&f] " + parsePlaceholders(p, "&f%luckperms_prefix%" + p.getDisplayName() + "%luckperms_suffix%")));
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player) {
            Player p = (Player) entity;
            if (p.getLocation().getWorld().getName().equals("spawn") ) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerHungry(FoodLevelChangeEvent e) {
        if (e.getEntity().getWorld().getName().equals("spawn")) e.setCancelled(true);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        if(! e.isCancelled()) {

            e.setCancelled(true);
            if (msg.startsWith("!") && msg.length() > 1) {
                msg =msg.replaceFirst("!", "");
                if (msg.startsWith(" ")) msg = msg.replaceFirst(" ", "");
                Bukkit.broadcast(parseColor(parsePlaceholders(p, "&#aaffaa[G] &f%luckperms_prefix%" + p.getDisplayName() + "%luckperms_suffix%" + "&f: " + msg)));
            }
            else if (getWorldWhereIncludingDevWorld(p) == spawn) p.getWorld().getPlayers().forEach((pl) -> pl.sendMessage(parseColor(parsePlaceholders(p, "&f%luckperms_prefix%" + p.getDisplayName() + "%luckperms_suffix%" + "&f: " + e.getMessage()))));
            else getWorldWhereIncludingDevWorld(p).getPlayers().forEach( (pl) -> {
                if (! inDevelopmentWorld(p)) pl.sendMessage(parseColor(parsePlaceholders(p, "&f%luckperms_prefix%" + p.getDisplayName() + "%luckperms_suffix%" + "&f: " + e.getMessage())));
                else pl.sendMessage(parseColor(parsePlaceholders(p, "&e<hover:show_text:'<yellow>Данный игрок находится в режиме разработчика'>[⚡]</hover> &f%luckperms_prefix%" + p.getDisplayName() + "%luckperms_suffix%" + "&f: " + e.getMessage())));
            });
        }
    }
    @EventHandler
    public static void onDeath(PlayerDeathEvent e) {
        Player p = e.getPlayer();
        e.getPlayer().getWorld().getPlayers().forEach((Player pl) -> {
            pl.sendMessage(parseColor("&7" + p.getName() + " &8Погиб"));
        });
    }
    @EventHandler
    public static void Respawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        try {
            if (Objects.requireNonNull(p.getBedSpawnLocation()).getWorld().getName().equals(Objects.requireNonNull(p.getLastDeathLocation()).getWorld().getName())) {
                e.setRespawnLocation(p.getBedSpawnLocation());
            }
        }
        catch (Exception ex) {
                e.setRespawnLocation(Objects.requireNonNull(p.getLastDeathLocation()).getWorld().getSpawnLocation());
        }
    }
    @EventHandler
    public static void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (getWorldWhereIncludingDevWorld(p) == spawn) {
            if (e.getAction().isRightClick() && e.getHand() == EquipmentSlot.HAND) {
                if (p.getInventory().getItemInMainHand().equals(Items.WORLD_BROWSER.getItem(getPlayerLang(p)).getItem()))
                    new WorldBrowser(worldList.toArray(new World[worldList.size()]), p);
                if (p.getInventory().getItemInMainHand().equals(Items.MY_WORLDS.getItem(getPlayerLang(p)).getItem())) new MyWorlds(p);
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (p.getGameMode() != GameMode.CREATIVE && Objects.requireNonNull(e.getClickedBlock()).getType().name().contains("DOOR")) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public static void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (getWorldWhereIncludingDevWorld(p) == spawn) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void onGamemodeChange(PlayerGameModeChangeEvent e) {
        if ( getWorldWhereIncludingDevWorld(e.getPlayer()) == spawn && getWorldWhere(e.getPlayer()) == spawn && ! e.getPlayer().isOp() && e.getNewGameMode() != GameMode.ADVENTURE) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void onEntityBreak(HangingBreakByEntityEvent e) {
        Entity entity = e.getRemover();
        if (entity instanceof Player p) {
            if (getWorldWhereIncludingDevWorld(p) == spawn) {
                if (p.getGameMode() != GameMode.CREATIVE) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public static void onEntityAttack(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if (entity instanceof Player p) {
        if (getWorldWhereIncludingDevWorld(p) == spawn) {
                if (p.getGameMode() != GameMode.CREATIVE) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public static void onSpectatorTeleporting(PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) e.setCancelled(true);
    }
}


