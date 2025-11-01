package com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;


public class PLAYER_CHAT_EVENT extends CodingEvent {

    @Override
    public String getName() {
        return "Сообщение";
    }
    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.TEST_BLOCK,this).cancellable();
    }

    @Override
    public EventType getType() {
        return EventType.PLAYER;
    }
}
