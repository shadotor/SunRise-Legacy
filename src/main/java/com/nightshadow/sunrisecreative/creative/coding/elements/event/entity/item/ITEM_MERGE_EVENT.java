package com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.item;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class ITEM_MERGE_EVENT extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.ENTITY;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.SLIME_BLOCK,this).cancellable();
    }

    @Override
    public String getName() {
        return "Предметы объед.";
    }
}
