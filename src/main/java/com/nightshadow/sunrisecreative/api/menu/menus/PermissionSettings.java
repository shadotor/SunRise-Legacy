package com.nightshadow.sunrisecreative.api.menu.menus;

import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import com.nightshadow.sunrisecreative.creative.world.World;
import com.nightshadow.sunrisecreative.creative.world.WorldConfig;
import com.nightshadow.sunrisecreative.creative.world.WorldPermission;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.creative.world.WorldConfig.getConfig;

@ApiStatus.Experimental
public class PermissionSettings extends Menu {
    World world;
    Menu menu;
    WorldPermission permission;
    Player player;
    YamlConfiguration config;
    public PermissionSettings(WorldPermission perm, World world, Player p) {
        this.world = world;
        permission = perm;
        player = p;
        menu = new Menu(6, getLocale("menu.title.world",getPlayerLang(p)));
        for (int i = 18; i <= 26; i++ ) {
            menu.setItem(i, new Item(Material.GRAY_STAINED_GLASS_PANE, " "));
        }
        config = getConfig(world,permission.name().toLowerCase());
        fillPermissionList();
        fillUnpermittedList();
        player.openInventory(menu.getInventory());
    }
    void fillPermissionList() {
        String[] keys = config.getKeys(false).toArray(new String[config.getKeys(false).size()]);
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Item playerHead = new Item(Material.PLAYER_HEAD, key);
            menu.setItem(i, playerHead);
            sendConsole("Player has permission " + key);
            menu.addButton(i, (p) -> {this.removePlayer(key);});
        }
    }
    void fillUnpermittedList() {
        List<String> keys = new ArrayList<>();
        for (Player worldPlayer : world.getPlayers()) {
            if (! world.ifPlayerHasPermission(worldPlayer, permission)) keys.add(worldPlayer.getName());
        }
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            sendConsole("Player hasn't permission " + key);
            Item playerHead = new Item(Material.PLAYER_HEAD, key);
            menu.setItem(27+i, playerHead);
            menu.addButton(27+i, (p) -> {this.addPlayer(key);});
        }
    }
    void restartList() {
        config = getConfig(world, permission.name().toLowerCase());
        sendConsole("Updating world list");
        menu.removeButtons();
        menu.getInventory().clear();
        for (int i = 18; i <= 26; i++ ) {
            menu.setItem(i, new Item(Material.GRAY_STAINED_GLASS_PANE, " "));
        }
        fillUnpermittedList();
        fillPermissionList();
    }
    void addPlayer(String nick) {
        WorldConfig.setKey(world, permission.name().toLowerCase(),nick,true);
        restartList();
    }
    void removePlayer(String nick) {
        WorldConfig.setKey(world, permission.name().toLowerCase(),nick,null);
        restartList();
    }
}
