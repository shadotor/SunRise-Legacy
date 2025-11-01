package com.nightshadow.sunrisecreative.creative.coding.elements.event.world;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class FURNACE_SMELT_EVENT extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.COAL,this).experimental();
    }

    @Override
    public String getName() {
        return "Печь плавит";
    }
}
