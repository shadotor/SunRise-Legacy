package com.nightshadow.sunrisecreative.api.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextUtils {
    private static final Pattern HEX_AMP = Pattern.compile("&x((&[0-9a-fA-F]){6})");

    /**
     * Parsing text to Minimessage and Component format.
     * @param text - Text with &0-9, &#FFFFFF, &a-f, &k, &o, &n, etc.
     * @return Component
     */
    public static Component parseColor(String text) {
            if (text == null || text.isEmpty()) text=  "";
            text = text.replaceAll("§", "&");

            text = HEX_AMP.matcher(text).replaceAll(m -> "<#" + m.group(1).replace("&", "") + ">");
            text = text.replaceAll("&#([a-fA-F0-9]{6})", "<#$1>");

            for (ChatColor c : ChatColor.values()) {
                if (c == ChatColor.MAGIC || c == ChatColor.RESET || c == ChatColor.ITALIC || c == ChatColor.UNDERLINE) continue;
                text = text.replaceAll("(?i)&" + c.getChar(), "<" + c.name().toLowerCase() + ">");
            }

            text = text.replaceAll("(?i)&n", "<u>");
            text = text.replaceAll("(?i)&k", "<obf>");
            text = text.replaceAll("(?i)&o", "<italic>");
            text = text.replaceAll("(?i)&r", "<reset>");

            return MiniMessage.miniMessage().deserialize(text);
    }

    /**
     * (Устарело, используйте parseColor)
     * Заменяет & на §.
      * @param text - Исходный текст
     * @return текст с заменой
     */
    @Deprecated(since = "0.3",forRemoval = true)
    public static String parseParagraph(String text) {
        if (text == null || text.isEmpty()) text=  "";

        text = text.replaceAll("&", "§");
        return text;
    }
    /**
     * (Устарело, используйте parseColor)
     * Заменяет & на §.
     * @param textList - Исходный текст
     * @return текст с заменой
     */
    @Deprecated(since = "0.3",forRemoval = true)
    public static List<String> parseParahraph(List<String> textList) {
        if (textList != null) {
            for (int i = 0; i < textList.size(); i++) {
                textList.set(i, textList.get(i).replaceAll("&", "§"));
            }

            return textList;
        }
        return null;
    }
    /**
     * Parsing text to Minimessage and Component format.
     * @param textList - Text with &0-9, &#FFFFFF, &a-f, &k, &o, &n, etc.
     * @return Component
     */
    public static List<Component> parseColor(List<String> textList) {
        if (textList != null) {
            List<Component> components = new ArrayList<>();
            for (String text : textList) {
                if (text == null || text.isEmpty()) text = "";

                text = text.replaceAll("§", "&");
                text = HEX_AMP.matcher(text).replaceAll(m -> "<#" + m.group(1).replace("&", "") + ">");
                text = text.replaceAll("&#([a-fA-F0-9]{6})", "<#$1>");

                for (ChatColor c : ChatColor.values()) {
                    if (c == ChatColor.MAGIC || c == ChatColor.RESET || c == ChatColor.ITALIC || c == ChatColor.UNDERLINE) continue;
                    text = text.replaceAll("(?i)&" + c.getChar(), "<" + c.name().toLowerCase() + ">");
                }

                text = text.replaceAll("(?i)&n", "<u>");
                text = text.replaceAll("(?i)&k", "<obf>");
                text = text.replaceAll("(?i)&o", "<italic>");
                text = text.replaceAll("(?i)&r", "<reset>");
                components.add(MiniMessage.miniMessage().deserialize(text));
            }

            return components;
        }
        return null;
    }

    /**
     * Parse Placeholders like %luckperm_prefix% to their values,
     * need Placeholder API installed on server.
     * @param player - selected player
     * @param text - String
     * @return String, text with Placeholders
     */
    public static String parsePlaceholders(Player player, String text) {
        return (player != null && Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
                ? PlaceholderAPI.setPlaceholders(player, text)
                : text;
    }
}
