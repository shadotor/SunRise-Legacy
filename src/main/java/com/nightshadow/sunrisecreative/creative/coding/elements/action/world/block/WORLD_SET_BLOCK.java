package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.setBlock;
import static java.lang.Integer.parseInt;

public class WORLD_SET_BLOCK extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        if (args.length == 2) {
            Location loc = locationFromString(args[0].toString(), selector.getWorld());
            if (!(args[1] instanceof ItemStack i)) {
                setBlock(loc, Material.valueOf(args[1].toString().toUpperCase()));
            } else setBlock(loc, i.getType());
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.POSITION);
        args.add(ValueType.ITEM);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public String getName() {
        return "Уст. блок";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.BRICKS,this);
    }
}
