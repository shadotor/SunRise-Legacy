package com.nightshadow.sunrisecreative.creative.listener;

import com.nightshadow.sunrisecreative.creative.coding.elements.event.world.BLOCK_BURN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.world.FURNACE_BURN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.world.FURNACE_SMELT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorld;

public class CreativeWorldEventListener implements Listener {
    @EventHandler
    public static void onFurnaceBurn(FurnaceBurnEvent e) {
        new FURNACE_BURN_EVENT().setEvent(e).onExecute(getWorld(e.getBlock().getWorld()),new Selector());
    }
    @EventHandler
    public static void onFurnaceSmelt(FurnaceSmeltEvent e) {
        new FURNACE_SMELT_EVENT().setEvent(e).onExecute(getWorld(e.getBlock().getWorld()),new Selector());
    }
    @EventHandler
    public static void onBlockBurn(BlockBurnEvent e) {
        new BLOCK_BURN_EVENT().setEvent(e).onExecute(getWorld(e.getBlock().getWorld()),new Selector());
    }
}
