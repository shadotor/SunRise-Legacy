package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.inventory;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PLAYER_SET_ARMOR extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        ItemStack[] armor = new ItemStack[4];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ItemStack) armor[i] = (ItemStack) args[i];
            else armor[i] = null;
        }
        for (Player player : selector.players) {
            player.getInventory().setArmorContents(armor);
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.ITEM, ValueType.ITEM, ValueType.ITEM, ValueType.ITEM);
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.IRON_CHESTPLATE,this).experimental();
    }

    @Override
    public String getName() {
        return "Уст. броню";
    }
}
