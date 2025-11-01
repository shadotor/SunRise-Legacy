package com.nightshadow.sunrisecreative.creative.command.scripting;

import com.nightshadow.sunrisecreative.creative.world.World;
import com.nightshadow.sunrisecreative.creative.coding.logs.Logs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhereIncludingDevWorld;

public class Log implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        World w = getWorldWhereIncludingDevWorld(p);
        if (w.isOwner(p) || w.isAdmin(p) || w.isDeveloper(p) || p.hasPermission("sunrise.logs")) {
            Logs logs = new Logs(w);
            switch (strings[0]) {
                case "show": {
                    if (logs.DialogExists()) {
                        logs.showDialog(p);
                    }
                    break;
                }
                case "clear": {
                    if (logs.DialogExists()) {
                        logs.clearData();
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of("show", "clear");
    }
}
