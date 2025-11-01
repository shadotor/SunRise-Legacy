package com.nightshadow.sunrisecreative.creative.coding.elements.action.world.entity;

import com.nightshadow.sunrisecreative.creative.coding.handler.ActionType;
import com.nightshadow.sunrisecreative.creative.coding.menus.SelectionIcon;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.coding.values.ValueType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;

import java.util.List;

import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;

public class WORLD_SUMMON_ENTITY extends Action {
    @Override
    public Object execute(Selector selector, Object[] args) {
        ItemStack item = (ItemStack) args[1];
        SpawnEggMeta meta = (SpawnEggMeta) item.getItemMeta();
        Location loc = locationFromString(args[0].toString(), selector.getWorld());
        loc.getWorld().spawnEntity(loc, meta.getCustomSpawnedType());
        return null;
    }

    @Override
    public List<ValueType> getArguments() {
        return List.of(ValueType.POSITION, ValueType.ITEM);
    }

    @Override
    public ActionType getType() {
        return ActionType.WORLD;
    }

    @Override
    public SelectionIcon getIcon() {
        return new SelectionIcon(Material.ZOMBIE_SPAWN_EGG,this);
    }

    @Override
    public String getName() {
        return "Призвать";
    }
}
