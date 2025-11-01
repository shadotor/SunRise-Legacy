package com.nightshadow.sunrisecreative.creative.coding.elements.action.selector;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.entity.Player;

import java.util.List;

public class SELECTOR_SELECT_ALL_PLAYERS extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        Selector ret = new Selector();
        ret.setSelector(selector.getWorld().getPlayers().toArray(new Player[selector.getWorld().getPlayers().size()]));
        return ret;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of();
    }

    @Override
    public ActionType getType() {
        return ActionType.SELECTOR;
    }

    @Override
    public String getName() {
        return "Все игроки";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }
}
