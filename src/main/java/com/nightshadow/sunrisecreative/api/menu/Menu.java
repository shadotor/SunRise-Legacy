package com.nightshadow.sunrisecreative.api.menu;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Menu implements MenuHolder, InventoryHolder {

    private Inventory inventory;
    private final Map<Integer, Consumer<InventoryClickEvent> > Buttons = new HashMap<>();
    private final Map<Integer, Marker> markers = new HashMap<>();
    private InventoryHolder holder;
    public Menu() {}
    public Menu(int lines, Component name, InventoryHolder holder) {
        this.holder = holder;
        inventory = Bukkit.createInventory(holder,lines*9, name);
    }
    public Menu(Component name, InventoryHolder holder, InventoryType type) {
        this.holder = holder;
        inventory = Bukkit.createInventory(holder,type, name);
    }
    public Menu(Component name, InventoryType type) {
        inventory = Bukkit.createInventory(this,type, name);
    }
    public Menu(int lines, Component name) {
        inventory = Bukkit.createInventory(this, lines*9, name);
    }
    public void addMarker(int index, Marker marker) {
        this.setItem(index,marker.getIcons().get(marker.getState()));
        markers.put(index, marker);
    }
    @Deprecated
    public void renameInventory(String name) {
        ItemStack[] content = inventory.getContents();
        inventory = Bukkit.createInventory(this, inventory.getSize(), name);
        inventory.setContents(content);
    }
    @Deprecated
    public void renameInventory(Component name) {
        ItemStack[] content = inventory.getContents();
        inventory = Bukkit.createInventory(this, inventory.getSize(),name);
        inventory.setContents(content);
    }
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final int slot = e.getSlot();
        if (Buttons.containsKey(slot)) {
                Buttons.get(slot).accept(e);
        }
        if (markers.containsKey(slot)) {
                markers.get(slot).onClickEvent(e);
        }
    }
    public void removeItem(int slot) {
        inventory.setItem(slot, new ItemStack(Material.AIR));
    }
    public void fillMenu(Item item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            setItem(i,item);
        }
    }
    public void setItem(int slot, Item item) {inventory.setItem(slot, item.getItem());}
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }
    public void setButtons(Map<Integer, Consumer<InventoryClickEvent> > Buttons) {
        this.Buttons.putAll(Buttons);
    }
    public void addButton(Integer i, Consumer<InventoryClickEvent> b ) {this.Buttons.put(i,b);}
    public void removeButtons() {this.Buttons.clear();}
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
