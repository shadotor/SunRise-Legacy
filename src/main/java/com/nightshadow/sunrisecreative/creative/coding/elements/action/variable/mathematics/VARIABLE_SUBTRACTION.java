package com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.mathematics;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;

import java.util.List;

import static java.lang.Double.parseDouble;

public class VARIABLE_SUBTRACTION extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        Variable var = Variable.variableFromString(args[0].toString());
        if (var.getType() == ValueType.NUMBER) {
            var.setValue(parseDouble(var.getValue().toString()) - parseDouble(args[1].toString()));
        }
        return null;
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
        return null;
    }

    @Override
    public String getName() {
        return "-=";
    }
}
