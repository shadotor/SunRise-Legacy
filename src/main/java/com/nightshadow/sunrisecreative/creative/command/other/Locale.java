package com.nightshadow.sunrisecreative.creative.command.other;

import com.nightshadow.sunrisecreative.api.utils.LangUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.*;

/* Языковые настройки */
public class Locale implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0)  {
            player.sendMessage(getLocale("command.locale.message.info",getPlayerLang(player)));
        }
        else {
            if (args[0].equals("reload")) {
                LangUtils.reloadLocales();
                return true;
            } else {
                try {
                    setPlayerLang(player, args[0]);
                } catch (Exception e) {
                    player.sendMessage("Неизвестный язык " + args[0]);
                }
            }
        }
            return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> tabCompleter = new ArrayList<>();
        if (args.length <= 1) {
            tabCompleter.add("reload");
            tabCompleter.add("ru");
            tabCompleter.add("en");
            tabCompleter.add("ua");
        }
        return tabCompleter;
    }
}
