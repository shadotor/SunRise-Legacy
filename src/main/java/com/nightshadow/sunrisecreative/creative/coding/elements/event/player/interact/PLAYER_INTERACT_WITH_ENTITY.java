package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.interact;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_INTERACT_WITH_ENTITY extends CodingEvent {

    @Override
    public String getName() {
        return "Взаимодействует с сущностью";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.DIAMOND, this).cancellable();
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}