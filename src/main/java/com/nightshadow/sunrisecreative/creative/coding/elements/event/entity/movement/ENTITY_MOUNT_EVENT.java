package com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.movement;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Material;

public class ENTITY_MOUNT_EVENT extends CodingEvent {
    @Override
    public EventType getType() {
        return EventType.ENTITY;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.SADDLE,this).cancellable();
    }

    @Override
    public String getName() {
        return "Село на сущ.";
    }

    public static class ENTITY_DEATH_EVENT extends CodingEvent {
        @Override
        public EventType getType() {
            return EventType.ENTITY;
        }

        @Override
        public SelectionIcon getIcon() {
            return new SelectionIcon(Material.SKELETON_SKULL,this).cancellable();
        }

        @Override
        public String getName() {
            return "Умирает";
        }
    }
}
