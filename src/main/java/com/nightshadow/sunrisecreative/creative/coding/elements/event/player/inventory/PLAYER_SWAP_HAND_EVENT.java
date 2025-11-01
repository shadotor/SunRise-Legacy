package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.inventory;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_SWAP_HAND_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Меняет руку";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.TRIPWIRE_HOOK,this).cancellable();
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
