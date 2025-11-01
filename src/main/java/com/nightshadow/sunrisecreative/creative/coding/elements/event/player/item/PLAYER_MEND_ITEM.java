package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_MEND_ITEM extends CodingEvent {


    @Override
    public String getName() {
        return "Чинит починкой";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.ENCHANTED_BOOK,this).cancellable().experimental();
    }
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
