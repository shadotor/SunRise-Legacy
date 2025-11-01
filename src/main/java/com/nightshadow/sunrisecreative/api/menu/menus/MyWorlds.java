package com.nightshadow.sunrisecreative.api.menu.menus;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import com.nightshadow.sunrisecreative.creative.world.World;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.nightshadow.sunrisecreative.api.menu.Template.createFramed;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.*;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getPlayerWorldLimit;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldsByPlayer;

public class MyWorlds extends Menu {
    Menu menu;
    Player owner;
    List<World> worlds;
    int[] indexes = new int[]{20, 21, 22, 23, 24, 29, 30, 31, 32, 33};
    int page = 0;

    public MyWorlds(Player p) {
        owner = p;
        worlds = getWorldsByPlayer(owner);
        menu = new Menu(6, getLocale("menu.title.my-world",getPlayerLang(p)));
        createFramed(menu);
        fillWorldList();
        p.openInventory(menu.getInventory());
    }

    void fillWorldList() {
        Map<Integer, Consumer<InventoryClickEvent>> map = new HashMap<>();
        Plugin plugin = Sunrise_Creative.getInstance();
        plugin.getLogger().info("World size: " + worlds.size());
        for (int i = 0; i < indexes.length; i++) {
            menu.setItem(indexes[i], new Item(Material.LIGHT_GRAY_STAINED_GLASS, " "));

            if (i + (10 * page) < worlds.size()) {
                final int index = i + (10 * page);
                map.put(indexes[i], (e) -> {
                    Player p = (Player) e.getWhoClicked();
                    World world = getWorldsByPlayer(p).get(index);
                    if (e.getClick() == ClickType.LEFT)  {
                        world.teleportWorld(p);
                        p.sendMessage(getLocale("creative.message.teleport", getPlayerLang(p)));
                    }
                    else {
                        Menu confirmationMenu = new Menu(getLocale("creative.confirmation-menu.title",getPlayerLang(p)), InventoryType.HOPPER);
                        Item confirm = new Item(Material.EMERALD_BLOCK, "&a✔");
                        Item deny = new Item(Material.REDSTONE_BLOCK, "&c❌");
                        confirmationMenu.setItem(1, confirm);
                        confirmationMenu.setItem(3,deny);
                        confirmationMenu.addButton(1, (event) -> {
                            world.delete();
                            ( (Player) e.getWhoClicked()).closeInventory();
                        });
                        confirmationMenu.addButton(3, (event) -> {
                            ( (Player) e.getWhoClicked()).closeInventory();
                        });
                        ( (Player) e.getWhoClicked()).openInventory(confirmationMenu.getInventory());
                    }

                });
                menu.setItem(indexes[i], worlds.get(i + (10 * page)).getIcon());
            }
            if (i +(10*page) == worlds.size() && worlds.size() < getPlayerWorldLimit(owner)) {
                Item newWorld = new Item(Material.LIME_STAINED_GLASS, "&a+");
                menu.setItem(indexes[i], newWorld);
                map.put(indexes[i], (e) -> {
                    Player p = (Player) e.getWhoClicked();
                    new WorldCreator(p);
                });
            }
        }
        menu.setButtons(map);
    }
}
