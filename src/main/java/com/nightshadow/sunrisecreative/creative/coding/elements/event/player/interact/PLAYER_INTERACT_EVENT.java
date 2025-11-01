package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.interact;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_INTERACT_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Взаим. с миром";
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
