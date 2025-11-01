package com.nightshadow.sunrisecreative.creative.coding.elements.action.selector;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class SELECTOR_SELECT_PLAYER_BY_NAME extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        Selector ret = new Selector();
        selector.getWorld().getPlayers().forEach((p) -> {
            if (p.getName().equals(args[0].toString())) ret.setSelector(new Player[] {p});
        });
        return ret;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.SELECTOR;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.NAME_TAG,this).experimental();
    }

    @Override
    public String getName() {
        return "По имени";
    }
}
