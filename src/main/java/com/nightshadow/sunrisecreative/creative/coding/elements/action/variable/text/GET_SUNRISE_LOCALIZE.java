package com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.text;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import org.bukkit.Material;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.localizeOnly;
import static com.nightshadow.sunrisecreative.api.utils.Language.findLang;

public class GET_SUNRISE_LOCALIZE extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        Variable var = Variable.variableFromString(args[0].toString());
        var.setValue(localizeOnly(args[1].toString(),findLang(args[2].toString())));
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.VARIABLE,ValueType.TEXT, ValueType.TEXT);
    }

    @Override
    public ActionType getType() {
        return ActionType.VARIABLE;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.KNOWLEDGE_BOOK, this).experimental();
    }

    @Override
    public String getName() {
        return "Перевод. сервера";
    }
}
