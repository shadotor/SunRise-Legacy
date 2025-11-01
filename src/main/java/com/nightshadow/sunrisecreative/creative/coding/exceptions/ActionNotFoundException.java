package com.nightshadow.sunrisecreative.creative.coding.exceptions;

public class ActionNotFoundException extends RuntimeException {
    public ActionNotFoundException(String event) {
        super("Can't find action: " + event);
    }
}
