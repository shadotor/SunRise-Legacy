package com.nightshadow.sunrisecreative.creative.coding.menus;

import com.nightshadow.sunrisecreative.api.utils.Language;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodeElement;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.api.menu.Item;
import org.bukkit.Material;

import java.util.ArrayList;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.localizeOnly;
import static com.nightshadow.sunrisecreative.creative.coding.menus.CodingMenuBuilder.getTypeItem;

public class SelectionIcon {
    boolean hidden = false;
    Item item;
    CodeElement element;
    public SelectionIcon(Material icon, CodeElement element)
    {
        String prefix = "";
        if (element instanceof Action) prefix = "action.";
        if (element instanceof CodingEvent) prefix = "event.";
        this.element = element;
        item = new Item(icon, prefix+element.getClass().getSimpleName()+".name");
        item.setDescription(new ArrayList<>());
    }
    public SelectionIcon experimental() {
        item.setEnchantmentGlint(true);
        item.addLore("&6⚠ Experimental");
        return this;
    }
    public SelectionIcon cancellable() {
        item.addLore("&6\uD83D\uDEC7 Cancellable");
        return this;
    }
    public SelectionIcon forRemoval() {
        item.addLore("&c\uD83D\uDDD9 For removal");
        return this;
    }
    public SelectionIcon deprecated() {
        item.addLore("&c⚠ Deprecated");
        return this;
    }
    public Item getItem(Language lang) {
        Item item = this.item;
        item.setName(localizeOnly(item.getName(),lang));
        String prefix = "";
        if (element instanceof Action) prefix = "action.";
        if (element instanceof CodingEvent) prefix = "event.";
        item.addLore(0, " ");
        item.addLore(1, localizeOnly(prefix+element.getClass().getSimpleName()+".description", lang));
        item.addLore(2, " ");
        if (element instanceof Action act) {
            item.addLore(act.getArguments().size()+3, "&eАргументы:");
            for (int i = 0; i < act.getArguments().size(); i++) {
                ValueType argument = act.getArguments().get(i);
                String name = getTypeItem(argument).getItem().getItemMeta().getDisplayName();
                item.addLore(i+3, name);
            }
            if (act.getArguments().isEmpty()) item.addLore(3,"&cНе требует аргументов");
            else item.addLore(3, " ");
        }
        return item;
    }
    public SelectionIcon hide() {
        item.addLore("&7\uD83D\uDC41 Hidden");
        hidden = true;
        return this;
    }
    public boolean isHidden() {
        return hidden;
    }
}
