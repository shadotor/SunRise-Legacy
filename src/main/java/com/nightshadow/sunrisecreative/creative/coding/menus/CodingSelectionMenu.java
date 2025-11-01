package com.nightshadow.sunrisecreative.creative.coding.menus;

import com.nightshadow.sunrisecreative.api.utils.Language;
import com.nightshadow.sunrisecreative.creative.coding.handler.CodingBlockCategories;
import com.nightshadow.sunrisecreative.creative.coding.handler.CodingBlocks;
import com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation;
import com.nightshadow.sunrisecreative.creative.coding.reader.Reader;
import com.nightshadow.sunrisecreative.creative.coding.primitives.*;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import me.clip.placeholderapi.libs.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation.getCodingLocation;
import static com.nightshadow.sunrisecreative.api.menu.Template.*;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.setBlock;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhereIncludingDevWorld;


public class CodingSelectionMenu extends Menu {
    Menu menu;
    public CodingSelectionMenu(List<CodeElement> elements, Location signLocation, Language menuLang) {
        menu = new Menu(6, MiniMessage.miniMessage().deserialize(""));
        createButtoned(menu);
        for (int i = 0; i < elements.size(); i++) {
            CodeElement element = elements.get(i);
            if (element.getIcon() != null) {
                menu.setItem(i, element.getIcon().getItem(menuLang));
            }
            else {
                menu.setItem(i, new SelectionIcon(Material.DIAMOND, element).getItem(menuLang));
            }
            menu.addButton(i, (e) -> {
                    if (signLocation.getBlock().getState() instanceof Sign sign) {
                        Location x;
                        sign.setLine(1, element.getName());
                        sign.update();
                        signLocation.add(0, 0, -1);
                        CodingLocation loc = getCodingLocation(signLocation);
                        Reader reader = new Reader(getWorldWhereIncludingDevWorld((Player) e.getWhoClicked()));
                        reader.setPosition(loc);
                        if (element instanceof Action act) {
                            reader.setAction(act.getClass().getSimpleName());
                            if (! act.getArguments().isEmpty()) {
                                x = signLocation.add(0,1,0);
                                setBlock(x,Material.CHEST);
                                if (x.getBlock().getBlockData() instanceof Chest c) {
                                    c.setFacing(BlockFace.SOUTH);
                                    c.createBlockState().update();
                                    x.getBlock().setBlockData(c);
                                }
                            }
                        } else if (element instanceof CodingEvent) {
                            reader.setEvent(element.getClass().getSimpleName());
                        }
                    }
                ( (Player) e.getWhoClicked() ).closeInventory();
                });
            }
        }

    public static List<CodeElement> codingGetActionsByBlock(Material block) {
        List<CodeElement> actions = new ArrayList<>();
        CodingBlocks cb = CodingBlocks.getCodingBlockByBlockMaterial(block);
        if (cb.getCategory() == CodingBlockCategories.ACTION || cb.getCategory() == CodingBlockCategories.CONDITION) {
            actions.addAll(cb.getBlockActions());
        }
        if (cb.getCategory() == CodingBlockCategories.EVENT){
            actions.addAll(cb.getBlockEvents());
        }
        return actions;
    }
    @Override
    public @NotNull Inventory getInventory() {return menu.getInventory();}
}
