package com.nightshadow.sunrisecreative.creative.command.creative;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MyWorlds implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        new com.nightshadow.sunrisecreative.api.menu.menus.MyWorlds((Player) commandSender);
        return true;
    }
}
