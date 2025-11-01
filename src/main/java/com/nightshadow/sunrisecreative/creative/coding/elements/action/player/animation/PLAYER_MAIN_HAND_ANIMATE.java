package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.animation;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class PLAYER_MAIN_HAND_ANIMATE extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            player.swingMainHand();
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of();
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.IRON_SWORD, this);
    }

    @Override
    public String getName() {
        return "Взмах гл. руки";
    }
}
