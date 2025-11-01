package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.inventory;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class  PLAYER_GIVE_ITEM extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
            for (Player player : selector.players) {
                for (Object arg : args) {
                    try {
                        ItemStack i = (ItemStack) arg;
                        player.give(i);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        Material material = Material.valueOf(arg.toString().replace("minecraft:", "").toUpperCase());
                        player.give(new ItemStack(material));
                    }
                }
            }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.ITEM);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Выдать предмет";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }
}
