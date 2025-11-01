package com.nightshadow.sunrisecreative.creative.coding.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String event) {
        super("Can't find event: " + event);
    }
}
