package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.variable;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import org.bukkit.Material;

import java.util.List;

public class VARIABLE_IF_EXISTS extends Condition {

    @Override
    public boolean comparate(Selector selector, Object[] args) {
        Variable var = Variable.variableFromString(args[0].toString());
            return var.getValue() != null;
        }
    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.VARIABLE);
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.EGG,this).experimental();
    }

    @Override
    public String getName() {
        return "";
    }
}
