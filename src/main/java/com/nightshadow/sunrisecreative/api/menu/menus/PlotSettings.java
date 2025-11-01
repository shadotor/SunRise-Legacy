package com.nightshadow.sunrisecreative.api.menu.menus;

import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.Marker;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import com.nightshadow.sunrisecreative.creative.world.World;
import com.nightshadow.sunrisecreative.creative.world.WorldPermission;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.*;
import static com.nightshadow.sunrisecreative.creative.world.WorldConfig.getKey;
import static com.nightshadow.sunrisecreative.creative.world.WorldConfig.setKey;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;

public class PlotSettings extends Menu {
    Menu ui;
    Player p;
    World w;
    public PlotSettings(Player p, World w) {
        this.p = p;
        this.w = w;
        ui = new Menu(6, getLocale("menu.title.world", getPlayerLang(p)));
        fillEmpty();
        fillMarkers();
        fillButtons();
        p.openInventory(ui.getInventory());
    }
    void fillEmpty() {
        int[] emptySlots = new int[]{3,5,9,10,11,12,13,14,15,16,17,19,25,28,34,37,43,46,52};
        for (int emptySlot : emptySlots) {
            ui.setItem(emptySlot, new Item(Material.GRAY_STAINED_GLASS_PANE, " "));
        }
    }
    void fillButtons() {

        // WhiteList

        Item whitelist = new Item(Material.SMOOTH_QUARTZ, localizeOnly("setting.whitelist",getPlayerLang(p)));
        ui.setItem(18, whitelist);
        ui.addButton(18, (pair) -> {
            new PermissionSettings(WorldPermission.WHITELIST,this.w,this.p);
        });

        // Blacklist
        Item blacklist = new Item(Material.COAL_BLOCK, localizeOnly("setting.blacklist",getPlayerLang(p)));
        ui.setItem(27, blacklist);
        ui.addButton(27, (pair) -> {
            new PermissionSettings(WorldPermission.BLACKLIST,this.w,this.p);
        });

        // Developers
        Item developers = new Item(Material.REPEATING_COMMAND_BLOCK, localizeOnly("setting.developer-list",getPlayerLang(p)));
        ui.setItem(36, developers);
        ui.addButton(36, (pair) -> {
            new PermissionSettings(WorldPermission.DEVELOPER,this.w,this.p);
        });

        // Admins
        Item admins = new Item(Material.BEACON, localizeOnly("setting.admin-list",getPlayerLang(p)));
        ui.setItem(26, admins);
        ui.addButton(26, (pair) -> {
            new PermissionSettings(WorldPermission.ADMIN,this.w,this.p);
        });
    }
    void fillMarkers() {
        ui.setItem(4, w.getIcon());

        // Availability Marker
        List<Item> availability = new ArrayList<>();
        List<String> d1 = new ArrayList<>();
        d1.add(localizeOnly("availability.open",getPlayerLang(p)));
        Item opened = new Item(Material.OAK_DOOR, localizeOnly("setting.availability", getPlayerLang(p)),d1);

        List<String> d2 = new ArrayList<>();
        d2.add(localizeOnly("availability.close",getPlayerLang(p)));
        Item closed = new Item(Material.IRON_DOOR, localizeOnly("setting.availability", getPlayerLang(p)),d2);

        availability.add(opened);
        availability.add(closed);

        Map<Integer,String> availabilityRecover = new HashMap<>();
        availabilityRecover.put(0, "true");
        availabilityRecover.put(1, "false");

        Marker availabilityMarker = new Marker(availability, (state) -> setKey(w,"data","availability",availabilityRecover.get(state)));


        if (getKey(w,"data","availability") != null) {
            for (Integer i : availabilityRecover.keySet()) {
                if (getKey(w,"data","availability").toString().equals(availabilityRecover.get(i))) {
                    availabilityMarker.setState(i);
                }
            }
        }
        // ==============================|
        // ==============================|

        ui.addMarker(22, availabilityMarker);

        // Change World GameMode marker
        List<Item> changeGamemode = new ArrayList<>();

        Item creative = new Item(Material.DIAMOND, localizeOnly("setting.gamemode",getPlayerLang(p)));
        List<String> desc = new ArrayList<>();
        desc.add(localizeOnly("gamemode.creative", getPlayerLang(p)));
        creative.setDescription(desc);

        List<String> desc1 = new ArrayList<>();
        desc1.add(localizeOnly("gamemode.adventure", getPlayerLang(p)));
        Item adventure = new Item(Material.SPYGLASS,  localizeOnly("setting.gamemode",getPlayerLang(p)), desc1);

        List<String> desc2 = new ArrayList<>();
        desc2.add(localizeOnly("gamemode.survival", getPlayerLang(p)));
        Item survival = new Item(Material.GOLDEN_PICKAXE,  localizeOnly("setting.gamemode",getPlayerLang(p)), desc2);

        List<String> desc3 = new ArrayList<>();
        desc3.add(localizeOnly("gamemode.spectator", getPlayerLang(p)));
        Item spectator = new Item(Material.GLASS,  localizeOnly("setting.gamemode",getPlayerLang(p)), desc3);

        changeGamemode.add(adventure);
        changeGamemode.add(survival);
        changeGamemode.add(spectator);
        changeGamemode.add(creative);
        Map<Integer,String> gamemodes = new HashMap<>();
        gamemodes.put(0, "ADVENTURE");
        gamemodes.put(1, "SURVIVAL");
        gamemodes.put(2, "SPECTATOR");
        gamemodes.put(3, "CREATIVE");
        Marker changeWorldGamemode = new Marker(changeGamemode, (state) -> setKey(w, "data", "gamemode", gamemodes.get(state)));
        if (getKey(w,"data","gamemode") != null) {
            for (Integer i : gamemodes.keySet()) {
                if (gamemodes.get(i).equals(getKey(w, "data", "gamemode").toString())) {
                    changeWorldGamemode.setState(i);
                }
            }
        }

        // ==============================|
        // ==============================|

        ui.addMarker(32, changeWorldGamemode);

        // World Difficulty Marker

        List<Item> changeDifficulty = new ArrayList<>();


        List<String> desc4 = new ArrayList<>();
        desc4.add(localizeOnly("difficulty.peaceful", getPlayerLang(p)));
        Item peaceful = new Item(Material.PLAYER_HEAD, localizeOnly("setting.difficulty",getPlayerLang(p)),desc4);

        List<String> desc5 = new ArrayList<>();
        desc5.add(localizeOnly("difficulty.easy", getPlayerLang(p)));
        Item easy = new Item(Material.ZOMBIE_HEAD,  localizeOnly("setting.difficulty",getPlayerLang(p)), desc5);

        List<String> desc6 = new ArrayList<>();
        desc6.add(localizeOnly("difficulty.normal", getPlayerLang(p)));
        Item normal = new Item(Material.SKELETON_SKULL,  localizeOnly("setting.difficulty",getPlayerLang(p)), desc6);

        List<String> desc7 = new ArrayList<>();
        desc7.add(localizeOnly("difficulty.hard", getPlayerLang(p)));
        Item hard = new Item(Material.WITHER_SKELETON_SKULL,  localizeOnly("setting.difficulty",getPlayerLang(p)), desc7);

        changeDifficulty.add(peaceful);
        changeDifficulty.add(easy);
        changeDifficulty.add(normal);
        changeDifficulty.add(hard);

        Map<Integer,String> difficulties = new HashMap<>();
        difficulties.put(0,"PEACEFUL");
        difficulties.put(1, "EASY");
        difficulties.put(2, "NORMAL");
        difficulties.put(3, "HARD");

        Marker changeWorldDifficulty = new Marker(changeDifficulty,(state) -> {
            setKey(w, "data", "difficulty", difficulties.get(state));
            getWorldWhere(p).getWorld().setDifficulty(Difficulty.valueOf(difficulties.get(state)));
        });

        if (getKey(w,"data","difficulty") != null) {
            for (Integer i : gamemodes.keySet()) {
                if (difficulties.get(i).equals(getKey(w, "data", "difficulty").toString())) {
                    changeWorldDifficulty.setState(i);
                }
            }
        } else changeWorldDifficulty.setState(1);

        ui.addMarker(47,changeWorldDifficulty);

        // ==============================|
        // ==============================|


    }
}
