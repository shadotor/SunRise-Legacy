package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;

public class WORLD_SET_BIOME extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        selector.getWorld().setBiome(locationFromString(args[0].toString(),selector.getWorld()), Biome.valueOf(args[1].toString()));
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.POSITION, ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.DIRT,this).experimental();
    }

    @Override
    public String getName() {
        return "Уст. биом";
    }
}
