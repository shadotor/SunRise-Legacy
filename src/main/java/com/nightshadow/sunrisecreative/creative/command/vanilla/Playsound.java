package com.nightshadow.sunrisecreative.creative.command.vanilla;

import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Playsound implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String @NotNull [] args) {
        Player p = (Player) commandSender;
        List<String> tabCompleter = new ArrayList<>();
        tabCompleter.addAll(Registry.SOUNDS.stream().filter(s -> s.getKey().asMinimalString().startsWith(args[1])).map(sound -> sound.getKey().asMinimalString()).toList());
        return tabCompleter;

    }
}
