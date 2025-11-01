package com.nightshadow.sunrisecreative.creative.world;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import com.nightshadow.sunrisecreative.creative.coding.world.DevelopmentWorld;
import com.nightshadow.sunrisecreative.creative.coding.elements.event.player.world.PLAYER_JOIN_EVENT;
import com.nightshadow.sunrisecreative.creative.command.other.Spawn;
import com.nightshadow.sunrisecreative.creative.world.generator.Generator;
import com.nightshadow.sunrisecreative.creative.world.generator.VoidGenerator;
import com.nightshadow.sunrisecreative.api.menu.Item;
import net.kyori.adventure.util.TriState;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.fillBlock;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.setBlock;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getLocale;
import static com.nightshadow.sunrisecreative.api.utils.LangUtils.getPlayerLang;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static com.nightshadow.sunrisecreative.creative.command.other.Spawn.teleportSpawn;
import static com.nightshadow.sunrisecreative.creative.world.WorldConfig.*;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.*;

public class World {
    private DevelopmentWorld coding = null;
    String name;
    private final int ID;
    int size;
    ItemStack icon = new ItemStack(Material.GRASS_BLOCK);
    org.bukkit.World world;
    private String author;

    public World(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }
    public World(String name, int ID, String owner) {
        this.author = owner;
        this.name = name;
        this.ID = ID;
        try {
            this.createConfig();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Генерирует мир
     * @param generator - тип генерации (void,survival,flat)
     * @param ID - ID мира(требуется удаление)
     * @param size - размер мира
     */
    public void generateWorld(Generator generator, int ID, int size) {
        WorldCreator creator = new WorldCreator("worlds/world_"+ID);
        creator.generateStructures(false);
        if (generator == Generator.VOID) creator.generator(new VoidGenerator());
        if (generator == Generator.VOID || generator == Generator.FLAT) creator.type(WorldType.FLAT);
        if (generator == Generator.SURVIVAL) creator.type(WorldType.NORMAL);
        WorldConfig.setKey(this, "data", "generator", generator.name());
        creator.keepSpawnLoaded(TriState.FALSE);
        org.bukkit.World world = Bukkit.createWorld(creator);
        if (generator == Generator.VOID) {
            fillBlock(new Location(world, -1, 0, -1), new Location(world, 1, 0, 1), Material.LIGHT_GRAY_STAINED_GLASS);
            setBlock(new Location(world, 0, 0, 0), Material.LODESTONE);
        }
        world.getWorldBorder().setSize(size);
        if (generator == Generator.VOID ) world.setSpawnLocation(0,1,0);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES,false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        worlds.add(world);
        worldList.add(this);
        this.world = world;
    }
    public org.bukkit.World getWorld() {
        if (world == null) {
            for (org.bukkit.World world1 : worlds) {
                if (("" + ID).equals(world1.getName().replace("worlds/world_", ""))) {
                    this.world = world1;
                    return world1;
                }
            }
            return Bukkit.getWorld("spawn");
        }
        else return this.world;
    }
    public void updateSize(int size) {
        this.size = size;
        this.world.getWorldBorder().setSize(size);
    }
    public Location getSpawnLoc() {
        this.getWorld();
        return this.world.getSpawnLocation();
    }

    void createConfig() {
        WorldConfig.createConfig(this);
        YamlConfiguration config = getConfig(this, "data");
        sendConsole(this.author);
        config.set("author", this.author);
        config.set("world-icon", this.icon);
        config.set("world-name", this.name);
        config.set("world-id", this.ID);
        saveConfig(this, "data", config);
        setKey(this, "data", "author", this.author);
    }
    public String getAuthorName() {
        author = getKey(this,"data","author").toString();
        return author;
    }
    public Player getAuthor() {
        Plugin plugin = Sunrise_Creative.getInstance();
        Player player;
        if (getKey(this,"data", "author") instanceof String)  {
            player = Bukkit.getPlayer(getKey(this,"data", "author").toString());
            return player;
        }
        else plugin.getLogger().info("ERROR 404");
        return null;
    }
        public Item getIcon() {
        String name = "&f"+ getKey(this, "data", "world-name").toString();
        List<String> description = new ArrayList<>();
        description.add("");
        description.add("&7Мир игрока " + getKey(this, "data", "author").toString());
        description.add("");
        description.add("&7ЛКМ - зайти в мир");
        description.add("");
        description.add("&7ID:" + ID);
        Item item = new Item((ItemStack) getKey(this,"data","world-icon"), name, description );
        if (world.getPlayerCount() > 1) item.setCount(world.getPlayerCount());
        else item.setCount(1);
        return item;
    }
    public void teleportWorld(Player p) {
        if ((getAvailability() && ! isBlacklisted(p) ) || isWhitelisted(p) || isAdmin(p) || isOwner(p)) {
            p.getInventory().clear();
            p.setHealth(20);
            p.setFoodLevel(20);
            p.clearActivePotionEffects();
            p.teleport(getSpawnLoc());
            p.setBedSpawnLocation(new Location(Bukkit.getWorld("spawn"), 0, -9999, 0));
            if (isOwner(p) || isBuilder(p) || isAdmin(p)) {
                sendConsole(p.getName() + " Имеет право");
                p.setGameMode(GameMode.CREATIVE);
            } else {
                sendConsole(p.getName() + " Не имеет права в отличии от" + getAuthorName() );
                Object gamemode = getKey(this, "data", "gamemode");
                if (gamemode != null) {
                    p.setGameMode(GameMode.valueOf(gamemode.toString().toUpperCase(Locale.ROOT)));
                } else p.setGameMode(GameMode.ADVENTURE);

            }
            new PLAYER_JOIN_EVENT().onExecute(this, p);
            this.getWorld().getPlayers().forEach( (pl) -> {
                pl.sendMessage(parseColor("&e" + p.getName() + " &fзашёл в мир!"));
            });
        }
        else p.sendMessage(parseColor("&cВы не можете зайти в данный мир!"));
}
    public int getID() {
        return this.ID;
    }



    // Проверки

    /** If player is Blacklisted is world, return true, else return false*/
    public boolean isBlacklisted(Player p) {
        Object isPlayer = getKey(this, "blacklist", p.getName());
        if (isPlayer != null) return true;
        else return false;
    }

    public boolean isWhitelisted(Player p) {
        Object isPlayer = getKey(this, "whitelist", p.getName());
        if (isPlayer != null) return true;
        else return false;
    }

    public boolean isDeveloper(Player p) {
        Object isPlayer = getKey(this, "developer", p.getName());
        return isPlayer != null;
    }

    public boolean isBuilder(Player p) {
        Object isPlayer = getKey(this, "builder", p.getName());
        return isPlayer != null;
    }

    public boolean isAdmin(Player p) {
        Object isPlayer = getKey(this, "admin", p.getName());
        return isPlayer != null;
    }

    public boolean isOwner(Player p) {
        Object isPlayer = getAuthorName();
        return String.valueOf(isPlayer).equals(p.getName());
    }
    public List<Player> getPlayers() {
        if (world != null) {
            List<Player> players = world.getPlayers();
            players.addAll(getCodingWorld().getWorld().getPlayers());
            return players;
        }
        else return getWorld().getPlayers();
    }
    public void updateDevelopmentWorld() {
        Plugin plugin = Sunrise_Creative.getInstance();
        File codingWorldFolder = new File(plugin.getDataFolder().getParentFile().getParent(), "coding");
        File codingWorld = new File(codingWorldFolder, "world_"+ID);
        if (! codingWorld.exists() || ! codingWorld.isDirectory()) coding = new DevelopmentWorld(this);
        else {
            for(File file : codingWorld.listFiles()) {
                if (file.getName().equals("level.dat")) {
                    WorldCreator worldCreator = new WorldCreator("coding/world_"+ID);
                    worldCreator.generator(new VoidGenerator());
                    coding = new DevelopmentWorld(Bukkit.getServer().createWorld(worldCreator));
                }
            }
        }
    }
    public DevelopmentWorld getCodingWorld() {
        updateDevelopmentWorld();
        return coding;
    }

    /**
     *
     * @return true, if world is open, else false
     */
    public boolean getAvailability() {
        Object availability = getKey(this, "data", "availability");
        if (availability != null ) {
            return Boolean.parseBoolean(availability.toString());
        }
        else return true;
    }
    public boolean ifPlayerHasPermission(Player p, WorldPermission permission) {
        Object isPlayer = getKey(this, permission.name().toLowerCase(), p.getName());
        return isPlayer != null;
    }
    public void setStatement(WorldStatement state) {
        setKey(this,"data","world-state", state.name());
    }
    public WorldStatement getStatement() {
        return WorldStatement.valueOf(getKey(this,"data", "world-state", "BUILD").toString());
    }
    public void delete() {
        this.world.getPlayers().forEach( (p) -> {
                    p.sendMessage(getLocale("creative.message.world-shutdown",getPlayerLang(p)));
                    teleportSpawn(p);
                }
        );
        Bukkit.unloadWorld("worlds/world_" + ID, false);
        worldList.remove(this);
        try {
            Plugin plugin = Sunrise_Creative.getInstance();
            FileUtils.deleteDirectory(new File(plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile(), "worlds/world_" + ID));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

