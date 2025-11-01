package com.nightshadow.sunrisecreative.creative.command.creative;

import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world.PLAYER_JOIN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world.PLAYER_QUIT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.world.WORLD_RUNNING_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.world.WORLD_SHUTDOWN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.world.World;
import com.nightshadow.sunrisecreative.creative.world.WorldStatement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhereIncludingDevWorld;

public class WorldStatementsCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        World w = getWorldWhereIncludingDevWorld(p);
        if (w.isAdmin(p) || w.isOwner(p)) {
            sendConsole(s);
            switch (s) {
                case "build": {
                    if (w.getStatement() != WorldStatement.BUILD) {
                        new WORLD_SHUTDOWN_EVENT().onExecute(w,new Selector());
                        w.setStatement(WorldStatement.BUILD);
                        w.getWorld().getPlayers().forEach((pl) -> {
                            pl.getInventory().clear();
                            pl.sendMessage(getLocale("world.statement-change.build",getPlayerLang(pl)));
                            pl.teleport(w.getSpawnLoc());
                            new PLAYER_QUIT_EVENT().onExecute(w,pl);
                        });
                    }
                    else {
                        p.getInventory().clear();
                        p.teleport(w.getSpawnLoc());
                    }
                    break;
                }
                case "play": {
                    if (w.getStatement() != WorldStatement.PLAY) {
                        new WORLD_RUNNING_EVENT().onExecute(w,new Selector());
                        w.setStatement(WorldStatement.PLAY);
                        w.getWorld().getPlayers().forEach((pl) -> {
                            pl.getInventory().clear();
                            pl.sendMessage(getLocale("world.statement-change.play",getPlayerLang(pl)));
                            pl.teleport(w.getSpawnLoc());
                            new PLAYER_JOIN_EVENT().onExecute(w,pl);
                        });
                    }
                    else {
                        p.getInventory().clear();
                        p.teleport(w.getSpawnLoc());
                        new PLAYER_JOIN_EVENT().onExecute(w,p);
                    }
                    break;
                }
            }
        }
        return true;
    }
}
