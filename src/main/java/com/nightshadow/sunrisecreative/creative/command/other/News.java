package com.nightshadow.sunrisecreative.creative.command.other;

import com.nightshadow.sunrisecreative.api.NewsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static java.lang.Integer.parseInt;

@ApiStatus.Experimental
public class News implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if (( p.isOp() || p.hasPermission("sunrise.newsRedacting") ) && strings.length > 0 ) {
            switch (strings[0]) {
                case "clear": {
                    NewsAPI.clearData();
                    p.sendMessage(parseColor("&e[\uD83D\uDCF0] &aДиалог успешно очищен"));
                    break;
                }
                case "reset": {
                    NewsAPI.clearReadData();
                    p.sendMessage(parseColor("&e[\uD83D\uDCF0] &aДанные о прочтении новостей успешно удалены"));
                    break;
                }
                case "addLine": {
                    if (strings.length > 1) {
                        StringBuilder a = new StringBuilder();
                        for (int i = 1; i < strings.length; i++) {
                            a.append(strings[i]).append(" ");
                        }
                        NewsAPI.addLine(a.toString());
                        p.sendMessage(parseColor("&e[\uD83D\uDCF0] &aЛиния успешно добавлена!"));
                    }
                    else p.sendMessage(parseColor("&e[\uD83D\uDCF0] &cИспользование: /news addLine <содержание>"));
                    break;
                }
                case "setLine": {
                    if (strings.length > 2) {
                        StringBuilder a = new StringBuilder();
                        for (int i = 2; i < strings.length; i++) {
                            a.append(strings[i]).append(" ");
                        }
                        NewsAPI.setLine(parseInt(strings[1]), a.toString());
                        p.sendMessage(parseColor("&e[\uD83D\uDCF0] &aЛиния успешно установлена!"));
                    }
                    else p.sendMessage(parseColor("&e[\uD83D\uDCF0] &cИспользование: /news addLine <содержание>"));
                    break;
                }
                case "show": {
                    if (NewsAPI.DialogExists()) NewsAPI.showDialog(p);
                    else p.sendMessage(parseColor("&e[\uD83D\uDCF0] &cДиалогового окна новостей не существует!"));
                    break;
                }
                case "removeLine": {
                    if (strings.length == 2) {
                        NewsAPI.removeLine(parseInt(strings[1]));
                        p.sendMessage(parseColor("&e[\uD83D\uDCF0] &aУспешно удалена строка с индексом " + strings[1]));
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of("clear","reset","addLine","show","removeLine", "setLine");
    }


}
