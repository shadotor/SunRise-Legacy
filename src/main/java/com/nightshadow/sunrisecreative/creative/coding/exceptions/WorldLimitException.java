package com.nightshadow.sunrisecreative.creative.coding.exceptions;

public class WorldLimitException extends RuntimeException {
    public WorldLimitException() {
        super("Превышен один из лимитов мира!");
    }
}
