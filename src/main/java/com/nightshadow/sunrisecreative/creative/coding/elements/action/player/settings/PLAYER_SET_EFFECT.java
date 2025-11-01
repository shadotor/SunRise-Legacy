package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.settings;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class PLAYER_SET_EFFECT extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(args[0].toString().toUpperCase()),parseInt(args[1].toString()),parseInt(args[2].toString())));
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.TEXT);
        args.add(ValueType.NUMBER);
        args.add(ValueType.NUMBER);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Эффект";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.POTION, this);
    }
}
