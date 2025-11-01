package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_QUIT_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Выход";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.IRON_DOOR,this).experimental();
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
