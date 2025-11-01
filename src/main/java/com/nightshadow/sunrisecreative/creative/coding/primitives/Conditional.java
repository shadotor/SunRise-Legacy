package com.nightshadow.sunrisecreative.creative.coding.primitives;

import org.jetbrains.annotations.ApiStatus;

/**
 * Главный интерфейс для блоков имеющих условную реализацию(как условия, со скобками)
 */
@ApiStatus.Experimental
public interface Conditional extends Actional {
    ConditionalType getConditionalType();
}
