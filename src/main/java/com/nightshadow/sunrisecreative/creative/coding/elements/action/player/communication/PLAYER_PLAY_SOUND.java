package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.communication;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.entity.Player;

import java.util.List;

import static java.lang.Float.parseFloat;

public class PLAYER_PLAY_SOUND extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            player.playSound(player.getLocation(), args[0].toString(), parseFloat(args[1].toString()),parseFloat(args[2].toString()));
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT,ValueType.NUMBER,ValueType.NUMBER);
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }
}
