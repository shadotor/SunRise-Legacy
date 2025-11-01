package com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.memory;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import org.bukkit.Material;

import java.util.List;

public class VARIABLE_SET_VALUE extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        Variable v = Variable.variableFromString(args[0].toString());
        v.setValue(args[1]);
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.VARIABLE, ValueType.VALUE);
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.IRON_INGOT,this).experimental();
    }

    @Override
    public String getName() {
        return "=";
    }
}
