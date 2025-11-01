package com.nightshadow.sunrisecreative.api.menu.menus;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import com.nightshadow.sunrisecreative.creative.world.World;

import org.apache.commons.lang3.tuple.Pair;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


import static com.nightshadow.sunrisecreative.api.menu.Template.createFramed;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.*;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.worldList;

public class WorldBrowser extends Menu {
    Menu browser;
    Player owner;
    World[] worlds;
    int[] indexes = new int[]{20, 21, 22, 23, 24, 29, 30, 31, 32, 33};
    int page = 0;

    public WorldBrowser(World[] worlds, Player player) {
        super();
        this.worlds = worlds;
        this.owner = player;
        browser = new Menu(6,getLocale("menu.title.worlds",getPlayerLang(player)));
        createFramed(browser);
        createMenu();
    }

    public void createMenu() {
        fillWorldList();
        if (worlds.length > 10) {
            Item next = new Item(Material.SPECTRAL_ARROW, " ");
            List<String> list1 = new ArrayList<>();
            list1.add(localizeOnly("menu.item.next-page", getPlayerLang(owner)).toString());
            next.setDescription(list1);
            browser.setItem(52, next);
            final WorldBrowser worldBrowser = this;
            browser.addButton(52, (pair) -> {
                worldBrowser.NextPage();
            });
        }
        BuildUI();
    }

    public void BuildUI() {
        List<String> list = new ArrayList<>();
        list.add(localizeOnly("menu.item.my-world", getPlayerLang(owner)).toString());
        Item MyWorlds = new Item(Material.REPEATING_COMMAND_BLOCK, " ", list);
        browser.setItem(51, MyWorlds);
        browser.addButton(51, (e) -> {
            Player p = (Player) e.getWhoClicked();
            new MyWorlds(p);
        });
        owner.openInventory(browser.getInventory());

    }

    public void NextPage() {
        browser.removeButtons();
        page++;
        fillWorldList();
        browser.setItem(52, new ItemStack(Material.AIR));
        browser.setItem(46, new ItemStack(Material.AIR));
        if (worlds.length > 10 + (10 * page)) {
            Item next = new Item(Material.SPECTRAL_ARROW, " ");
            List<String> list1 = new ArrayList<>();
            list1.add(localizeOnly("menu.item.next-page", getPlayerLang(owner)).toString());
            next.setDescription(list1);
            browser.setItem(52, next);
            final WorldBrowser worldBrowser = this;
            browser.addButton(52, (pair) -> {
                worldBrowser.NextPage();
            });
        }
        if (page > 0) {
            Item next = new Item(Material.SPECTRAL_ARROW, " ");
            List<String> list1 = new ArrayList<>();
            list1.add(localizeOnly("menu.item.previous-page", getPlayerLang(owner)).toString());
            next.setDescription(list1);
            browser.setItem(46, next);
            final WorldBrowser worldBrowser = this;
            browser.addButton(46, (pair) -> {
                worldBrowser.PreviousPage();
            });
        }

        BuildUI();
        owner.openInventory(browser.getInventory());
    }

    public void PreviousPage() {
        browser.removeButtons();
        page--;
        Map<Integer, Consumer<InventoryClickEvent>> map = new HashMap<>();
        fillWorldList();
        browser.setItem(52, new ItemStack(Material.AIR));
        browser.setItem(46, new ItemStack(Material.AIR));
        if (worlds.length > 10 + (10 * page)) {
            Item next = new Item(Material.SPECTRAL_ARROW, " ");
            List<String> list1 = new ArrayList<>();
            list1.add(localizeOnly("menu.item.next-page", getPlayerLang(owner)).toString());
            next.setDescription(list1);
            browser.setItem(52, next);
            final WorldBrowser worldBrowser = this;
            browser.addButton(52, (pair) -> {
                worldBrowser.NextPage();
            });
        }
        if (page > 0) {
            Item next = new Item(Material.SPECTRAL_ARROW, " ");
            List<String> list1 = new ArrayList<>();
            list1.add(localizeOnly("menu.item.previous-page", getPlayerLang(owner)).toString());
            next.setDescription(list1);
            browser.setItem(46, next);
            final WorldBrowser worldBrowser = this;
            browser.addButton(46, (pair) -> {
                worldBrowser.PreviousPage();
            });
        }
        BuildUI();
        browser.setButtons(map);
        owner.openInventory(browser.getInventory());
    }

    public void fillWorldList() {
        Map<Integer, Consumer<InventoryClickEvent>> map = new HashMap<>();
        Plugin plugin = Sunrise_Creative.getInstance();
        plugin.getLogger().info("World size: " + String.valueOf(worlds.length));
        for (int i = 0; i < indexes.length; i++) {
            int j = i;
            browser.setItem(indexes[i], new Item(Material.LIGHT_GRAY_STAINED_GLASS, " "));

            if (j + (10 * page) < worlds.length) {
                    final int index = j + (10 * page);
                    map.put(indexes[j], (e) -> {
                        Player p = (Player) e.getWhoClicked();
                        World world = worldList.get(index);
                        p.sendMessage(localizeOnly("creative.message.teleport", getPlayerLang(p)));
                        world.teleportWorld(p);
                    });
                    browser.setItem(indexes[j], worlds[index].getIcon());
                }

        }
        browser.setButtons(map);
    }
}
