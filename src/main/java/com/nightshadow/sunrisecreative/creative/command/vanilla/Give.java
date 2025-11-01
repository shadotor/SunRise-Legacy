package com.nightshadow.sunrisecreative.creative.command.vanilla;

import com.nightshadow.sunrisecreative.creative.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;

public class Give implements TabCompleter, CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        World w = getWorldWhere(p);
        if ( w.isOwner(p) || w.isAdmin(p)  || p.isOp()) if (strings.length < 2)  p.sendMessage(getLocale("command.give.info",getPlayerLang(p)));
        else {
            if (strings.length == 2) {
                if (Bukkit.getPlayer(strings[0]).getWorld() == p.getWorld()) {
                    Player givePlayer = Bukkit.getPlayer(strings[0]);
                    try {
                        Material material = Material.valueOf(strings[1].replace("minecraft:", "").toUpperCase());
                        givePlayer.getInventory().addItem(new ItemStack(material));
                    } catch (IllegalArgumentException e) {
                        p.sendMessage(getLocale("command.give.error",getPlayerLang(p)));
                    }
                }
            }
            if (strings.length == 3) {
                if (Bukkit.getPlayer(strings[0]).getWorld() == p.getWorld()) {
                    Player givePlayer = Bukkit.getPlayer(strings[0]);
                    try {
                        Material material = Material.valueOf(strings[1].replace("minecraft:", "").toUpperCase());
                        givePlayer.getInventory().addItem(new ItemStack(material,Integer.parseInt(strings[1])));
                    } catch (IllegalArgumentException e) {
                        p.sendMessage(getLocale("command.give.error",getPlayerLang(p)));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> tabCompleter = new ArrayList<>();
        Player p = (Player) commandSender;
        if (strings.length == 1) {
            for (Player player : p.getWorld().getPlayers()) {
                tabCompleter.add(player.getName());
            }
        } else if (strings.length == 2) {
            tabCompleter.addAll(Arrays.stream(Material.values()).filter(Material::isItem).map(material -> material.name().toLowerCase()).toList());
        }
        return tabCompleter;
    }
}
