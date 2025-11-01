package com.nightshadow.sunrisecreative.creative.command.creative;

import com.nightshadow.sunrisecreative.api.menu.menus.PlotSettings;
import com.nightshadow.sunrisecreative.creative.world.World;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static com.nightshadow.sunrisecreative.creative.world.WorldConfig.setKey;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.spawn;

public class Plot implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        World w = getWorldWhere(p);
        if (w != spawn) if (w.isAdmin(p) || w.isOwner(p)) {
            if (strings.length == 0) new PlotSettings(p,w);
            if (strings.length > 1) switch (strings[0]) {
                case "rename": {
                    StringBuilder a = new StringBuilder();
                    for (int i = 1; i < strings.length; i++) {
                        a.append(" " + strings[i]);
                    }
                    setKey(w, "data", "world-name", a.toString());
                    p.sendMessage(getLocale("creative.world.rename.success",getPlayerLang(p)));
                }
            }
            if (strings.length == 1) switch (strings[0]) {
                case "open": {
                    p.sendMessage(getLocale("creative.open.success", getPlayerLang(p)));
                    setKey(w, "data", "availability", true);
                    break;
                }
                case "close": {
                    p.sendMessage(getLocale("creative.close.success", getPlayerLang(p)));
                    setKey(w, "data", "availability", false);
                    break;
                }
                case "changeicon": {
                    if (! p.getItemInHand().getType().equals(Material.AIR)) {
                        p.sendMessage(getLocale("creative.icon-change.success", getPlayerLang(p)));
                        setKey(w, "data", "world-icon", p.getItemInHand());
                    } else p.sendMessage(getLocale("creative.icon-change.denied",getPlayerLang(p)));
                    break;
                }
                case "setspawn": {
                    w.getSpawnLoc().getWorld().setSpawnLocation(p.getLocation());
                    break;
                }
            }
        } else p.sendMessage(getLocale("creative.access.denied",getPlayerLang(p)));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        List<String> tabComplete = new ArrayList<>();
            tabComplete.add("rename");
            tabComplete.add("setspawn");
            tabComplete.add("open");
            tabComplete.add("close");
            tabComplete.add("changeicon");
        return tabComplete;
    }
}
