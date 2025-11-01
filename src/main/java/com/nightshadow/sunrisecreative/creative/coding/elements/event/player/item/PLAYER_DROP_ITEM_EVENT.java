package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_DROP_ITEM_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Выбр. предмет";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.SUGAR,this).cancellable();
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
