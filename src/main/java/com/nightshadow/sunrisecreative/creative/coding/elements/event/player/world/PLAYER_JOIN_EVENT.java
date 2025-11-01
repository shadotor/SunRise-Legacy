package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_JOIN_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Вход";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.OAK_DOOR, this);
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
