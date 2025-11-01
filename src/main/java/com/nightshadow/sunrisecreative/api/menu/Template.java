package com.nightshadow.sunrisecreative.api.menu;

import org.bukkit.Material;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;

public abstract class Template {
    public static void createFramed(Menu m) {
        Item frame = new Item(Material.GRAY_STAINED_GLASS_PANE, " ");
        int[] framedSlots = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 53};
        for (int i : framedSlots) {
            m.setItem(i, frame);
        }
        sendConsole("Successful generated template: framed");
    }
    public static void createButtoned(Menu m) {
        Item frame = new Item(Material.GRAY_STAINED_GLASS_PANE, " ");
        for (int i = 45; i < 54; i++ ) {
            m.setItem(i,frame);
        }
    }
}
