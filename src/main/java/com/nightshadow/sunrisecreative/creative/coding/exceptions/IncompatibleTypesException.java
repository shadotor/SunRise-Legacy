package com.nightshadow.sunrisecreative.creative.coding.exceptions;

import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;

public class IncompatibleTypesException extends RuntimeException {
    public IncompatibleTypesException(ValueType vt) {
      super("Несовместимые типы, требуется " + vt.name());
    }
}
