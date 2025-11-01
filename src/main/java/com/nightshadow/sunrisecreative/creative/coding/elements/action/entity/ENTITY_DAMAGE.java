package com.nightshadow.sunrisecreative.creative.coding.elements.action.entity;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ENTITY_DAMAGE extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Entity entity : selector.entities) {
            if (entity instanceof LivingEntity livingEntity) livingEntity.damage(parseDouble(args[0].toString()));
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
        return ActionType.ENTITY;
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
