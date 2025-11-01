package com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.damage;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class ENTITY_DAMAGE_EVENT extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.ENTITY;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.REDSTONE,this).cancellable();
    }

    @Override
    public String getName() {
        return "Получает урон";
    }
}
