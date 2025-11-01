package com.nightshadow.sunrisecreative.api.menu.menus;

import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.MenuHolder;
import com.nightshadow.sunrisecreative.creative.world.World;
import com.nightshadow.sunrisecreative.creative.world.generator.Generator;
import com.nightshadow.sunrisecreative.creative.world.worldManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import static com.nightshadow.sunrisecreative.Config.getConfig;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.*;
import static java.lang.Integer.valueOf;

public class WorldCreator implements MenuHolder, InventoryHolder {

    Inventory inv;
    Component worldName;
    int worldSize;
    Player owner;


    public WorldCreator(Player p) {
        inv = Bukkit.createInventory(this,9*3, getLocale("menu.title.create",getPlayerLang(p)));
        owner = p;
        resetWorldCreatorMenu();
        p.openInventory(inv);
    }

    void resetWorldCreatorMenu() {
        int[] freeSlot = new int[]{10,11,12,13,14,15,16};
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, new Item(Material.GRAY_STAINED_GLASS_PANE, " ").getItem());
        }
        for (int i : freeSlot) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }
        Item voidGenerator = new Item(Material.STRUCTURE_VOID,localizeOnly("creative.generators.void",getPlayerLang(owner)));
        Item flatGenerator = new Item(Material.GRASS_BLOCK,localizeOnly("creative.generators.flat",getPlayerLang(owner)));
        Item survivalGenerator = new Item(Material.SHORT_GRASS,localizeOnly("creative.generators.survival",getPlayerLang(owner)));
        inv.setItem(10, voidGenerator.getItem());
        inv.setItem(11, flatGenerator.getItem());
        inv.setItem(12, survivalGenerator.getItem());
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if ( e.getSlot() == 10 ) {
            Player player = (Player) e.getWhoClicked();
            int id = worldManager.getNextID();
            Title title = Title.title(getLocale("command.creative.title.generatingTitle", getPlayerLang(player)), getLocale("command.creative.title.generatingSubTitle", getPlayerLang(player)));
            player.closeInventory();
            player.setGameMode(GameMode.SPECTATOR);
            player.showTitle(title);
            World world = new World("&fМир игрока &e"+player.getName(), id, player.getName());
            world.generateWorld(Generator.VOID, id, valueOf(getConfig("world-size-default").toString()));
            world.teleportWorld(player);
            player.sendMessage(getLocale("command.creative.message.world-created", getPlayerLang(player)));
        }
        if ( e.getSlot() == 11 ) {
            Player player = (Player) e.getWhoClicked();
            int id = worldManager.getNextID();
            Title title = Title.title(getLocale("command.creative.title.generatingTitle", getPlayerLang(player)), getLocale("command.creative.title.generatingSubTitle", getPlayerLang(player)));
            player.closeInventory();
            player.setGameMode(GameMode.SPECTATOR);
            player.showTitle(title);
            World world = new World("&fМир игрока &e"+player.getName(), id, player.getName());
            world.generateWorld(Generator.FLAT, id, valueOf(getConfig("world-size-default").toString()));
            world.teleportWorld(player);
            player.sendMessage(getLocale("command.creative.message.world-created", getPlayerLang(player)));
        }
        if ( e.getSlot() == 12 ) {
            Player player = (Player) e.getWhoClicked();
            int id = worldManager.getNextID();
            Title title = Title.title(getLocale("command.creative.title.generatingTitle", getPlayerLang(player)), getLocale("command.creative.title.generatingSubTitle", getPlayerLang(player)));
            player.closeInventory();
            player.setGameMode(GameMode.SPECTATOR);
            player.showTitle(title);
            World world = new World("&fМир игрока &e"+player.getName(), id, player.getName());
            world.generateWorld(Generator.SURVIVAL, id, valueOf(getConfig("world-size-default").toString()));
            world.teleportWorld(player);
            player.sendMessage(getLocale("command.creative.message.world-created", getPlayerLang(player)));
        }

    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
