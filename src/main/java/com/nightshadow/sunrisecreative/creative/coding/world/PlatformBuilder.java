package com.nightshadow.sunrisecreative.creative.coding.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.fillBlock;
import static com.nightshadow.sunrisecreative.api.utils.BlockUtils.setBlock;

public class PlatformBuilder {
    public static int countOfLines = 100;
    public static int countOfColumns = 100;
    public static int globalOffset = 3;
    public static int lineOffset = 4;
    public static int sizeOfLine = (countOfLines * lineOffset) + (globalOffset*2);
    public static int sizeOfColumn = (countOfColumns*2) + (globalOffset*2);
    public static void createPlatform(World w) {

        sendConsole("Size of line: " + sizeOfLine + " Size of column: " + sizeOfColumn);

        Location from = getFrom(w);
        Location to = getTo(w);

        Location start = from;
        fillBlock(start, to, Material.WHITE_STAINED_GLASS);

        Location block = start;
        block = block.add(globalOffset, 0, globalOffset);
        for (int line = 0; line <= countOfColumns; line++) {
            setBlock(start,Material.BLUE_STAINED_GLASS);
            block = block.add(2,0,0);
            for (int column = 0; column <= countOfColumns; column++) {
                setBlock(start, Material.GRAY_STAINED_GLASS);
                block = block.add(2,0,0);
            }
            block = block.add(0,0,lineOffset);
            block.setX(globalOffset);
        }
    }
    public static Location getFrom(World w) {
        return new Location(w, 0,0,0);
    }
    public static Location getTo(World w) {
        return new Location(w,  sizeOfColumn ,0, sizeOfLine);
    }
}
