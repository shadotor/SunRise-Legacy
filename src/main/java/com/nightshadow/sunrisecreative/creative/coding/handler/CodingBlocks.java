package com.nightshadow.sunrisecreative.creative.coding.handler;

import com.nightshadow.sunrisecreative.api.menu.Item;
import com.nightshadow.sunrisecreative.api.utils.Language;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Action;
import com.nightshadow.sunrisecreative.creative.coding.primitives.Actional;
import com.nightshadow.sunrisecreative.creative.coding.primitives.CodingEvent;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.localizeOnly;
import static com.nightshadow.sunrisecreative.api.utils.PlayerUtils.setItems;
import static com.nightshadow.sunrisecreative.creative.coding.handler.Actions.getActions;

public enum CodingBlocks {
    // Events
    WORLD_EVENT(Material.REDSTONE_BLOCK, Material.REDSTONE_ORE, Material.MANGROVE_WALL_SIGN, DyeColor.RED, "items.world-event.title", "items.world-event.description", CodingBlockCategories.EVENT, EventType.WORLD),
    ENTITY_EVENT(Material.GOLD_BLOCK, Material.GOLD_ORE, Material.BAMBOO_WALL_SIGN, DyeColor.YELLOW, "items.entity-event.title", "items.entity-event.description", CodingBlockCategories.EVENT, EventType.ENTITY),
    PLAYER_EVENT(Material.DIAMOND_BLOCK, Material.DIAMOND_ORE, Material.WARPED_WALL_SIGN, DyeColor.LIGHT_BLUE, "items.player-event.title", "items.player-event.description", CodingBlockCategories.EVENT, EventType.PLAYER),

    // === ACTIONS ===
    // Действия над миром
    WORLD_ACTION(Material.NETHERRACK, Material.NETHER_GOLD_ORE, Material.MANGROVE_WALL_SIGN, DyeColor.RED, "items.world-action.title", "items.world-action.description", CodingBlockCategories.ACTION, ActionType.WORLD),
    // Действия над кодом
    CODE_ACTION(Material.COAL_BLOCK, Material.DEEPSLATE_COAL_ORE, Material.DARK_OAK_WALL_SIGN, DyeColor.GRAY, "items.code-action.title", "items.code-action.description", CodingBlockCategories.ACTION, ActionType.CODE),
    SELECTOR_CONFIGURATOR(Material.PURPUR_BLOCK, Material.PURPUR_PILLAR, Material.CHERRY_WALL_SIGN, DyeColor.PURPLE, "items.selector-action.title", "items.selector-action.description", CodingBlockCategories.ACTION, ActionType.SELECTOR),

    // Действия над переменными
    DATA_ACTION(Material.RAW_GOLD_BLOCK, Material.DEEPSLATE_GOLD_ORE, Material.BAMBOO_WALL_SIGN, DyeColor.YELLOW, "items.data-action.title", "items.data-action.description", CodingBlockCategories.ACTION, ActionType.DATA),
    VARIABLE_ACTION(Material.IRON_BLOCK, Material.IRON_ORE, Material.CHERRY_WALL_SIGN, DyeColor.WHITE, "items.variable-action.title", "items.variable-action.description", CodingBlockCategories.ACTION, ActionType.VARIABLE),

    // Действия над существами
    PLAYER_ACTION(Material.COBBLESTONE, Material.STONE, Material.DARK_OAK_WALL_SIGN, DyeColor.LIGHT_GRAY, "items.player-action.title", "items.player-action.description", CodingBlockCategories.ACTION, ActionType.PLAYER),
    ENTITY_ACTION(Material.MOSSY_COBBLESTONE, Material.STONE, Material.DARK_OAK_WALL_SIGN, DyeColor.LIME, "items.entity-action.title", "items.entity-action.description", CodingBlockCategories.ACTION, ActionType.ENTITY),

    // Условия
    PLAYER_CONDITION(Material.OAK_PLANKS, Material.STRIPPED_OAK_WOOD, Material.OAK_WALL_SIGN, DyeColor.ORANGE, "items.player-condition.title", "items.player-condition.description", CodingBlockCategories.CONDITION, ActionType.PLAYER),
    VARIABLE_CONDITION(Material.OBSIDIAN, Material.BLACKSTONE, Material.DARK_OAK_WALL_SIGN, DyeColor.BLUE, "items.variable-condition.title", "items.variable-condition.description", CodingBlockCategories.CONDITION, ActionType.VARIABLE),
    DATA_CONDITION(Material.BAMBOO_PLANKS, Material.YELLOW_GLAZED_TERRACOTTA, Material.BAMBOO_WALL_SIGN, DyeColor.YELLOW, "items.data-condition.title", "items.data-condition.description", CodingBlockCategories.CONDITION, ActionType.DATA),
    WORLD_CONDITION(Material.RED_NETHER_BRICKS, Material.NETHER_QUARTZ_ORE, Material.OAK_WALL_SIGN, DyeColor.RED, "items.world-condition.title", "items.world-condition.description", CodingBlockCategories.CONDITION, ActionType.WORLD);

    private final Material block;
    private final String name;
    private final String description;
    private final Material connectorMaterial;
    private final Material signMaterial;
    private final DyeColor signColor;
    private final CodingBlockCategories category;
    private ActionType actionType;
    private EventType eventType;

