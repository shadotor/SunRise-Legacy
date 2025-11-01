package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.communication;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import net.kyori.adventure.title.Title;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.creative.coding.parsing.Placeholders.getPlaceholder;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

public class PLAYER_SEND_TITLE extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        if (selector.getType().equals("PlayerSelector")) {
            for (int i = 0; i < selector.players.length; i++) {
                selector.players[i].showTitle(Title.title(parseColor(getPlaceholder(args[0].toString(),selector)), parseColor(getPlaceholder(args[1].toString(),selector))));
            }
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.TEXT);
        args.add(ValueType.TEXT);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Отправить титл";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.OAK_HANGING_SIGN,this);
    }
}
