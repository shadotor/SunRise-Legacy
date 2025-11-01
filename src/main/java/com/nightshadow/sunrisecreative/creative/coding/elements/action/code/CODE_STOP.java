package com.nightshadow.sunrisecreative.creative.coding.elements.action.code;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;

import java.util.List;

public class CODE_STOP extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        stopRun(selector);
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of();
    }

    @Override
    public ActionType getType() {
        return ActionType.CODE;
    }

    @Override
    public String getName() {
        return "Остановить";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }
}
