package com.nightshadow.sunrisecreative.creative.coding.elements.action.entity;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

import java.util.List;

public class ENTITY_DELETE extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Entity entity : selector.entities) {
            entity.remove();
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of();
    }

    @Override
    public ActionType getType() {
        return ActionType.ENTITY;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.BARRIER,this).experimental();
    }

    @Override
    public String getName() {
        return "Удалить";
    }
}
