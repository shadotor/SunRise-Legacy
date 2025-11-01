package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.interact;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_PLACE_BLOCK_EVENT extends CodingEvent {
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.BRICKS, this).cancellable();
    }

    @Override
    public String getName() {
        return "Ставит блок";
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
