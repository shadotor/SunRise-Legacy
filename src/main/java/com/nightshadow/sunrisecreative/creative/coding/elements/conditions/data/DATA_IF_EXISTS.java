package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.data;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.world.WorldVariableData;
import org.bukkit.Material;

import java.util.List;

public class DATA_IF_EXISTS extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        return new WorldVariableData(selector.getRunner().getReader().getWorld()).getData(args[0].toString()) != null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.DATA;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.TURTLE_EGG,this);
    }

    @Override
    public String getName() {
        return "Существует";
    }
}
