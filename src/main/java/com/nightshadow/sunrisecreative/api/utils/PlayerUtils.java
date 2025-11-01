package com.nightshadow.sunrisecreative.api.utils;

import com.nightshadow.sunrisecreative.api.menu.Item;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.*;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;

public class PlayerUtils {
    private final static Map<UUID, PermissionAttachment> permissionAttachmentMap = new HashMap<>();

    /**
     * Give item Item to player
     * @param i
     * @param p
     */
    @Deprecated
    public static void giveItem(Item i, Player p) {
        p.getInventory().addItem(i.getItem());
    }

    /**
     * Set item to player
     * @param items
     * @param p
     */
    public static void setItems(Map<Integer, Item> items, Player p) {
        items.forEach((Integer i, Item it) -> {
            sendConsole(i.toString());
            p.getInventory().setItem(i, it.getItem());
        });
    }
}
