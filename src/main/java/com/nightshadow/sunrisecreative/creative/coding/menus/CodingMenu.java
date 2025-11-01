package com.nightshadow.sunrisecreative.creative.coding.menus;

import com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation;
import com.nightshadow.sunrisecreative.creative.coding.reader.Reader;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import com.nightshadow.sunrisecreative.creative.coding.values.Variable;
import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.menu.Menu;
import com.nightshadow.sunrisecreative.api.utils.Language;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation.getCodingLocation;
import static com.nightshadow.sunrisecreative.creative.coding.menus.CodingMenuBuilder.getTypeItem;
import static com.nightshadow.sunrisecreative.creative.coding.handler.Actions.getAction;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldByLocation;
import static java.lang.Double.parseDouble;

public class CodingMenu implements InventoryHolder {
    Menu menu;
    List<ValueType> argTypes;
    List<Integer> slots;
    Location chestLocation;
    public CodingMenu(CodingMenuInitializer initializer) {

    }
    public CodingMenu(String action, Language lang) {
        Action act = getAction(action);
        argTypes = act.getArguments();
        List<ValueType> list = act.getArguments();
        List<Integer> slots = new ArrayList<>();
        Menu codingMenu;
        if (list.size() <= 9) codingMenu = new Menu(3, getLocale("action."+action,lang),this);
        else codingMenu = new Menu(6, getLocale("action."+action,lang), this);
        codingMenu.fillMenu(new Item(Material.BLACK_STAINED_GLASS_PANE, " "));

        if (list.size() == 1) slots.add(13);
        else if (list.size() == 2) {
            slots.add(12);
            slots.add(14);
        }
        else if (list.size() <= 9) {
            for (int i = 0; i < list.size(); i++) {
                slots.add(i+9);
            }
        }
        for (int i = 0; i < slots.size(); i++)
        {
            Integer slot = slots.get(i);
            codingMenu.removeItem(slot);
            codingMenu.setItem(slot+9, getTypeItem(list.get(i)));
        }
        this.menu = codingMenu;
        this.slots = slots;
    }
    public void setChestLocation(Location loc) {this.chestLocation = loc; }
    public Location getChestLocation() {return  chestLocation;}
    public void saveMenuData(Location chestLocation) {
        if (chestLocation.getBlock().getState() instanceof Chest chest) {
            List<ItemStack> variableContents = new ArrayList<>();
            for (int i = 0; i < slots.size(); i++) {
                int slot = slots.get(i);
                sendConsole("Saved: " + slot);
                variableContents.add(menu.getInventory().getItem(slot));
                ItemStack item =  menu.getInventory().getItem(slot);
                if (item == null) {
                    item = new ItemStack(Material.AIR);
                }
                parseToArgument(new Reader(getWorldByLocation(chestLocation)), i, item, getCodingLocation(chestLocation), argTypes.get(i));
            }
            chest.getBlockInventory().setContents(variableContents.toArray(new ItemStack[variableContents.size()]));
        }
    }

    public void loadMenuData(Location chestLocation) {
        if (chestLocation.getBlock().getState() instanceof Chest chest) {
            for (int i = 0; i < slots.size(); i++) {
                    ItemStack item = chest.getBlockInventory().getItem(i);
                    menu.setItem(slots.get(i), item);
            }
        }
    }
    @Override
    public @NotNull Inventory getInventory() {
        return menu.getInventory();
    }

    public List<Integer> getSlots() {
        return slots;
    }

    void parseToArgument(Reader r, int index, ItemStack argumentItem, CodingLocation loc, ValueType type) {
        r.setPosition(loc);
        try {
                if (argumentItem.getType() == Material.MAGMA_CREAM && argumentItem.getItemMeta().getCustomModelData() == 1024) {
                    if (type == ValueType.VARIABLE) {
                    r.setArgument(String.valueOf(index),new Variable(r.getWorld(), argumentItem.getItemMeta().getDisplayName()).toString());
                    }
                    else {
                        r.setArgument(String.valueOf(index), new Variable(r.getWorld(), argumentItem.getItemMeta().getDisplayName()).getPlaceholder());
                    }
                    return;
                }
            if (type == ValueType.VALUE) {
                if (argumentItem.getType() == Material.AIR) {
                    r.setArgument(String.valueOf(index), null);
                }
                if ( ( argumentItem.getType() == Material.BOOK)  && argumentItem.getItemMeta().getCustomModelData() == 1024) {
                    r.setArgument(String.valueOf(index), argumentItem.getItemMeta().getDisplayName());
                }
                else if (argumentItem.getType() == Material.SLIME_BALL && argumentItem.getItemMeta().getCustomModelData() == 1024) {
                    r.setArgument(String.valueOf(index), parseDouble(argumentItem.getItemMeta().getDisplayName()));
                }
                else r.setArgument(String.valueOf(index), argumentItem);
            }
            if (type == ValueType.TEXT) {

                r.setArgument(String.valueOf(index), argumentItem.getItemMeta().getDisplayName());
                if (argumentItem.getType() == Material.AIR) {
                    r.setArgument(String.valueOf(index), "");
                }
            }
            if (type == ValueType.NUMBER) {
                r.setArgument(String.valueOf(index), parseDouble(argumentItem.getItemMeta().getDisplayName()));
                if (argumentItem.getType() == Material.AIR) {
                    r.setArgument(String.valueOf(index), 0);
                }
            }
            if (type == ValueType.POSITION) {
                if (argumentItem.getType() == Material.AIR) r.setArgument(String.valueOf(index), "0 0 0");
                r.setArgument(String.valueOf(index), argumentItem.getItemMeta().getDisplayName());
            }
            if (type == ValueType.ITEM) {
                if (argumentItem.getType() == Material.BOOK && argumentItem.getItemMeta().getCustomModelData() == 1024)
                {
                    r.setArgument(String.valueOf(index), ItemStack.of(Material.valueOf(argumentItem.getItemMeta().getDisplayName())));
                }
                else {
                    r.setArgument(String.valueOf(index), argumentItem);
                }
            }

        } catch(Exception e){
             e.printStackTrace();
        }

    }
}

