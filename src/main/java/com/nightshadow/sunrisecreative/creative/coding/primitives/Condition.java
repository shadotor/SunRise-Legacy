package com.nightshadow.sunrisecreative.creative.coding.primitives;

import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import org.jetbrains.annotations.ApiStatus;

/**
 * Класс условия
 */
@ApiStatus.Experimental
public abstract class Condition extends Action implements Conditional {
    public abstract boolean comparate(Selector selector, Object[] args);
    public Object execute(Selector selector, Object[] args) {
        if (! comparate(selector, args)) {
            selector.getRunner().conditonBegin();
        }
        return null;
    }
    @Override
    public ConditionalType getConditionalType() {return ConditionalType.PREVIOUS;}
}
