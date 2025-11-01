package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.communication;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

public class PLAYER_SEND_NOTICE extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        DialogBase dialog = DialogBase.builder(parseColor(args[0].toString())).afterAction(DialogBase.DialogAfterAction.CLOSE).body(List.of(DialogBody.plainMessage(parseColor(args[1].toString())))).build();
        for (Player player : selector.players) {
            player.showDialog(Dialog.create((d) -> {
                d.empty().base(dialog).type(DialogType.notice());
            }));
        }
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.TEXT, ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.BELL, this).experimental();
    }

    @Override
    public String getName() {
        return "Оповещение";
    }
}
