package com.nightshadow.sunrisecreative.creative.command.creative;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import com.nightshadow.sunrisecreative.creative.world.World;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.codehaus.plexus.util.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.*;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


/* Команда /creative (самая главная, по сути) */
public class Creative implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull  Command cmd,@NotNull  String str, String[] args) {
        World[] worlds = getWorldList();
        Player player = (Player) sender;
        Plugin plugin = Sunrise_Creative.getInstance();
        if (args.length == 0) {
            player.sendMessage(getLocale("command.creative.message.info",getPlayerLang(player)));
            return true;
        }
        if (args.length == 2) {
            switch (args[0]) {
                case "delete": {
                    Integer i = valueOf(args[1]);
                    for (World w : worldList) {
                        if (w.getID() == i && w.getAuthor() == player || player.isOp() && w.getID() == i) {
                            Bukkit.unloadWorld("worlds/world_" + args[1], false);
                            worldList.remove(w);
                            try {
                                FileUtils.deleteDirectory(new File(plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile(), "worlds/world_" + args[1]));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            player.sendMessage(getLocale("creative.command.message.delete.success", getPlayerLang(player)));
                            return true;
                        }
                    }
                    player.sendMessage(getLocale("creative.command.message.delete.denied", getPlayerLang(player)));
                    break;
                }
            }
        }
        if (args.length == 3 &&  ( player.hasPermission("sunrise.limiting") || player.isOp() )) {
            if (args[0].equals("limit")) {
                if (Bukkit.getPlayer(args[1]).isOnline()) {
                    setPlayerWorldLimit(Bukkit.getPlayer(args[1]), parseInt(args[2]));
                }
            } else player.sendMessage(getLocale("creative.access.denied", getPlayerLang(player)));
        }



        //CommandSender - отправляющий команду, Command - команда, String str я никогда не использовал, мне хватало cmd, String[] args - массив аргументов.
        return true; //вернем "ложь" если команда выполнена неправильно (настраивается в plugin.yml)
    }

    // Таб комплиты
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> tabCompleter = new ArrayList<>();
        if (args.length <= 1) {
            tabCompleter.add("delete");
            tabCompleter.add("limit");
        }
        return tabCompleter;
    }

}
