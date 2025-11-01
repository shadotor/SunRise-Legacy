package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_RESPAWN_EVENT extends CodingEvent {


    @Override
    public String getName() {
        return "Возрождение";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.TOTEM_OF_UNDYING,this);
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