    CodingBlocks(Material block, Material connectorMaterial, Material signMaterial, DyeColor signColor, String nameTranslationKey, String descriptionTranslationKey, CodingBlockCategories category, ActionType type) {
        this.block = block;
        this.connectorMaterial = connectorMaterial;
        this.signMaterial = signMaterial;
        this.signColor = signColor;
        this.name = nameTranslationKey;
        this.description = descriptionTranslationKey;
        this.category = category;
        this.actionType = type;
    }

    CodingBlocks(Material block, Material connectorMaterial, Material signMaterial, DyeColor signColor, String nameTranslationKey, String descriptionTranslationKey, CodingBlockCategories category, EventType type) {
        this.block = block;
        this.connectorMaterial = connectorMaterial;
        this.signMaterial = signMaterial;
        this.signColor = signColor;
        this.name = nameTranslationKey;
        this.description = descriptionTranslationKey;
        this.category = category;
        this.eventType = type;
    }

    private Item getItem(Language lang) {
        List<String> description = new ArrayList<>();
        description.add("");
        description.add(localizeOnly(this.description, lang));
        description.add("");
        description.add("&7Type: " + localizeOnly(category.translationKey, lang));
        return new Item(block, localizeOnly(name, lang), description);
    }

    public static CodingBlocks get(Material block) {
        for (CodingBlocks value : CodingBlocks.values()) {
            if (value.block == block) {
                return value;
            }
        }
        return null;
    }

    public List<Action> getBlockActions() {
        List<Action> actions = new ArrayList<>();
        if (this.category == CodingBlockCategories.ACTION) {
            for (Action action : getActions()) {
                if (!action.getClass().getSimpleName().contains("IF")) {
                    if (action.getType() == actionType) {
                        if (action.getIcon() != null) {
                            if (!action.getIcon().isHidden()) actions.add(action);
                        } else actions.add(action);
                    }
                }
            }
        }
        if (this.category == CodingBlockCategories.CONDITION) {
            for (Action action : getActions()) {
                if (action.getClass().getSimpleName().contains("IF")) {
                    if (action.getType() == actionType) {
                        if (action.getIcon() != null) {
                            if (!action.getIcon().isHidden()) actions.add(action);
                        } else actions.add(action);
                    }
                }
            }
        }
        return actions;
    }

    public List<CodingEvent> getBlockEvents() {
        List<CodingEvent> events = new ArrayList<>();
        if (this.category == CodingBlockCategories.EVENT) {
            for (CodingEvent event : Events.getEvents()) {
                if (event.getType() == eventType) {
                    if (event.getIcon() != null) {
                        if (!event.getIcon().isHidden()) events.add(event);
                    } else events.add(event);
                }
            }
            return events;
        } else return null;

    }

    public Material getBlock() {
        return this.block;
    }

    public Material getConnector() {
        return this.connectorMaterial;
    }

    public Material getSignMaterial() {
        return this.signMaterial;
    }

    public DyeColor getSignColor() {
        return this.signColor;
    }

    public CodingBlockCategories getCategory() {
        return this.category;
    }

    public static CodingBlocks getCodingBlockByBlockMaterial(Material m) {
        for (CodingBlocks value : values()) {
            if (value.getBlock() == m) {
                return value;
            }
        }
        return null;
    }

    public static List<Material> getEventBlocks() {
        List<Material> list = new ArrayList<>();
        for (CodingBlocks value : values()) {
            if (value.getCategory() == CodingBlockCategories.EVENT) {
                list.add(value.block);
            }
        }
        return list;
    }

    public static List<Material> getActionBlocks() {
        List<Material> list = new ArrayList<>();
        for (CodingBlocks value : values()) {
            if (value.getCategory() == CodingBlockCategories.ACTION || value.getCategory() == CodingBlockCategories.CONDITION) {
                list.add(value.block);
            }
        }
        return list;
    }

    public static List<Material> getCodingBlocks() {
        List<Material> list = new ArrayList<>();
        for (CodingBlocks value : values()) {
            list.add(value.block);
        }
        return list;
    }

    public static void giveItems(Player p) {
        Map<Integer, Item> map = new HashMap<>();
        Language lang = getPlayerLang(p);

        // Хотбар
        map.put(0, PLAYER_EVENT.getItem(lang));
        map.put(1, PLAYER_ACTION.getItem(lang));
        map.put(2, PLAYER_CONDITION.getItem(lang));
        map.put(3, WORLD_ACTION.getItem(lang));
        map.put(4, ENTITY_ACTION.getItem(lang));

        // Строки, сверху вниз
        map.put(9, WORLD_EVENT.getItem(lang));
        map.put(10, ENTITY_EVENT.getItem(lang));

        map.put(18, WORLD_CONDITION.getItem(lang));
        map.put(19, DATA_CONDITION.getItem(lang));
        map.put(20, VARIABLE_CONDITION.getItem(lang));

        map.put(27, VARIABLE_ACTION.getItem(lang));
        map.put(29, CODE_ACTION.getItem(lang));
        map.put(28, DATA_ACTION.getItem(lang));
        map.put(30, SELECTOR_CONFIGURATOR.getItem(lang));

        map.put(8, new Item(Material.IRON_INGOT, "&eCode Values"));
        setItems(map, p);
    }
}


