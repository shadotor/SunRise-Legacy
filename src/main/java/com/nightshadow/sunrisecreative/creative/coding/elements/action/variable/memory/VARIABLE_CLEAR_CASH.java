package com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.memory;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.world.WorldVariableData;
import org.bukkit.Material;

import java.util.List;

public class VARIABLE_CLEAR_CASH extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        WorldVariableData data = new WorldVariableData(selector.getRunner().getReader().getWorld());
        data.clearCash();
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of();
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.LAVA_BUCKET,this).experimental();
    }

    @Override
    public String getName() {
        return "Очистить кэш";
    }
}
