package com.nightshadow.sunrisecreative.creative.coding.menus;

import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.utils.Language;
import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.localizeOnly;


@ApiStatus.Experimental
public enum CodeCategories {
    PLAYER_COMMUNICATION(Material.OAK_SIGN, "coding.category.player.communication.item.title", "coding.category.player.communication.item.description"),
    EVERY_OTHER(Material.BARRIER, "coding.category.every.other.item.title", "coding.category.every.other.item.description"),
    PLAYER_INVENTORY(Material.CHEST,"coding.category.player.inventory.item.title", "coding.category.player.inventory.item.title" );
    private final Material icon;
    private final String itemNameTranslationKey;
    private final String itemDescriptionTranslationKey;
    CodeCategories(Material icon, String itemNameTranslationKey, String descriptionTranslationKey) {
        this.icon = icon;
        this.itemNameTranslationKey = itemNameTranslationKey;
        this.itemDescriptionTranslationKey = descriptionTranslationKey;
    }
    public Item getCategoryItem(Language lang) {
        return new Item(icon,localizeOnly(itemNameTranslationKey,lang), List.of(localizeOnly(itemDescriptionTranslationKey,lang)));
    }
}
