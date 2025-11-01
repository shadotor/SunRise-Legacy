package com.nightshadow.sunrisecreative.creative.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_RESPAWN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.interact.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.inventory.PLAYER_SWAP_HAND_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_ITEM_BREAK_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_MEND_ITEM;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_DAMAGE_ENTITY_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_DAMAGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_DEATH_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_DROP_ITEM_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_ENTER_PORTAL_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_CATCH_FISH_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_JUMP_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_MOVE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_TELEPORT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other.PLAYER_CHANGE_GAMEMODE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other.PLAYER_CHAT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other.PLAYER_FOOD_LEVEL_CHANGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world.PLAYER_QUIT_EVENT;
import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;

import static com.nightshadow.sunrisecreative.creative.world.worldManager.*;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldWhere;

public class CreativeEventListener implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (getWorldWhere(p) != spawn) new PLAYER_QUIT_EVENT().onExecute(getWorldWhere(p), p);
    }
    @EventHandler
    public void onHandSwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        new PLAYER_SWAP_HAND_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player p) {
            new PLAYER_DAMAGE_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
        }
    }
    @EventHandler
    public void onPlayerHungry(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        new PLAYER_FOOD_LEVEL_CHANGE_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (getWorldWhere(p) != spawn) new PLAYER_CHAT_EVENT().setEvent(e).onExecute(getWorldWhere(p), p);
    }
    @EventHandler
    public static void onDeath(PlayerDeathEvent e) {
        Player p = e.getPlayer();
        new PLAYER_DEATH_EVENT().onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void Respawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        new PLAYER_RESPAWN_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().isRightClick() && e.getHand() == EquipmentSlot.HAND) {
            new PLAYER_RIGHT_CLICK_EVENT().setEvent(e).onExecute(getWorldWhere(p), p);
        }
        else if (e.getAction().isLeftClick() && e.getHand() == EquipmentSlot.HAND) {
            new PLAYER_LEFT_CLICK_EVENT().setEvent(e).onExecute(getWorldWhere(p),p) ;
        }

    }
    @EventHandler
    public static void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        new PLAYER_DROP_ITEM_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onGamemodeChange(PlayerGameModeChangeEvent e) {
        Player p = e.getPlayer();
        new PLAYER_CHANGE_GAMEMODE_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onPlaceBlock(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        new PLAYER_PLACE_BLOCK_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onBreakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        new PLAYER_BREAK_BLOCK_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onFishCatch(PlayerFishEvent e) {
        Player p = e.getPlayer();
        new PLAYER_CATCH_FISH_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onEntityBreak(HangingBreakByEntityEvent e) {
        Entity entity = e.getRemover();
        if (entity instanceof Player p) {
            new PLAYER_BREAK_ENTITY_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
        }
    }
    @EventHandler
    public static void onEntityAttack(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if (entity instanceof Player p) {
            if (getWorldWhereIncludingDevWorld(p) != spawn) {
                new PLAYER_DAMAGE_ENTITY_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
            }
        }
    }
    @EventHandler
    public static void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        new PLAYER_INTERACT_WITH_ENTITY().setEvent(e).onExecute(getWorldWhere(p),p);
    }

    @EventHandler
    public static void onTeleport(PlayerTeleportEvent e) {
    if (e.getCause() != PlayerTeleportEvent.TeleportCause.PLUGIN && e.getCause() != PlayerTeleportEvent.TeleportCause.COMMAND) {
        Player p = e.getPlayer();
        new PLAYER_TELEPORT_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
        }
    }
    @EventHandler
    public static void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        new PLAYER_MOVE_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onJump(PlayerJumpEvent e) {
        Player p = e.getPlayer();
        new PLAYER_JUMP_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);

    }
    @EventHandler
    public static void onItemBreak(PlayerItemBreakEvent e) {
        Player p = e.getPlayer();
        new PLAYER_ITEM_BREAK_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onInteractWorld(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        new PLAYER_INTERACT_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onStartedBrokenBlock(BlockDamageEvent e) {
        Player p = e.getPlayer();
        new PLAYER_STARTED_BREAKING_BLOCK().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onEndingBrokenBlock(BlockDamageAbortEvent e) {
        Player p = e.getPlayer();
        new PLAYER_ENDING_BREAKING_BLOCK_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onArmSwing(PlayerArmSwingEvent e ) {
        Player p = e.getPlayer();
        new PLAYER_ARM_SWING_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
    }
    @EventHandler
    public static void onPortalEnter(EntityPortalEnterEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            Player p = (Player) e.getEntity();
            new PLAYER_ENTER_PORTAL_EVENT().setEvent(e).onExecute(getWorldWhere(p),p);
        }
    }
    @EventHandler
    public static void onMendItem(PlayerItemMendEvent e) {
            Player p = e.getPlayer();
            new PLAYER_MEND_ITEM().setEvent(e).onExecute(getWorldWhere(p),p);
    }
}


