package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class PLAYER_ITEM_BREAK_EVENT extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.IRON_PICKAXE,this).cancellable();
    }

    @Override
    public String getName() {
        return "Ломает предмет";
    }
}
