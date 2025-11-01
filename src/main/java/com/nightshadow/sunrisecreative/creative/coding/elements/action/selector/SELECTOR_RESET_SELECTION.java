package com.nightshadow.sunrisecreative.creative.coding.elements.action.selector;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class SELECTOR_RESET_SELECTION extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        selector.setSelector(new Entity[0], new Player[0]);
        return selector;
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
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.STRUCTURE_VOID,this);
    }

    @Override
    public String getName() {
        return "Очистить";
    }
}
