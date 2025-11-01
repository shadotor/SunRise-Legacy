package com.nightshadow.sunrisecreative.creative.command.creative;

import com.nightshadow.sunrisecreative.api.menu.menus.WorldBrowser;
import com.nightshadow.sunrisecreative.creative.world.worldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Worlds implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player player = (Player) sender;
        WorldBrowser wb = new WorldBrowser(worldManager.getWorldList(),player);
        return true;
    }
}
