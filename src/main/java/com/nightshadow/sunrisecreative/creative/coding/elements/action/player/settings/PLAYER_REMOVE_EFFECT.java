package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.settings;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PLAYER_REMOVE_EFFECT extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        for (Player player : selector.players) {
            if (args.length > 0) {
                for (Object arg : args) {
                    player.removePotionEffect(PotionEffectType.getByName(arg.toString()));
                }
            } else player.clearActivePotionEffects();
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT,ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.DRAGON_BREATH, this).experimental();
    }

    @Override
    public String getName() {
        return "Удалить эффект";
    }
}
