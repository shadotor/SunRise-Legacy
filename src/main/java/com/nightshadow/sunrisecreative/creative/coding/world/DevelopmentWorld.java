package com.nightshadow.sunrisecreative.creative.coding.world;

import com.nightshadow.sunrisecreative.creative.world.generator.VoidGenerator;
import net.kyori.adventure.util.TriState;
import org.bukkit.*;

import static com.nightshadow.sunrisecreative.creative.coding.world.PlatformBuilder.*;

public class DevelopmentWorld {
    World world;
    public DevelopmentWorld(com.nightshadow.sunrisecreative.creative.world.World w) {
        WorldCreator creator = new WorldCreator("coding/world_"+w.getID());
        creator.generator(new VoidGenerator());
        creator.generateStructures(false);
        creator.type(WorldType.FLAT);
        creator.keepSpawnLoaded(TriState.FALSE);
        world = creator.createWorld();
        Location center = new Location(world, (getFrom(world).x()+getTo(world).x())/2,(getFrom(world).y()+getTo(world).y())/2, (getFrom(world).z()+getTo(world).z())/2 );
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS,false);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES,false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS,false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE,false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING,false);
        world.setGameRule(GameRule.LOCATOR_BAR,false);
        createPlatform(world);
    }
    public DevelopmentWorld(World world) {
        this.world = world;
    }
    public World getWorld() {return world;}
}
