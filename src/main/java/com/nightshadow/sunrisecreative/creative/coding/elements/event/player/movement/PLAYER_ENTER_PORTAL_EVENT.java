package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_ENTER_PORTAL_EVENT extends CodingEvent {

    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.ENDER_EYE,this).experimental();
    }

    @Override
    public String getName() {
        return "Вход в портал";
    }
}
