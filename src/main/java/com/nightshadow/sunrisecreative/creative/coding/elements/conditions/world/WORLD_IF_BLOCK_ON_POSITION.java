package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.world;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;

public class WORLD_IF_BLOCK_ON_POSITION extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        Material block;
        if (! (args[1] instanceof ItemStack i)) {
            block = Material.valueOf(args[1].toString().toUpperCase());
        } else  block = i.getType();
        return locationFromString(args[0].toString(), selector.getWorld()).getBlock().getType() == block;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.POSITION,ValueType.ITEM );
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.FLOWER_POT, this);
    }

    @Override
    public String getName() {
        return "Если блок равен";
    }
}
