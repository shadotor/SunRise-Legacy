package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.event;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;

import java.util.List;

public class WORLD_RETURN_EVENT extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        if (selector.getRunner().getEvent() != null) {
            if (selector.getRunner().getEvent() instanceof Cancellable cancellable) {
                cancellable.setCancelled(false);
            }
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of();
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.STRUCTURE_VOID,this);
    }

    @Override
    public String getName() {return "Возврат события";}
}
