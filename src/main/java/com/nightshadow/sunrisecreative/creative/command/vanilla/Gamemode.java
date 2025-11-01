package com.nightshadow.sunrisecreative.creative.command.vanilla;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.spawn;


public class Gamemode implements TabCompleter, CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player player = (Player) commandSender;
        Map<String,String> shortcut = new HashMap<>();
        shortcut.put("0", "survival");
        shortcut.put("1","creative");
        shortcut.put("2","adventure");
        shortcut.put("3","spectator");
        if (getWorldWhere(player) != spawn) {
            if (strings.length == 1 && ( getWorldWhere(player).isOwner(player) || player.isOp() ) ){
                if (shortcut.containsKey(strings[0])) {
                    strings[0] = strings[0].replace(strings[0], shortcut.get(strings[0]));
                }
                player.setGameMode(GameMode.valueOf(strings[0].toUpperCase()));
            }
        } else {
            if (strings.length == 1 && player.isOp()) {
                if (shortcut.containsKey(strings[0])) {
                    strings[0] = strings[0].replace(strings[0], shortcut.get(strings[0]));
                }
                player.setGameMode(GameMode.valueOf(strings[0].toUpperCase()));
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> tabComplete = new ArrayList<>();
        tabComplete.add("creative");
        tabComplete.add("survival");
        tabComplete.add("spectator");
        tabComplete.add("adventure");
        return tabComplete;
    }
}
