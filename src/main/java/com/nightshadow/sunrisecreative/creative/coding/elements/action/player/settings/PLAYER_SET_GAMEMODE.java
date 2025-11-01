package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.settings;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;
import static java.lang.Integer.parseInt;

public class PLAYER_SET_GAMEMODE extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
            for (Player player : selector.players) {
                Bukkit.getScheduler().runTask(getInstance(), () -> {player.setGameMode(GameMode.valueOf(args[0].toString().toUpperCase()));});
            }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.TEXT);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Режим игры";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.GOLDEN_PICKAXE,this);
    }

}
