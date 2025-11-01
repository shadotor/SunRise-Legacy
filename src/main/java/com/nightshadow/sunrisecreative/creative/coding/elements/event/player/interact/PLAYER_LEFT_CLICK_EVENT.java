package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.interact;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_LEFT_CLICK_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Левый клик";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.IRON_SWORD,this).cancellable();
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}

