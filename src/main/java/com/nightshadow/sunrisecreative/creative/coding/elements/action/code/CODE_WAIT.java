package com.nightshadow.sunrisecreative.creative.coding.elements.action.code;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Math.round;

public class CODE_WAIT extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {

        double wait = parseDouble(args[0].toString());
        long w = round(wait);
        sendConsole("Waiting " + w + " ticks");
        selector.getRunner().sleep(w);
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.NUMBER);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.CODE;
    }

    @Override
    public String getName() {
        return "Ждать";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }
}
