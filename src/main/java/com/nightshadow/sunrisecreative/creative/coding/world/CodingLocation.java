package com.nightshadow.sunrisecreative.creative.coding.world;

import org.bukkit.Location;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static java.lang.String.valueOf;

public class CodingLocation {
    private final int line;
    private final int column;
    public CodingLocation(int line, int column) {
        this.line = line;
        this.column = column;
    }

    /**
     * Получает локацию кода из локации в мире
     * @param loc локация кода
     * @return CodingLocation
     */
    public static CodingLocation getCodingLocation(Location loc) {
        int column = (loc.getBlockX()- PlatformBuilder.globalOffset-2)/2;
        int line = (loc.getBlockZ()- PlatformBuilder.globalOffset)/PlatformBuilder.lineOffset;
        return new CodingLocation(line,column);
    }
    public static boolean isCodingLocation(Location loc) {
        boolean result = ((loc.getBlockX()- PlatformBuilder.globalOffset-2) % 2 == 0 ) && ((loc.getBlockZ()- PlatformBuilder.globalOffset) % PlatformBuilder.lineOffset == 0);
        sendConsole(valueOf(result));
        return result;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
