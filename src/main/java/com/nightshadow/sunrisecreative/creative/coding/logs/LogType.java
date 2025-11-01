package com.nightshadow.sunrisecreative.creative.coding.logs;

import org.jetbrains.annotations.ApiStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@ApiStatus.Experimental
public enum LogType {
    INFO("&f","INFO"), WARNING("&6", "WARNING"), ERROR("&c", "ERROR"), DEBUG("&b", "DEBUG");
    private final String color;
    private final String prefix;
    LogType(String color, String prefix) {
        this.color = color;
        this.prefix = prefix;
    }
    public String getMessage(String content) {
        return color + "[" + new SimpleDateFormat("dd.MM.yyyy:hh:mm:ss").format(new Date(System.currentTimeMillis())) + " " + prefix + "] " + content;
    }
}
