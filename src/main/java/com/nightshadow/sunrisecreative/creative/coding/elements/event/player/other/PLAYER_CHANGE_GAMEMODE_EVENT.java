package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_CHANGE_GAMEMODE_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Меняет реж. игры";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.GOLD_BLOCK,this).cancellable().experimental();
    }

    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
