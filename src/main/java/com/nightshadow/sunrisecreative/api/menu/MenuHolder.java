package com.nightshadow.sunrisecreative.api.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public interface MenuHolder {
    void onInventoryClick(InventoryClickEvent e);

    Inventory getInventory();


}
