package com.nightshadow.sunrisecreative.indev.api.database;

import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

@ApiStatus.Experimental
public interface Serializable {
    String serializeString();
    Map<String, Object> serializeMap();
    Serializable deserializeString(String s);
    Serializable deserializeMap(Map<String,Object> map);

}
