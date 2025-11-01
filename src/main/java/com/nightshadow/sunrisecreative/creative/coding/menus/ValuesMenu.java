package com.nightshadow.sunrisecreative.creative.coding.menus;

import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

public class ValuesMenu extends Menu {
    Menu menu;
    public ValuesMenu() {
        menu = new Menu(1, parseColor(""));
        // Текст
        menu.setItem(0, new Item(Material.BOOK, "Text Value"));
        menu.addButton(0, (e) -> {
            ItemStack itemStack = new Item(Material.BOOK, "Text Value").getItem();
            ItemMeta i =  itemStack.getItemMeta();
            i.setCustomModelData(1024);
            itemStack.setItemMeta(i);
            ((Player) e.getWhoClicked()).give(itemStack);
        });
        // Число
        menu.setItem(1, new Item(Material.SLIME_BALL, "&cNumber Value"));
        menu.addButton(1, (e) -> {
            ItemStack itemStack = new Item(Material.SLIME_BALL, "&cNumber Value").getItem();
            ItemMeta i =  itemStack.getItemMeta();
            i.setCustomModelData(1024);
            itemStack.setItemMeta(i);
            ( (Player) e.getWhoClicked() ).give(itemStack);
        });
        // Переменная
        menu.setItem(2, new Item(Material.MAGMA_CREAM, "&eVariable"));
        menu.addButton(2, (e) -> {
            ItemStack itemStack = new Item(Material.MAGMA_CREAM, "&eVariable").getItem();
            ItemMeta i =  itemStack.getItemMeta();
            i.setCustomModelData(1024);
            itemStack.setItemMeta(i);
            ( (Player) e.getWhoClicked() ).give(itemStack);
        });
        // Игровое значение
        menu.setItem(8, new Item(Material.NAME_TAG, "&aGame Values"));
        menu.addButton(8, (e) -> {
            ItemStack itemStack = new Item(Material.MAGMA_CREAM, "&eVariable").getItem();
            ItemMeta i =  itemStack.getItemMeta();
            i.setCustomModelData(1024);
            itemStack.setItemMeta(i);
            ( (Player) e.getWhoClicked() ).give(itemStack);
        });
    }
    @Override
    public @NotNull Inventory getInventory() {
        return menu.getInventory();
    }
}

