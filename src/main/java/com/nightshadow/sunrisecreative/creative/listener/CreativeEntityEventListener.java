package com.nightshadow.sunrisecreative.creative.listener;

import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.damage.ENTITY_DAMAGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.damage.ENTITY_SPAWN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.inventory.ENTITY_PICKUP_ITEM;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.item.ITEM_MERGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.movement.ENTITY_MOUNT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.movement.ENTITY_MOVE_EVENT;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;

public class CreativeEntityEventListener implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (!e.getEntity().hasMetadata("DeveloperEntity")) {
            new ENTITY_SPAWN_EVENT().setEvent(e).onExecute(getWorldWhere(e.getEntity()),e.getEntity());
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntityType() != EntityType.PLAYER || !e.getEntity().hasMetadata("DeveloperEntity")) {
            new ENTITY_DAMAGE_EVENT().setEvent(e).onExecute(getWorldWhere(e.getEntity()),e.getEntity());
        }
    }

    @EventHandler
    public void onEntityDespawn(EntityMoveEvent e) {
        if (e.getEntityType() != EntityType.PLAYER || !e.getEntity().hasMetadata("DeveloperEntity")) {
            new ENTITY_MOVE_EVENT().setEvent(e).onExecute(getWorldWhere(e.getEntity()),e.getEntity());
        }
    }
    @EventHandler
    public void onMount(EntityMountEvent e) {
        if (e.getEntityType() != EntityType.PLAYER || !e.getEntity().hasMetadata("DeveloperEntity")) {
            new ENTITY_MOUNT_EVENT().setEvent(e).onExecute(getWorldWhere(e.getEntity()),e.getEntity());
        }
    }
    @EventHandler
    public void onDeath(EntityDeathEvent e ){
        if (e.getEntityType() != EntityType.PLAYER || !e.getEntity().hasMetadata("DeveloperEntity")) {
            new ENTITY_MOUNT_EVENT.ENTITY_DEATH_EVENT().setEvent(e).onExecute(getWorldWhere(e.getEntity()),e.getEntity());
        }
    }
    @EventHandler
    public void onItemMerge(ItemMergeEvent e ){
        if (e.getEntityType() != EntityType.PLAYER || !e.getEntity().hasMetadata("DeveloperEntity")) {
            new ITEM_MERGE_EVENT().setEvent(e).onExecute(getWorldWhere(e.getEntity()),e.getEntity());
        }
    }
    @EventHandler
    public void onPickupItem(EntityPickupItemEvent e ){
        if (e.getEntityType() != EntityType.PLAYER || !e.getEntity().hasMetadata("DeveloperEntity")) {
            new ENTITY_PICKUP_ITEM().setEvent(e).onExecute(getWorldWhere(e.getEntity()),e.getEntity());
        }
    }
}

