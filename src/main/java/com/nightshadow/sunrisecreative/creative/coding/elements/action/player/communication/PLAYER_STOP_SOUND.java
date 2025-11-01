package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.communication;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.entity.Player;

import java.util.List;

public class PLAYER_STOP_SOUND extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        if (args.length > 0) {
            for (Object arg : args) {
                for (Player player : selector.players) {
                    player.stopSound(arg.toString());
                }
            }
        }
        else for (Player player : selector.players) {
            player.stopAllSounds();
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT);
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
