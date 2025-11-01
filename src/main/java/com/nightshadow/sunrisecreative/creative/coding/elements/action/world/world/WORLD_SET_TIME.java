package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.world;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;

import java.util.List;

public class WORLD_SET_TIME extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        selector.getWorld().setTime(Long.valueOf(args[0].toString()));
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.NUMBER);
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.CLOCK,this).experimental();
    }

    @Override
    public String getName() {
        return "Уст. время";
    }
}
