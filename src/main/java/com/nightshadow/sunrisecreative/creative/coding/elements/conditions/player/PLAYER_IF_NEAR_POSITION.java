package com.nightshadow.sunrisecreative.creative.coding.elements.conditions.player;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PLAYER_IF_NEAR_POSITION extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        Location loc = locationFromString(args[0].toString(),selector.getWorld());
        return loc.getNearbyPlayers(parseDouble(args[1].toString())).containsAll(Arrays.asList(selector.players));
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.POSITION);
        args.add(ValueType.NUMBER);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Если рядом";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.COMPASS,this);
    }
}
