package com.nightshadow.sunrisecreative.creative.coding.primitives;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;

import java.util.List;

public abstract class Action extends CodeElement implements Actional{
    public abstract Object execute(Selector selector, Object[] args);;
    public void stopRun(Selector selector) {
        selector.getRunner().stop();
    }
}
