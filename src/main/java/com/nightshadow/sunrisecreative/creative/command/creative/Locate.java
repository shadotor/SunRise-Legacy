package com.nightshadow.sunrisecreative.creative.command.creative;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhereIncludingDevWorld;

public class Locate implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if (strings.length < 1) {
            p.sendMessage(getLocale("locate.info",getPlayerLang(p)));
        } else if (Objects.requireNonNull(Bukkit.getPlayer(strings[0])).isOnline()) {
            int id = getWorldWhereIncludingDevWorld(Bukkit.getPlayer(strings[0])).getID();
            p.sendMessage(parseColor("&fИгрок &e" + strings[0] + "&fСейчас играет в мире с ID:&e " + id));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String @NotNull [] strings) {
        List<String> s = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            s.add(onlinePlayer.getName());
        }
        return s;
    }
}
