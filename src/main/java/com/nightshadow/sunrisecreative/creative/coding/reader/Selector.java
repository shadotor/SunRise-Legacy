package com.nightshadow.sunrisecreative.creative.coding.reader;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Selector {
    public Player[] players;
    public Entity[] entities;
    Runner runner;
    public String getType() {
        if (players == null && entities != null) {
            return "EntitySelector";
        }
        else if (players != null && entities == null) {
            return "PlayerSelector";
        }
        else if (players != null && entities != null) {
            return "MultiSelector";
        }
        else return "EmptySelector";
    }
    public World getWorld() {
        return runner.getReader().getWorld().getWorld();
    }
    public void resetSelect()  {
        players = null;
        entities = null;
    }
    public void setSelector(Player[] players) {
        this.players = players;
        this.entities = null;
    }
    public void setSelector(Entity[] entities) {
        this.entities = entities;
        this.players = null;
    }
    public void setSelector(Entity[] entities, Player[] players) {
        this.entities = entities;
        this.players = players;
    }
    public void setRunner(Runner r) {
        this.runner = r;
    }
    public Runner getRunner() {
        return this.runner;
    }
}
