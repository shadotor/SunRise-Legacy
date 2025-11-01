package com.nightshadow.sunrisecreative.api.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


import static com.nightshadow.sunrisecreative.api.utils.TextUtils.*;

public class Item {
    ItemStack item;
    String displayName;
    List<String> description;

    public Item (ItemStack item) {
        this.item = item;
    }
    public Item(Material material) {
        this.item = new ItemStack(material);
    }
    public Item(ItemStack item, String name) {
        this.item = item;
        this.displayName = name;
    }
    public Item(Material material, String name) {
        this.item = new ItemStack(material);
        this.displayName = name;
    }
    public Item(Material material, String name, List<String> description) {
        this.item = new ItemStack(material);
        this.displayName = name;
        this.description = description;
    }
    public Item(ItemStack item, String name, List<String> description) {
        this.item = item;
        this.displayName = name;
        this.description = description;
    }
    public void setCount(int count) {
        this.item.setAmount(count);
    }
    public void setDescription(List<String> description) {
        this.description = description;
    }
    public ItemStack getItem() {
        ItemMeta meta = item.getItemMeta();
        meta.setItemName("");
        meta.setDisplayName("");
        meta.customName(parseColor("<!i>" + displayName));
        meta.itemName(parseColor("<!i>" + displayName));
        List<Component> lores = new ArrayList<>();
        if (description != null) {
            description.replaceAll(s -> "<!i>" + s);
            lores.addAll(parseColor(description));
        }
        meta.lore(lores);
        item.setItemMeta(meta);
        return item;
    }
    public void setName(String name) {
        this.displayName = name;
    }
    public String getName() {
        return this.displayName;
    }
    public void addLore(int index, String lore) {
        description.add(index, lore);
    }
    public void addLore(String lore) {
        description.add(lore);
    }
    public void removeLore() {
        description.removeLast();
    }
    public void hideAdditionalInfo(boolean hide) {
        if (hide) item.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        else item.removeItemFlags(ItemFlag.HIDE_ARMOR_TRIM, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
    }
    public void setEnchantmentGlint(boolean glint) {
        ItemMeta meta = item.getItemMeta();
        meta.setEnchantmentGlintOverride(glint);
        item.setItemMeta(meta);
    }
    public void setCustomModelData(int customModelData) {
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
    }

}
