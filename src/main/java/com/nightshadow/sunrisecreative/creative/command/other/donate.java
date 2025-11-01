package com.nightshadow.sunrisecreative.creative.command.other;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getPlayerWorldLimit;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.setPlayerWorldLimit;

@ApiStatus.Experimental
public class donate implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (commandSender.isOp()) {
            if (strings[0].equals("give") && strings.length >=3 ) {
                Player p = Bukkit.getPlayer(strings[2]);
                User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
                user.setPrimaryGroup(strings[1]);
                sendConsole(user.getPrimaryGroup());


                InheritanceNode node = InheritanceNode.builder(strings[1]).value(true).build();
                user.data().add(node);
                LuckPermsProvider.get().getUserManager().saveUser(user);
                int addWorlds;
                switch (strings[1]) {
                    case "torch": {
                        setPlayerWorldLimit(p,getPlayerWorldLimit(p)+3);
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> tabCompleter = new ArrayList<>();
        if (strings.length < 2) {
            tabCompleter.add("give");
        }
        if (strings.length == 2 && strings[0].equals("give")) {
            tabCompleter.add("solar");
            tabCompleter.add("light");
            tabCompleter.add("lantern");
            tabCompleter.add("fire");
            tabCompleter.add("camp");
            tabCompleter.add("torch");
        }
        if (strings.length == 3 && strings[0].equals("give")) {
            Bukkit.getOnlinePlayers().forEach((p) -> {
                tabCompleter.add(p.getName());
            } );
        }
        return tabCompleter;
    }
}
