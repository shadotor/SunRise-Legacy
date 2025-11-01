package com.nightshadow.sunrisecreative.creative.coding.values;

import com.nightshadow.sunrisecreative.creative.coding.exceptions.IncompatibleTypesException;
import com.nightshadow.sunrisecreative.creative.world.World;
import com.nightshadow.sunrisecreative.creative.world.WorldVariableData;
import com.nightshadow.sunrisecreative.indev.api.database.Serializable;
import it.unimi.dsi.fastutil.Pair;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.locationFromString;
import static com.nightshadow.sunrisecreative.creative.world.worldManager.getWorldByID;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Variable implements Serializable
{
    String name;
    World w;
    public Variable(World world, String name) {
        this.w = world;
        this.name = name;
    }
    public ValueType getType() {
        try {
            parseDouble(getValue().toString());
            return ValueType.NUMBER;
        } catch (NumberFormatException ex) {
            if (getValue() instanceof ItemStack) return ValueType.ITEM;
            if (locationFromString(getValue().toString(), w.getWorld()) != null) return  ValueType.POSITION;
            return ValueType.TEXT;
        }
    }
    public void setValue(Object value) {
        new WorldVariableData(w).saveVariable(name, value);
    }
    public void delete() {
        new WorldVariableData(w).saveVariable(name,null);
    }
    public String getName() {
        return name;
    }
    public Object getValue() {
        return new WorldVariableData(w).getVariable(name);
    }
    @Override
    public String toString() {
        return "SRV//"+w.getID()+"//"+name;
    }
    public static Variable variableFromString(String variable) {
        String[] splitter = variable.split("//");
        sendConsole(splitter.length+"");
        if(splitter.length == 3) {
            int ID = parseInt(splitter[1]);
            String name = splitter[2];
            return new Variable(getWorldByID(ID), name);
        }
        else throw new IncompatibleTypesException(ValueType.VARIABLE);
    }
    public String getPlaceholder() {
        return "SRV.ValueOf()//"+w.getID()+"//"+name;
    }

    @Override
    public String serializeString() {
        return "SRV.ValueOf()//"+w.getID()+"//"+name;
    }

    @Override
    public Map<String, Object> serializeMap() {
        return Map.of("world",w.getID(),"name",name);
    }

    @Override
    public Serializable deserializeString(String s) {
        return variableFromString(s);
    }

    @Override
    public Serializable deserializeMap(Map<String, Object> map) {
        if (map.size() == 2) {
            return new Variable(getWorldByID(parseInt(map.get("world").toString())), map.get("name").toString());
        } else throw new IncompatibleTypesException(ValueType.VARIABLE);
    }
}
