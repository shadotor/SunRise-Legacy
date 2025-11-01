package com.nightshadow.sunrisecreative.creative.coding.logs;

import com.nightshadow.sunrisecreative.creative.world.World;
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
import static com.nightshadow.sunrisecreative.creative.world.WorldConfig.getFile;

@ApiStatus.Experimental
public class Logs {
    private final World world;
    private final File LogFile;

    public Logs(World world) {
        this.world = world;
        this.LogFile = getFile(world, "log");
    }

    public  boolean DialogExists() {
        return LogFile.exists();
    }
    public void addLine(String contents, LogType type) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(LogFile);
        int index;
        if (config.getConfigurationSection("text") != null) index = YamlConfiguration.loadConfiguration(LogFile).getConfigurationSection("text").getValues(false).values().size();
        else index = 0;
        contents = type.getMessage(contents);
        config.set("text."+index,contents);
        try {
            config.save(LogFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Dialog getNotice() {
        List<DialogBody> body = new ArrayList<>();
        for (Object text : YamlConfiguration.loadConfiguration(LogFile).getConfigurationSection("text").getValues(false).values()) {
            body.add(DialogBody.plainMessage(parseColor(text.toString())));
        }
        DialogBase base = DialogBase.builder(parseColor("&7\uD83D\uDDCA World &e" + world.getID() + "&7 logs"))
                .afterAction(DialogBase.DialogAfterAction.CLOSE)
                .body(body).build();

        return Dialog.create((d) -> {
            d.empty().base(base).type(DialogType.notice());
        });
    }
    public void showDialog(Player p) {
        p.showDialog(getNotice());
    }
    public void clearData() {
        LogFile.delete();
    }
}
