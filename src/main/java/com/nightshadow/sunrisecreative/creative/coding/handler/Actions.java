package com.nightshadow.sunrisecreative.creative.coding.handler;

import com.nightshadow.sunrisecreative.creative.coding.elements.action.code.CODE_CONDITION_END;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.code.CODE_RUN_LINE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.code.CODE_STOP;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.code.CODE_WAIT;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.data.DATA_CLEAR;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.data.DATA_LOAD;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.data.DATA_SAVE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.entity.ENTITY_DAMAGE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.entity.ENTITY_DELETE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.player.animation.PLAYER_MAIN_HAND_ANIMATE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.player.animation.PLAYER_OFF_HAND_ANIMATE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.player.communication.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.player.inventory.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.player.movement.PLAYER_RANDOM_TELEPORT;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.player.movement.PLAYER_TELEPORT;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.player.settings.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.selector.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.mathematics.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.memory.VARIABLE_CLEAR_CASH;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.memory.VARIABLE_DELETE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.memory.VARIABLE_SET_VALUE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.text.GET_SUNRISE_LOCALIZE;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.variable.text.VARIABLE_COMBINE_TEXT;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block.WORLD_FILL_REGION;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block.WORLD_SET_BIOME;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block.WORLD_SET_BLOCK;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.block.WORLD_SET_RANDOM_BLOCK;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.entity.WORLD_SUMMON_ENTITY;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.event.WORLD_CANCEL_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.event.WORLD_RETURN_EVENT;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.log.WORLD_DEBUG;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.log.WORLD_ERROR;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.log.WORLD_LOG;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.log.WORLD_WARNING;
import com.nightshadow.sunrisecreative.creative.coding.elements.action.world.world.WORLD_SET_TIME;
import com.nightshadow.sunrisecreative.creative.coding.elements.conditions.variable.VARIABLE_IF_EQUALS;
import com.nightshadow.sunrisecreative.creative.coding.elements.conditions.variable.VARIABLE_IF_NOT_EQUALS;
import com.nightshadow.sunrisecreative.creative.coding.elements.conditions.data.DATA_IF_EXISTS;
import com.nightshadow.sunrisecreative.creative.coding.elements.conditions.player.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.conditions.variable.*;
import com.nightshadow.sunrisecreative.creative.coding.elements.conditions.world.WORLD_IF_BLOCK_ON_POSITION;
import com.nightshadow.sunrisecreative.creative.coding.elements.conditions.world.WORLD_IF_EVENT_BLOCK_EQUALS;
import com.nightshadow.sunrisecreative.creative.coding.exceptions.ActionNotFoundException;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;


