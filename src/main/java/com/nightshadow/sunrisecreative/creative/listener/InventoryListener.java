package com.nightshadow.sunrisecreative.creative.listener;


import com.nightshadow.sunrisecreative.creative.coding.menus.CodingMenu;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import com.nightshadow.sunrisecreative.api.menu.menus.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.Objects;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        InventoryHolder holder = Objects.requireNonNull(e.getClickedInventory()).getHolder();
        if (e.getClickedInventory().getHolder() instanceof Menu) {
            e.setCancelled(true);
            Menu menu = (Menu) holder;
            Objects.requireNonNull(menu).onInventoryClick(e);
        }
        if (e.getClickedInventory().getHolder() instanceof WorldCreator) {
            e.setCancelled(true);
            WorldCreator menu = (WorldCreator) holder;
            Objects.requireNonNull(menu).onInventoryClick(e);
        }
        if (holder instanceof CodingMenu m) {
            if (! m.getSlots().contains(e.getSlot())) e.setCancelled(true);
        }
    }
}
