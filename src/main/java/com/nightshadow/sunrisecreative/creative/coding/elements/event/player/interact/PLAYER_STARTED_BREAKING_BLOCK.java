package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.interact;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_STARTED_BREAKING_BLOCK extends CodingEvent {
    @Override
    public String getName() {
        return "Начал лом. блок";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.WOODEN_PICKAXE,this).cancellable().experimental();
    }

    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
