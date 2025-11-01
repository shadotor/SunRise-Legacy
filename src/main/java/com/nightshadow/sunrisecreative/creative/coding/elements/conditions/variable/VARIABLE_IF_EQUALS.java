package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.variable;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.creative.coding.parsing.Placeholders.getPlaceholder;

public class VARIABLE_IF_EQUALS extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        return getPlaceholder(args[0].toString(),selector).equals(getPlaceholder(args[1].toString(),selector));
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.VALUE);
        args.add(ValueType.VALUE);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public String getName() {
        return "==";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }
}
