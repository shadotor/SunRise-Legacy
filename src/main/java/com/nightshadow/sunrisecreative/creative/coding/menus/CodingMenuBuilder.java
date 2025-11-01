package com.nightshadow.sunrisecreative.creative.coding.menus;

import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.api.menu.Item;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;

public interface CodingMenuBuilder {
    Item valueItem = new Item(Material.WHITE_STAINED_GLASS_PANE, "&fОбъект");
    Item stringItem = new Item(Material.BLUE_STAINED_GLASS_PANE, "&bТекст");
    Item numberItem = new Item(Material.RED_STAINED_GLASS_PANE, "&cЧисло");
    Item variableItem = new Item(Material.YELLOW_STAINED_GLASS_PANE, "&eПеременная");
    Item componentItem = new Item(Material.LIME_STAINED_GLASS_PANE, "&aТекстовый компонент");
    Item positionItem = new Item(Material.GREEN_STAINED_GLASS_PANE, "&2Местоположение");
    Item vectorItem = new Item(Material.CYAN_STAINED_GLASS_PANE, "&3Вектор");
    Item itemValueItem = new Item(Material.ORANGE_STAINED_GLASS_PANE, "&6Предмет");
    Item unknownValueItem = new Item(Material.BARRIER, "&cНеизвестное значение");

    static Item getTypeItem(ValueType type) {
        if (type == ValueType.VALUE) return valueItem;
        if (type == ValueType.TEXT) return stringItem;
        if (type == ValueType.TEXT_COMPONENT) return componentItem;
        if (type == ValueType.CONSTANT || type == ValueType.VARIABLE) return variableItem;
        if (type == ValueType.VECTOR) return vectorItem;
        if (type == ValueType.POSITION) return positionItem;
        if (type == ValueType.NUMBER) return  numberItem;
        if (type == ValueType.ITEM) return  itemValueItem;
        return unknownValueItem;
    }

    static CodingMenuInitializer initializer() {
        return new CodingMenuInitializer();
    }
    static List<Integer> range(int from, int to) {
        List<Integer> slots = new ArrayList<>();
        for(int i = from; i <= to; i++) {
            slots.add(i);
        }
        return slots;
    }
}
