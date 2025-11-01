package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.player;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Condition;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static java.lang.Integer.parseInt;

public class PLAYER_IF_SAW_TO_BLOCK extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            Material block = player.getTargetBlockExact(parseInt(args[0].toString())).getType();
            boolean cond = false;
            for (int i = 1; i < args.length; i++) {
                if (( (ItemStack) args[i] ).getType() == block ) cond = true;
            }
            if (! cond) return false;
        }
        return true;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(
                ValueType.NUMBER,
                ValueType.ITEM,
                ValueType.ITEM,
                ValueType.ITEM,
                ValueType.ITEM,
                ValueType.ITEM,
                ValueType.ITEM,
                ValueType.ITEM,
                ValueType.ITEM
        );
    }

    @Override
    public ActionType getType() {
        return null;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.OAK_PLANKS, this).experimental();
    }

    @Override
    public String getName() {
        return "Смотрит на блок";
    }
}
