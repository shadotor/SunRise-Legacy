package com.nightshadow.sunrisecreative.creative.coding.menus;

import com.nightshadow.sunrisecreative.api.utils.Language;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;

import java.util.List;

public class CodingMenuInitializer implements CodingMenuBuilder
{
    Action action;
    Language lang;
    List<Integer> argSlot;

    public CodingMenu build() {
        return new CodingMenu(this);
    }
    public CodingMenuInitializer setAction(Action action) {
        this.action = action;
        return this;
    }
    public CodingMenuInitializer setArgSlots(List<Integer> list) {
        this.argSlot = list;
        return this;
    }
    public CodingMenuInitializer setLanguage(Language lang) {
        this.lang = lang;
        return this;
    }
}
