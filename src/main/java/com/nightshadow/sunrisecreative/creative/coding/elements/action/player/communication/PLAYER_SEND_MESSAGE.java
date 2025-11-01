package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.communication;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.creative.coding.parsing.Placeholders.getPlaceholder;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

public class PLAYER_SEND_MESSAGE extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        String[] stringArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            stringArgs[i] = getPlaceholder(String.valueOf(args[i]),selector);
        }
        if (selector.getType().equals("PlayerSelector")) {
            for (int i = 0; i < selector.players.length; i++) {
                selector.players[i].sendMessage(parseColor(String.join(" ",stringArgs)));
            }
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        List<ValueType> args = new ArrayList<>();
        args.add(ValueType.TEXT);
        return args;
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public String getName() {
        return "Сообщение";
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.BOOK, this);
    }
}
