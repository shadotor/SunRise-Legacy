package com.nightshadow.sunrisecreative.creative.world.exception;

public class WorldNotFoundException extends RuntimeException {
    public WorldNotFoundException(int ID) {
        super("Not found world with ID: " + ID);
    }
}
