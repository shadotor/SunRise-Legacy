package com.nightshadow.sunrisecreative.creative.listener;

import com.nightshadow.sunrisecreative.creative.coding.handler.CodingBlocks;
import com.nightshadow.sunrisecreative.creative.coding.menus.CustomCodingMenu;
import com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation;
import com.nightshadow.sunrisecreative.creative.coding.reader.Reader;
import com.nightshadow.sunrisecreative.creative.coding.menus.CodingMenu;
import com.nightshadow.sunrisecreative.creative.coding.menus.CodingSelectionMenu;
import com.nightshadow.sunrisecreative.creative.coding.menus.ValuesMenu;
import com.nightshadow.sunrisecreative.api.menu.Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import static com.nightshadow.sunrisecreative.creative.coding.handler.Actions.getAction;
import static com.nightshadow.sunrisecreative.creative.coding.handler.CodingBlocks.*;
import static com.nightshadow.sunrisecreative.creative.coding.handler.Actions.getActionName;
import static com.nightshadow.sunrisecreative.creative.coding.handler.Events.getEventName;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.removeBlock;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.setBlock;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation.getCodingLocation;
import static com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation.isCodingLocation;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.*;
import static java.lang.Double.parseDouble;

public class DevelopmentWorldListener implements Listener {
    @EventHandler
    public static void onPlaceBlock(BlockPlaceEvent e) {
        Block block = e.getBlock();
        Material blockMaterial = block.getType();
        Location blockPlaceLocation = block.getLocation();
        Player player = e.getPlayer();
        if (inDevelopmentWorld(player)) {
            if (getCodingBlocks().contains(blockMaterial)) {
                CodingBlocks i = CodingBlocks.get(blockMaterial);
                if (block.getLocation().add(0,-1,0).getBlock().getType() == Material.BLUE_STAINED_GLASS){
                    if (getEventBlocks().contains(block.getType())) {
                        Location stoneLocation = blockPlaceLocation.offset(1,0,0).toLocation(blockPlaceLocation.getWorld());
                        Location signLocation = blockPlaceLocation.offset(0,0,1).toLocation(blockPlaceLocation.getWorld());
                        setBlock(stoneLocation, i.getConnector());
                        setBlock(signLocation, i.getSignMaterial());
                        WallSign s = (WallSign) signLocation.getBlock().getBlockData();
                        s.setFacing(BlockFace.SOUTH);
                        signLocation.getBlock().setBlockData(s);
                        if (signLocation.getBlock().getState() instanceof Sign) {
                            Sign sign = (Sign) signLocation.getBlock().getState();
                            sign.setLine(0, getEventName(blockMaterial));
                            sign.getSide(Side.FRONT).setGlowingText(true);
                            sign.getSide(Side.FRONT).setColor(i.getSignColor());
                            sign.update();
                        }
                    } else e.setCancelled(true);
                }
                else if (block.getLocation().add(0,-1,0).getBlock().getType() == Material.GRAY_STAINED_GLASS){
                    if (getActionBlocks().contains(block.getType())) {
                        Location stoneLocation = blockPlaceLocation.offset(1,0,0).toLocation(blockPlaceLocation.getWorld());
                        Location signLocation = blockPlaceLocation.offset(0,0,1).toLocation(blockPlaceLocation.getWorld());
                        setBlock(stoneLocation,  i.getConnector());
                        setBlock(signLocation, i.getSignMaterial());
                        WallSign s = (WallSign) signLocation.getBlock().getBlockData();
                        s.setFacing(BlockFace.SOUTH);
                        signLocation.getBlock().setBlockData(s);
                        if (signLocation.getBlock().getState() instanceof Sign) {
                            Sign sign = (Sign) signLocation.getBlock().getState();
                            sign.setLine(0, getActionName(blockMaterial));
                            sign.getSide(Side.FRONT).setGlowingText(true);
                            sign.getSide(Side.FRONT).setColor(i.getSignColor());
                            sign.update();
                        }
                    } else e.setCancelled(true);
                }
                else e.setCancelled(true);
            }
            else e.setCancelled(true);
        }

    }
    @EventHandler
    public static void onBreakBlock(BlockBreakEvent e) {
        Block block = e.getBlock();
        Material blockMaterial = block.getType();
        Location blockBreakLocation = e.getBlock().getLocation();
        Player player = e.getPlayer();
        if (inDevelopmentWorld(player)) {
            if (getCodingBlocks().contains(blockMaterial)) {
                if (block.getLocation().add(0,-1,0).getBlock().getType() == Material.BLUE_STAINED_GLASS || block.getLocation().add(0,-1,0).getBlock().getType() == Material.GRAY_STAINED_GLASS){
                    if (getEventBlocks().contains(block.getType()) || getActionBlocks().contains(block.getType())) {
                        Location stoneLocation = blockBreakLocation.offset(1,0,0).toLocation(blockBreakLocation.getWorld());
                        Location signLocation = blockBreakLocation.offset(0,0,1).toLocation(blockBreakLocation.getWorld());
                        Location chestLocation = blockBreakLocation.offset(0,1,0).toLocation(blockBreakLocation.getWorld());
                        removeBlock(signLocation);
                        removeBlock(stoneLocation);
                        removeBlock(chestLocation);
                        Reader r = new Reader(getWorldWhereIncludingDevWorld(player));
                        if (blockBreakLocation.offset(0,-1,0).toLocation(blockBreakLocation.getWorld()).getBlock().getType() == Material.BLUE_STAINED_GLASS) {
                            r.setPosition(CodingLocation.getCodingLocation(blockBreakLocation));
                            r.removeLine();
                        } else if (blockBreakLocation.offset(0,-1,0).toLocation(blockBreakLocation.getWorld()).getBlock().getType() == Material.GRAY_STAINED_GLASS) {
                            r.setPosition(CodingLocation.getCodingLocation(blockBreakLocation));
                            r.removeColumn();
                        }
                    }
                }
                else e.setCancelled(true);
            }
            else e.setCancelled(true);
        }

    }
    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        if (inDevelopmentWorld(e.getPlayer())) {
            /* if (e.getPlayer().isSneaking() && e.getHand() == EquipmentSlot.HAND && e.getAction().isRightClick()) {
                if (isCodingLocation(e.getClickedBlock().getLocation().offset(-1, 0, 0).toLocation(e.getClickedBlock().getWorld()))) {
                    Reader r = new Reader(getWorldWhereIncludingDevWorld(e.getPlayer()));
                    r.setPosition(getCodingLocation(e.getClickedBlock().getLocation().add(-1, 0, 0)));
                    r.offset();
                    e.getPlayer().sendMessage("&eКод сдвинут");
                }
            } */
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_INGOT) {
                e.getPlayer().openInventory(new ValuesMenu().getInventory());
            }
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getType() == Material.CHEST) {
                    e.setCancelled(true);
                    Reader r = new Reader(getWorldWhereIncludingDevWorld(e.getPlayer()));
                    r.setPosition(CodingLocation.getCodingLocation(e.getClickedBlock().getLocation()));
                    CodingMenu codingMenu;
                    if (! ( getAction(r.getAction().getAction()) instanceof CustomCodingMenu)) codingMenu = new CodingMenu(r.getAction().getAction(), getPlayerLang(e.getPlayer()));
                    else codingMenu = (((CustomCodingMenu) getAction(r.getAction().getAction())).getMenuInitializer().setLanguage(getPlayerLang(e.getPlayer())).build());
                    codingMenu.loadMenuData(e.getClickedBlock().getLocation());
                    codingMenu.setChestLocation(e.getClickedBlock().getLocation());
                    e.getPlayer().openInventory(codingMenu.getInventory());
                }
                if (e.getClickedBlock().getBlockData() instanceof WallSign) {
                    e.setCancelled(true);
                    Player p = e.getPlayer();
                    Location blockPosition = e.getClickedBlock().getLocation().add(0, 0, -1);
                    CodingSelectionMenu menu = new CodingSelectionMenu(CodingSelectionMenu.codingGetActionsByBlock(blockPosition.getBlock().getType()), e.getClickedBlock().getLocation(), getPlayerLang(e.getPlayer()));
                    p.openInventory(menu.getInventory());
                }
            }
        }
    }
    @EventHandler
    public static void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (inDevelopmentWorld(p)) {
            if (p.getItemInHand().getType() == Material.BOOK) {
                e.setCancelled(true);
                Item item = new Item(p.getItemInHand(), e.getMessage());
                p.setItemInHand(item.getItem());
            }
            if (p.getItemInHand().getType() == Material.SLIME_BALL) {
                e.setCancelled(true);
                try {
                    parseDouble(e.getMessage());
                    Item item = new Item(p.getItemInHand(), e.getMessage());
                    p.setItemInHand(item.getItem());
                }
                catch (NumberFormatException ex) {
                    p.showTitle(Title.title(getLocale("input.wrong-number-format",getPlayerLang(p)), Component.text("")));
                }
            }
            if (p.getItemInHand().getType() == Material.MAGMA_CREAM) {
                e.setCancelled(true);
                Item item = new Item(p.getItemInHand(), e.getMessage());
                item.setCustomModelData(1024);
                p.setItemInHand(item.getItem());
            }
        }
    }
    @EventHandler
    public static void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof CodingMenu menu) {
            menu.saveMenuData(menu.getChestLocation());
        }
    }
}
