package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_DAMAGE_EVENT extends CodingEvent {


    @Override
    public String getName() {
        return "Получает урон";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.REDSTONE,this).cancellable();
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
