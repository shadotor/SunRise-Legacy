package com.nightshadow.sunrisecreative.creative.command.scripting;

import com.nightshadow.sunrisecreative.creative.world.World;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Location;

import static com.nightshadow.sunrisecreative.creative.coding.handler.CodingBlocks.giveItems;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.*;

public class Dev implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        Player p = (Player) sender;
        World w = getWorldWhereIncludingDevWorld(p);
        if (w != spawn) {
            if (w.isDeveloper(p) || w.isAdmin(p) || w.isOwner(p) || p.hasPermission("sunrise.devTp")) {
                if (!inDevelopmentWorld(p)) {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(getLocale("coding.teleporting", getPlayerLang(p)));
                    try {
                        Location devSpawn = new Location(w.getCodingWorld().getWorld(), 0.0, 1.0, 0.0);
                        p.teleport(devSpawn);
                        giveItems(p);
                    } catch (Exception e) {
                        p.sendMessage(getLocale("coding.teleport.error", getPlayerLang(p)));
                        e.printStackTrace();
                    }
                } else {
                    w.teleportWorld(p);
                }
            } else if (w == spawn) p.sendMessage(parseColor("&cВы должны находиться в мире!"));
        }
        return true;
    }
}
