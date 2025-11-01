package com.nightshadow.sunrisecreative.creative.coding.primitives;

import com.nightshadow.sunrisecreative.creative.coding.handler.EventType;
import com.nightshadow.sunrisecreative.creative.coding.reader.Reader;
import com.nightshadow.sunrisecreative.creative.coding.reader.Selector;
import com.nightshadow.sunrisecreative.creative.world.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.List;

public abstract class CodingEvent extends CodeElement {
    Event event;
    public void onExecute(World world, Selector selector){
        Reader reader = new Reader(world);
        List<Integer> ints = reader.findEvent(this.getClass().getSimpleName());
        for (int i : ints) {
            reader.setLine(i);
            reader.setColumn(0);
            reader.setSelector(selector);
            if (this.event == null) {
                reader.runLine();
            } else reader.runLine(this.event);

        }
    }
    public void onExecute(World world, Entity entity) {
        Selector selector = new Selector();
        selector.setSelector(new Entity[] {entity});
        Reader reader = new Reader(world);
        List<Integer> ints = reader.findEvent(this.getClass().getSimpleName());
        for (int i : ints) {
            reader.setLine(i);
            reader.setColumn(0);
            reader.setSelector(selector);
            if (this.event == null) {
                reader.runLine();
            } else reader.runLine(this.event);

        }
    }
    public void onExecute(World world, Player player) {
        Selector selector = new Selector();
        selector.setSelector(new Player[] {player});
        Reader reader = new Reader(world);
        List<Integer> ints = reader.findEvent(this.getClass().getSimpleName());
        for (int i : ints) {
            reader.setLine(i);
            reader.setColumn(0);
            reader.setSelector(selector);
            if (this.event == null) {
                reader.runLine();
            } else reader.runLine(this.event);

        }
    }
    public CodingEvent setEvent(Event e) {
        this.event = e;
        return this;
    }

    public abstract EventType getType();



}