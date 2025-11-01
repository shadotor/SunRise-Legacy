package com.nightshadow.sunrisecreative.api;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

@ApiStatus.Experimental
public abstract class NewsAPI {
    private static final File ReadFile = new File(Sunrise_Creative.getInstance().getDataFolder(),"newRead.yml");
    private static final File NewsFile = new File(Sunrise_Creative.getInstance().getDataFolder(),"new.yml");
    public static boolean DialogExists() {
        return NewsFile.exists();
    }
    public static boolean isRead(Player p) {
        return YamlConfiguration.loadConfiguration(ReadFile).getKeys(false).contains(p.getName());
    }
    public static void setRead(Player p, boolean b) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(ReadFile);
        config.set(p.getName(),b);
        try {
            config.save(ReadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addLine(String contents) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(NewsFile);
        int index;
        if (config.getConfigurationSection("text") != null) index = YamlConfiguration.loadConfiguration(NewsFile).getConfigurationSection("text").getValues(false).values().size();
        else index = 0;
        config.set("text."+index,contents);
        try {
            config.save(NewsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setLine(int index, String contents) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(NewsFile);
        config.set("text."+index,contents);
        try {
            config.save(NewsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Dialog getNotice() {
        List<DialogBody> body = new ArrayList<>();
        for (Object text : YamlConfiguration.loadConfiguration(NewsFile).getConfigurationSection("text").getValues(false).values()) {
            body.add(DialogBody.plainMessage(parseColor(text.toString())));
        }
        DialogBase base = DialogBase.builder(parseColor("\uD83D\uDCF0 Новости"))
                .afterAction(DialogBase.DialogAfterAction.CLOSE)
                .body(body).build();

        return Dialog.create((d) -> {
             d.empty().base(base).type(DialogType.notice());
        });
    }
    public static void showDialog(Player p) {
        p.showDialog(getNotice());
    }
    public static void removeLine(int index) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(NewsFile);
        config.set("text."+index,null);
        try {
            config.save(NewsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void clearReadData() {
        ReadFile.delete();

    }
    public static void clearData() {
        NewsFile.delete();
    }
}
