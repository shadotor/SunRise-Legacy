package com.nightshadow.sunrisecreative.creative.command.creative;

import com.nightshadow.sunrisecreative.creative.world.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.worldList;
import static java.lang.Integer.valueOf;

public class Join implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player player = (Player) commandSender;
        try {
            Integer i = valueOf(strings[0]);
            for (World w : worldList) {
                if (w.getID() == i) {
                    w.teleportWorld(player);
                    return true;
                }
            }
            player.sendMessage(getLocale("creative.command.message.wrong-id", getPlayerLang(player)));
        } catch(Exception e) {
            player.sendMessage(parseColor("&cВо время исполнения произошла непредвиденная ошибка!"));
        }
        return true;
    }
}
