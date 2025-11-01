package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.variable;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import org.bukkit.Material;

import java.util.List;

import static java.lang.Double.parseDouble;

public class VARIABLE_IF_GREATER_OR_EQUALS extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        Variable var = Variable.variableFromString(args[0].toString());
        if (var.getType() == ValueType.NUMBER) {
            return !(parseDouble(var.getValue().toString()) < parseDouble(args[1].toString()));
        } else return false;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.VARIABLE,ValueType.NUMBER);
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.IRON_INGOT,this);
    }

    @Override
    public String getName() {
        return ">=";
    }
}
