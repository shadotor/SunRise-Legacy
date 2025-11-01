package com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.inventory;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class ENTITY_PICKUP_ITEM extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.ENTITY;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.HOPPER,this).cancellable();
    }

    @Override
    public String getName() {
        return "Подняла предмет";
    }
}
