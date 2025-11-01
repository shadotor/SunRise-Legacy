package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.*;

public class WORLD_FILL_REGION extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        if (args[0] instanceof ItemStack i) {
            fillBlock(locationFromString(args[1].toString(),selector.getWorld()),locationFromString(args[2].toString(),selector.getWorld()), i.getType());
        } else fillBlock(locationFromString(args[1].toString(),selector.getWorld()),locationFromString(args[2].toString(),selector.getWorld()), Material.valueOf(args[0].toString()));
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.ITEM,ValueType.POSITION,ValueType.POSITION);
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.LIME_STAINED_GLASS,this).experimental();
    }

    @Override
    public String getName() {
        return "Заполнить";
    }
}
