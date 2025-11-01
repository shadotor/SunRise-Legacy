package com.nightshadow.sunrisecreative.creative.coding.elements.action.player.inventory;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.CodingMenuInitializer;
import com.nightshadow.sunrisecreative.creative.coding.menus.CustomCodingMenu;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Material;

import java.util.List;

public class PLAYER_OPEN_MENU extends Action implements CustomCodingMenu {



    @Override
    public Object execute(Selector selector, Object[] args) {
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of();
    }

    @Override
    public ActionType getType() {
        return ActionType.PLAYER;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.PAINTING,this).experimental().hide();
    }

    @Override
    public String getName() {
        return "Показать меню";
    }

    @Override
    public CodingMenuInitializer getMenuInitializer() {
        return null;
    }
}
