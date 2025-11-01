package com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.text;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import org.bukkit.Material;

import java.util.List;

public class VARIABLE_COMBINE_TEXT extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        Variable var = Variable.variableFromString(args[0].toString());
        var.setValue(args[1].toString() + args[2].toString());
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.VARIABLE,ValueType.TEXT,ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.CHISELED_BOOKSHELF,this).experimental();
    }

    @Override
    public String getName() {
        return "Совместить текст";
    }
}
