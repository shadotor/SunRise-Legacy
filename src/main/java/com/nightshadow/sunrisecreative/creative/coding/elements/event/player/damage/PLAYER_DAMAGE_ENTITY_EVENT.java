package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_DAMAGE_ENTITY_EVENT extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.GOLDEN_SWORD, this).cancellable();
    }

    @Override
    public String getName() {
        return "Ранит существо";
    }
}
