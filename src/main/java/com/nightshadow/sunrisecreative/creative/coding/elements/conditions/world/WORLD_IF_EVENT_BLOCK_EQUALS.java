package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.world;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.event.block.BlockEvent;

import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;

public class WORLD_IF_EVENT_BLOCK_EQUALS extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        if (selector.getRunner().getEvent() instanceof BlockEvent event) {
            sendConsole(event.getBlock().getLocation().toString());
            sendConsole(locationFromString(args[0].toString(),selector.getWorld()).toString());
            if (! event.getBlock().getLocation().equals(locationFromString(args[0].toString(),selector.getWorld()))) return false;
        } else return false;
        return true;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.POSITION);
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.DIAMOND_BLOCK,this);
    }

    @Override
    public String getName() {
        return "Блок события равен";
    }
}
