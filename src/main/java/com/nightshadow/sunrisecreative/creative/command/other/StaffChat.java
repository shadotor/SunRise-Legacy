package com.nightshadow.sunrisecreative.creative.command.other;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parsePlaceholders;

public class StaffChat implements CommandExecutor {
   @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
       Player p = (Player) sender;
       if (p.hasPermission("sunrise.staffChat") || p.isOp()) {
          String FullMsg = String.join(" ", args);
          Bukkit.getOnlinePlayers().forEach((Player pl) -> {
            if (pl.hasPermission("sunrise.staffChat") || pl.isOp()) pl.sendMessage(parseColor(parsePlaceholders(p, "&#ff00cc[S] &f%luckperms_prefix%" + p.getDisplayName() + "%luckperms_suffix%" + "&f: " + FullMsg)));
          });

      }
      return true;
  }
}
