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

public class PLAYER_IF_HOLD_ITEM extends Condition {
    @Override
    public boolean comparate(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            if (! player.getItemInHand().equals( (ItemStack) args[0])) return false;
        }
        return true;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.ITEM);
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.WOODEN_PICKAXE,this);
    }

    @Override
    public String getName() {
        return "Держит предмет";
    }
}
