package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.communication;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

public class PLAYER_SEND_DIALOG extends Action {

    @Override
    public Object execute(Selector selector, Object[] args) {
        try {
        DialogBase dialog = DialogBase.builder(parseColor(args[0].toString())).afterAction(DialogBase.DialogAfterAction.CLOSE).body(List.of()).build();
            for (Player player : selector.players) {
                player.showDialog(Dialog.create((d) -> {
                    d.empty().base(dialog).type(DialogType.notice());
                }));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.OAK_SIGN, this).experimental().forRemoval().hide();
    }

    @Override
    public String getName() {
        return "Диалог";
    }
}
