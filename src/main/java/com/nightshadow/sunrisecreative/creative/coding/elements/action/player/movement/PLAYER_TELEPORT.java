package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.movement;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;

public class PLAYER_TELEPORT extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            player.teleport(locationFromString(args[0].toString(),selector.getWorld()));
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.POSITION);
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.ENDER_PEARL,this);
    }

    @Override
    public String getName() {
        return "Телепорт";
    }
}
