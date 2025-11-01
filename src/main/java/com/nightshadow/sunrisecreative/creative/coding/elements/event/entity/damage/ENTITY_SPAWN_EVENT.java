package com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.damage;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class ENTITY_SPAWN_EVENT extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.ENTITY;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.ZOMBIE_SPAWN_EGG,this).cancellable();
    }

    @Override
    public String getName() {
        return "Спавн";
    }
}
