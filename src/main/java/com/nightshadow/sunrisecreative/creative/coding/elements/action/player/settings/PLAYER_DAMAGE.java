package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.settings;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PLAYER_DAMAGE extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            player.damage(parseDouble(args[0].toString()));
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {

        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.NUMBER);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Урон";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }
}
