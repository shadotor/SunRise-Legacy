package com.nightshadow.sunrisecreative.api.menu;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.function.Consumer;

public class Marker {
    private int state = 0;
    private List<Item> icons;
    private Consumer<Integer> onClick;
    public Marker(List<Item> setting) {
            icons = setting;
    }
    public Marker(List<Item> setting, Consumer<Integer> consumer) {
        icons = setting;
        onClick = consumer;
    }
    public void onClickEvent(InventoryClickEvent e) {
        Inventory i = e.getClickedInventory();
        if (e.getClick() == ClickType.LEFT) {
            if (state < icons.size()-1) {
                state += 1;
                i.setItem(e.getSlot(), icons.get(state).getItem());
            }
            else {
                state = 0;
                i.setItem(e.getSlot(), icons.get(state).getItem());
            }

        } else if(e.getClick() == ClickType.RIGHT) {
            if (state != 0) {
                state -= 1;
                i.setItem(e.getSlot(), icons.get(state).getItem());
            }
            else {
                this.state = icons.size()-1;
                i.setItem(e.getSlot(), icons.get(this.state).getItem());
            }
        }
        if (onClick != null) {
            onClick.accept(this.state);
        }
    }
    public List<Item> getIcons() {return this.icons;}
    public void setState(int state) { this.state = state;}
    public int getState() { return state;}
}
