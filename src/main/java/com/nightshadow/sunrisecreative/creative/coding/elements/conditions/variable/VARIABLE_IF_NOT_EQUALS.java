package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.variable;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;

import java.util.List;

import static com.nightshadow.sunrisecreative.creative.coding.parsing.Placeholders.getPlaceholder;

public class VARIABLE_IF_NOT_EQUALS extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        if (getPlaceholder(args[0].toString(),selector).equals(getPlaceholder(args[1].toString(),selector))) return false;
        return true;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.VALUE, ValueType.VALUE);
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.STRUCTURE_VOID,this);
    }

    @Override
    public String getName() {
        return "!=";
    }
}
