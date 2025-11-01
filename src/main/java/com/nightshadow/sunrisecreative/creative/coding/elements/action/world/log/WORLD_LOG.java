package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.log;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.logs.LogType;
import com.nightshadow.sunrisecreative.creative.coding.logs.Logs;
import org.bukkit.Material;

import java.util.List;

public class WORLD_LOG extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        Logs logs = new Logs(selector.getRunner().getReader().getWorld());
        logs.addLine(args[0].toString(), LogType.INFO);
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.OAK_SIGN,this).experimental();
    }

    @Override
    public String getName() {
        return "Лог";
    }
}
