package com.nightshadow.sunrisecreative.api.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Math.round;

public class BlockUtils {
    public static Location roundLocation(Location x) {
        return new Location(x.getWorld(), round(x.getBlockX()), round(x.getBlockY()), round(x.getBlockZ()));
    }
    public static String stringFromLocation(Location x) {
        return "" + x.getBlockX() + x.getBlockY() + x.getBlockZ();
    }
    public static Location locationFromString(String s, World w) {
        String[] coords = s.split(" ");
        if (coords.length == 3) {
            double x = parseDouble(coords[0]);
            double y = parseDouble(coords[1]);
            double z = parseDouble(coords[2]);
            return new Location(w,x,y,z);
        } else return null;
    }
    public static void fillBlock(Location from, Location to, Material block) {
        for (int x = from.getBlockX(); x <= to.getBlockX(); x++ ) {
            for (int y = from.getBlockY(); y <= to.getBlockY();  y++) {
                for (int z = from.getBlockZ(); z <= to.getBlockZ(); z++) {
                    Location loc = new Location(from.getWorld(),x,y,z);
                    setBlock(loc, block);
                }
            }
        }
    }

    /**
     * Устанавливает блок на позиции
      * @param loc - Позиция
     * @param m - Блок
     */
    public static void setBlock(Location loc, Material m) {
        loc.getBlock().setType(m);
    }
    public static void removeBlock(Location loc) {
        setBlock(loc, Material.AIR);
    }
    public static void clearArea(Location from, Location to) {
        for (int x = from.getBlockX(); x <= to.getBlockX(); x++ ) {
            for (int y = from.getBlockY(); y <= to.getBlockY();  y++) {
                for (int z = from.getBlockZ(); z <= to.getBlockZ(); z++) {
                    Location loc = new Location(from.getWorld(),x,y,z);
                    setBlock(loc, Material.AIR);
                }
            }
        }
    }
}
