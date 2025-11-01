package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.player;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class PLAYER_IF_GAMEMODE_EQUALS extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            if (! player.getGameMode().name().equals(args[0].toString().toUpperCase())) return false;
        }
        return true;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.GOLDEN_PICKAXE,this);
    }

    @Override
    public String getName() {
        return "Режим игры ==";
    }
}