public abstract class Actions {
    static List<Action> actions = new ArrayList<>();
    public static List<Action> getActions() {return actions;}
    public static void loadActions() {
        // Действия над игроком
        actions.add(new PLAYER_SEND_MESSAGE());
        actions.add(new PLAYER_SEND_TITLE());
        actions.add(new PLAYER_SEND_ACTION_BAR());
        actions.add(new PLAYER_GIVE_ITEM());
        actions.add(new PLAYER_SET_EFFECT());
        actions.add(new PLAYER_GIVE_RANDOM_ITEM());
        actions.add(new PLAYER_KILL());
        actions.add(new PLAYER_DAMAGE());
        actions.add(new PLAYER_SET_GAMEMODE());
        actions.add(new PLAYER_SEND_DIALOG());
        actions.add(new PLAYER_SEND_NOTICE());
        actions.add(new PLAYER_MAIN_HAND_ANIMATE());
        actions.add(new PLAYER_OFF_HAND_ANIMATE());
        actions.add(new PLAYER_CLEAR_INVENTORY());
        actions.add(new PLAYER_PLAY_SOUND());
        actions.add(new PLAYER_SET_MAX_HEALTH());
        actions.add(new PLAYER_SET_HEALTH());
        actions.add(new PLAYER_SET_MOVEMENT_SPEED());
        actions.add(new PLAYER_REMOVE_EFFECT());
        actions.add(new PLAYER_SET_FOOD_LEVEL());
        actions.add(new PLAYER_SET_OXYGEN_LEVEL());
        actions.add(new PLAYER_SET_SCALE());
        actions.add(new PLAYER_OPEN_MENU());
        actions.add(new PLAYER_SET_ARMOR());
        actions.add(new PLAYER_STOP_SOUND());
        actions.add(new PLAYER_RANDOM_TELEPORT());
        actions.add(new PLAYER_TELEPORT());

        // ================================================
        // Действия над кодом
        actions.add(new CODE_STOP());
        actions.add(new CODE_WAIT());
        actions.add(new CODE_RUN_LINE());
        actions.add(new CODE_CONDITION_END());

        // ================================================
        // Действия над выборкой
        actions.add(new SELECTOR_RESET_SELECTION());
        actions.add(new SELECTOR_SELECT_ALL_PLAYERS());
        actions.add(new SELECTOR_SELECT_ALL_ENTITIES());
        actions.add(new SELECTOR_SELECT_PLAYER_BY_NAME());
        actions.add(new SELECTOR_SELECT_ENTITY_BY_NAME());

        // ================================================
        // Действия над миром
        actions.add(new WORLD_SET_BLOCK());
        actions.add(new WORLD_SET_RANDOM_BLOCK());
        actions.add(new WORLD_IF_BLOCK_ON_POSITION());
        actions.add(new WORLD_CANCEL_EVENT());
        actions.add(new WORLD_IF_EVENT_BLOCK_EQUALS());
        actions.add(new WORLD_FILL_REGION());
        actions.add(new WORLD_LOG());
        actions.add(new WORLD_WARNING());
        actions.add(new WORLD_DEBUG());
        actions.add(new WORLD_ERROR());
        actions.add(new WORLD_RETURN_EVENT());
        actions.add(new WORLD_SUMMON_ENTITY());
        actions.add(new WORLD_SET_BIOME());
        actions.add(new WORLD_SET_TIME());

        // ================================================
        // Действия над данными
        actions.add(new DATA_SAVE());
        actions.add(new DATA_LOAD());
        actions.add(new DATA_IF_EXISTS());
        actions.add(new DATA_CLEAR());

        // ================================================
        // Действия над переменной
        actions.add(new VARIABLE_ADDITION());
        actions.add(new VARIABLE_SET_VALUE());
        actions.add(new VARIABLE_ADD());
        actions.add(new VARIABLE_IF_SMALLER_OR_EQUALS());
        actions.add(new VARIABLE_IF_SMALLER());
        actions.add(new VARIABLE_IF_GREATER());
        actions.add(new VARIABLE_IF_GREATER_OR_EQUALS());
        actions.add(new VARIABLE_REDUCTION());
        actions.add(new VARIABLE_SUBTRACTION());
        actions.add(new VARIABLE_COS());
        actions.add(new VARIABLE_SIN());
        actions.add(new VARIABLE_TAN());
        actions.add(new VARIABLE_LOG());
        actions.add(new VARIABLE_DELETE());
        actions.add(new VARIABLE_COMBINE_TEXT());
        actions.add(new VARIABLE_CLEAR_CASH());
        actions.add(new GET_SUNRISE_LOCALIZE());
        actions.add(new VARIABLE_IF_EXISTS());


        // ================================================
        // Действия над сущностью
        actions.add(new ENTITY_DAMAGE());
        actions.add(new ENTITY_DELETE());


        // ================================================
        // Если игрок
        actions.add(new PLAYER_IF_SNEAKING());
        actions.add(new PLAYER_IF_HOLD_ITEM());
        actions.add(new PLAYER_IF_GAMEMODE_EQUALS());
        actions.add(new PLAYER_IF_NAME_EQUALS());
        actions.add(new PLAYER_IF_NEAR_POSITION());
        actions.add(new PLAYER_IF_HAVE_ITEM());
        actions.add(new PLAYER_IF_SAW_TO_BLOCK());

        // ================================================
        // Если в мире

        // ================================================
        // Если переменная
        actions.add(new VARIABLE_IF_EQUALS());
        actions.add(new VARIABLE_IF_NOT_EQUALS());

        Bukkit.broadcast(parseColor("&6&l[SunRise Creative+] &r&eLoaded "+actions.size()+" actions"));
    }
    public static Action getAction(String identify) {
        for (Action action : actions) {
            if (action.getClass().getSimpleName().equals(identify)) {
                return action;
            }
        }
        throw new ActionNotFoundException(identify);
    }

    public static String getActionName(Material block) {
        Map<CodingBlocks, String> map = new HashMap<>();
        map.put(CodingBlocks.PLAYER_ACTION, "Действие игрока");
        map.put(CodingBlocks.WORLD_ACTION, "Действие мира");
        map.put(CodingBlocks.CODE_ACTION, "Действие кода");
        map.put(CodingBlocks.DATA_ACTION, "Действ. данных");
        map.put(CodingBlocks.VARIABLE_ACTION, "Действ. перемен.");
        map.put(CodingBlocks.SELECTOR_CONFIGURATOR, "Дейст. селектора");
        map.put(CodingBlocks.ENTITY_ACTION, "Действие сущн.");
        map.put(CodingBlocks.PLAYER_CONDITION, "Если игрок");
        map.put(CodingBlocks.DATA_CONDITION, "Если данные");
        map.put(CodingBlocks.WORLD_CONDITION, "Если в мире");
        map.put(CodingBlocks.VARIABLE_CONDITION, "Если переменная");
        return map.get(CodingBlocks.getCodingBlockByBlockMaterial(block));
    }

}

