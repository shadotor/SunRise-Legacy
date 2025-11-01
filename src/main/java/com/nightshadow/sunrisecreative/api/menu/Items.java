package com.nightshadow.sunrisecreative.api.menu;

import com.nightshadow.sunrisecreative.api.utils.Language;
import org.bukkit.Material;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.localizeOnly;

public enum Items {
    WORLD_BROWSER(Material.RECOVERY_COMPASS, "items.world-browser.title"), MY_WORLDS(Material.CHAIN_COMMAND_BLOCK,"items.my-worlds.title"), SUFFIXES(Material.YELLOW_DYE,"items.suffixes.title");
    private final Material item;
    private final String translationKey;
    Items(Material item, String translationKey) {
        this.item = item;
        this.translationKey = translationKey;
    }
    public Item getItem(Language lang) {
        return new Item(item, localizeOnly(translationKey, lang));
    }
    @Deprecated(forRemoval = true,since = "0.5")
    public static Item world_browser = new Item(Material.RECOVERY_COMPASS, "&eБраузер миров");
    @Deprecated(forRemoval = true,since = "0.5")
    public static Item my_worlds = new Item(Material.CHAIN_COMMAND_BLOCK, "&aВаши миры");
    @Deprecated(forRemoval = true,since = "0.5")
    public static Item suffixes = new Item(Material.YELLOW_DYE, "&eСуффиксы");
}
