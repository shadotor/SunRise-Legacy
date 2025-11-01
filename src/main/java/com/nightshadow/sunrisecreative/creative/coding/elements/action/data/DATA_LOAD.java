package com.nightshadow.sunrisecreative.creative.coding.elements.action.data;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.exceptions.IncompatibleTypesException;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import com.nightshadow.sunrisecreative.creative.world.WorldVariableData;
import org.bukkit.Material;

import java.util.List;

public class DATA_LOAD extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        if (args[0] instanceof Variable var) {
            var.setValue(new WorldVariableData(selector.getRunner().getReader().getWorld()).getData(args[1].toString()));
        } else throw new IncompatibleTypesException(ValueType.VARIABLE);
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.VARIABLE, ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.DATA;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.CHISELED_BOOKSHELF,this).experimental();
    }

    @Override
    public String getName() {
        return "Загрузить";
    }
}
