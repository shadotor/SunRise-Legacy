package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.inventory;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;

public class PLAYER_GIVE_RANDOM_ITEM extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        Bukkit.getScheduler().runTaskLater(getInstance(), () -> {
            for (Player player : selector.players) {
                int materialIndex = new Random().nextInt(args.length);
                if (!(args[materialIndex] instanceof ItemStack i)) {
                    Material material = Material.valueOf(args[materialIndex].toString().replace("minecraft:", "").toUpperCase());
                    player.give(new ItemStack(material));
                } else player.give(i);
            }
        },1);
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        args.add(ValueType.ITEM);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Случ. предмет";
    }

    @Override
    public SelectionIcon getIcon() {
        return null;
    }
}
