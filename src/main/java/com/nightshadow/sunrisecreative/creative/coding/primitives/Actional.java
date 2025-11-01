package com.nightshadow.sunrisecreative.creative.coding.primitives;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;

import java.util.List;

public interface Actional {
    List<ValueType> getArguments();
    ActionType getType();
}
