package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.setBlock;
import static java.lang.Integer.parseInt;

public class WORLD_SET_RANDOM_BLOCK extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        int materialIndex = new Random().nextInt(args.length - 1) + 1;
        Location location = locationFromString(args[0].toString(), selector.getWorld());
        Bukkit.getScheduler().runTask(getInstance(), () -> {
            if (! (args[materialIndex] instanceof ItemStack i) ) {
                setBlock(location, Material.valueOf(args[materialIndex].toString().toUpperCase()));
            }
            else setBlock(location,i.getType());
        });

        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.POSITION);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public String getName() {
        return "Уст. случ. блок";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.STONE_BRICKS,this);
    }
}
