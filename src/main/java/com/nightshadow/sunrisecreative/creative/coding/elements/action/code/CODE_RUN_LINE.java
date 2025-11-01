package com.nightshadow.sunrisecreative.creative.coding.elements.action.code;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Reader;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;
import static java.lang.Integer.parseInt;

public class CODE_RUN_LINE extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
            int lineToRun = parseInt(args[0].toString());
            Reader reader = new Reader(getWorldWhere(selector.players[0]));
            reader.setLine(lineToRun);
            reader.setColumn(0);
            reader.setSelector(selector);
            reader.runLine();

        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.NUMBER);
        return args;
    }

    @Override
    public String getName() {
        return "Запустить линию";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }

    @Override
    public ActionType getType() {
        return ActionType.CODE;
    }

}
