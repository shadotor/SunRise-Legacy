package com.nightshadow.sunrisecreative.creative.command.other;

import com.nightshadow.sunrisecreative.api.utils.LangUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FakePlayer implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        /*
        Player player = (Player) sender;
            if (args[0].equals("create") || args[0].equals("remove")) {
                if (args[0].equals("create")) {
                  Villager DeveloperEntity = getWorldWhereIncludingDevWorld(p).getSpawnLoc, EntityType.VILLAGER;
                  DeveloperEntity.setProfession(Villager.Profession.NONE);
                  DeveloperEntity.setVillagerType(Villager.Type.PLAINS);
                  DeveloperEntity.setMetadata("DeveloperEntity", true)
                }
                if (args[0].equals("remove")) {
                  player.sendMessage("Извините но команда в разработке");
                }
                  } else {
                return false;
            }
        }
            return true;

         */
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> tabCompleter = new ArrayList<>();
        if (args.length <= 1) {
            tabCompleter.add("create");
            tabCompleter.add("remove");
        }
        return tabCompleter;
    }
}
