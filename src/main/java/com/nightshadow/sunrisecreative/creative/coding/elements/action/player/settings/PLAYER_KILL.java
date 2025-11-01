package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.settings;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;

public class PLAYER_KILL extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            Bukkit.getScheduler().runTask(getInstance(), () -> player.setHealth(0));
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
    public String getName() {
        return "Убить";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.SKELETON_SKULL, this);
    }
}
