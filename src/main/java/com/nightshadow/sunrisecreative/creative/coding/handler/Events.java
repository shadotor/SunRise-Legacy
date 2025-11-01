package com.nightshadow.sunrisecreative.creative.coding.handler;

import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.damage.ENTITY_DAMAGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.damage.ENTITY_SPAWN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.inventory.ENTITY_PICKUP_ITEM;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.item.ITEM_MERGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.movement.ENTITY_MOUNT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.entity.movement.ENTITY_MOVE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_DAMAGE_ENTITY_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_DAMAGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_DEATH_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.damage.PLAYER_RESPAWN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.interact.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.inventory.PLAYER_SWAP_HAND_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_DROP_ITEM_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_ITEM_BREAK_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_MEND_ITEM;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_ENTER_PORTAL_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.item.PLAYER_CATCH_FISH_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_JUMP_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_MOVE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.movement.PLAYER_TELEPORT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other.PLAYER_CHANGE_GAMEMODE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other.PLAYER_CHAT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.other.PLAYER_FOOD_LEVEL_CHANGE_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world.PLAYER_JOIN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world.PLAYER_QUIT_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.world.*;
import com.nightshadow.sunrisecreative.creative.coding.exceptions.EventNotFoundException;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;

public abstract class Events {
    public static List<CodingEvent> events = new ArrayList<>();
    public static CodingEvent getEvent(String identify) {
        for (CodingEvent event : events) {
            if (event.getClass().getSimpleName().equals(identify)) {
                return event;
            }
        }
        throw new EventNotFoundException(identify);
    }
    public static void loadEvents() {
        // События игрока
        events.add(new PLAYER_JOIN_EVENT());
        events.add(new PLAYER_RIGHT_CLICK_EVENT());
        events.add(new PLAYER_RESPAWN_EVENT());
        events.add(new PLAYER_LEFT_CLICK_EVENT());
        events.add(new PLAYER_QUIT_EVENT());
        events.add(new PLAYER_CHAT_EVENT());
        events.add(new PLAYER_DROP_ITEM_EVENT());
        events.add(new PLAYER_BREAK_BLOCK_EVENT());
        events.add(new PLAYER_CHANGE_GAMEMODE_EVENT());
        events.add(new PLAYER_DAMAGE_EVENT());
        events.add(new PLAYER_SWAP_HAND_EVENT());
        events.add(new PLAYER_PLACE_BLOCK_EVENT());
        events.add(new PLAYER_INTERACT_WITH_ENTITY());
        events.add(new PLAYER_INTERACT_EVENT());
        events.add(new PLAYER_CATCH_FISH_EVENT());
        events.add(new PLAYER_TELEPORT_EVENT());
        events.add(new PLAYER_BREAK_ENTITY_EVENT());
        events.add(new PLAYER_MOVE_EVENT());
        events.add(new PLAYER_DEATH_EVENT());
        events.add(new PLAYER_ITEM_BREAK_EVENT());
        events.add(new PLAYER_JUMP_EVENT());
        events.add(new PLAYER_DAMAGE_ENTITY_EVENT());
        events.add(new PLAYER_FOOD_LEVEL_CHANGE_EVENT());
        events.add(new PLAYER_STARTED_BREAKING_BLOCK());
        events.add(new PLAYER_ENTER_PORTAL_EVENT());
        events.add(new PLAYER_ENDING_BREAKING_BLOCK_EVENT());
        events.add(new PLAYER_ARM_SWING_EVENT());
        events.add(new PLAYER_MEND_ITEM());

        // События сущности

        events.add(new ENTITY_MOUNT_EVENT());
        events.add(new ENTITY_DAMAGE_EVENT());
        events.add(new ENTITY_MOVE_EVENT());
        events.add(new ENTITY_MOUNT_EVENT.ENTITY_DEATH_EVENT());
        events.add(new ENTITY_SPAWN_EVENT());
        events.add(new ITEM_MERGE_EVENT());
        events.add(new ENTITY_PICKUP_ITEM());

        // События мира

        events.add(new BLOCK_BURN_EVENT());
        events.add(new FURNACE_BURN_EVENT());
        events.add(new FURNACE_SMELT_EVENT());
        events.add(new WORLD_SHUTDOWN_EVENT());
        events.add(new WORLD_RUNNING_EVENT());


        Bukkit.broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoaded " + events.size() + " events"));
    }
    public static List<CodingEvent> getEvents() {return events;}
    public static String getEventName(Material block) {
        Map<Material,String> m = new HashMap<>();
        m.put(Material.DIAMOND_BLOCK, "Событие игрока");
        m.put(Material.REDSTONE_BLOCK, "Событие мира");
        m.put(Material.GOLD_BLOCK, "Событие сущн.");
        return m.get(block);
    }
}



