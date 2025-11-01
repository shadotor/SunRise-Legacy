package com.nightshadow.sunrisecreative.api.menu.menus;

import com.nightshadow.sunrisecreative.api.menu.Menu;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.entity.Player;

@Deprecated(forRemoval = true)
public class Suffixes extends Menu {
    Menu ui;
    Player owner;

    SuffixNode node = SuffixNode.builder("[Some Suffix]", 150).build();
}
