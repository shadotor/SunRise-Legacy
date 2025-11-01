package com.nightshadow.sunrisecreative.creative.coding.parsing;

import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerEvent;

import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.roundLocation;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.stringFromLocation;

public abstract class Placeholders {
    public static String getPlaceholder(String s, Selector selector) {
        if (selector.getRunner() != null) {
            if (selector.getRunner().getEvent() instanceof PlayerEvent) {
                s = s.replaceAll("%player%", ((PlayerEvent) selector.getRunner().getEvent()).getPlayer().getName());
            } else {
                s = s.replaceAll("%player%", selector.players[0].getName());
            }

            s = s.replaceAll("%selector%", selector.players[0].getName());
            s = s.replaceAll("%selector_position%", stringFromLocation(roundLocation(selector.players[0].getLocation())));
            s = s.replaceAll("%item%", selector.players[0].getInventory().getItemInMainHand().getType().name());
            s = s.replaceAll("%unix%", String.valueOf((System.currentTimeMillis() + 10800)));
            if (selector.players[0].getInventory().getItemInMainHand().getType() != Material.AIR)
                s = s.replaceAll("%item_name%", selector.players[0].getInventory().getItemInMainHand().getItemMeta().getDisplayName());

            if (s.startsWith("SRV.ValueOf()//")) {
                Variable source = Variable.variableFromString(s.replace(".ValueOf()", ""));
                s = s.replace(s, source.getValue().toString());
            }
        }

        return s;
    }
}
