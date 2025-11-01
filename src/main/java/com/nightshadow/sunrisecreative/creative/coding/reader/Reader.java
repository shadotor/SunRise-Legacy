package com.nightshadow.sunrisecreative.creative.coding.reader;

import com.nightshadow.sunrisecreative.Sunrise_Creative;
import com.nightshadow.sunrisecreative.creative.coding.world.CodingLocation;
import com.nightshadow.sunrisecreative.creative.world.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;
import static java.lang.Integer.parseInt;

/**
 * Reader кода
 * Читает код, модифицирует его.
 */
public class Reader {
    World world;
    YamlConfiguration code;
    int line = 0;
    int column = 0;
    Selector selector = new Selector();
    public Reader(@NotNull World world) {
        this.world = world;
        Plugin plugin = Sunrise_Creative.getInstance();
        File file = new File(plugin.getDataFolder(), "coding/world_" + world.getID() + ".yml");
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        code = YamlConfiguration.loadConfiguration(file);
    }

    public int getColumn() {
        return column;
    }

    @ApiStatus.Experimental
    public void offset() {
        ConfigurationSection beforeOffset = code.getConfigurationSection("line_"+line);
        ConfigurationSection afterOffset = code.getConfigurationSection("line_"+line);
        Set<String> keys = beforeOffset.getKeys(false);
        for (String key : keys) {
            sendConsole(key);
            if (! key.equals("event")) {
                if (parseInt(key.replace("column_", "")) >= column) {

                    String newKey = "column_" + (parseInt(key.replace("column_", "")) + 1);
                    sendConsole("Prev: " + key + "Now:" + newKey);
                    sendConsole(beforeOffset.get(key+".action").toString());
                    Object action = beforeOffset.get(newKey);
                    afterOffset.set(newKey, action);
                }
            }
        }
        afterOffset.set("column_" + column, null);
        code.set("line_"+line, afterOffset);
        try {
            code.save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Файл кода мира
     */
    public File getFile() {
        Plugin plugin = Sunrise_Creative.getInstance();
        return new File(plugin.getDataFolder(), "coding/world_" + world.getID() + ".yml");
    }

    /**
     * Запускает выбранную строку чтения кода
     */
    public void runLine() {
        if (this.getColumnCount() > 0) new Runner(duplicate(this)).run();
    }
    public void runLine(Event event) {
        if (this.getColumnCount() > 0) new Runner(duplicate(this), event).run();
    }

    /**
     * @return количество колонок в выбранной строке
     */
    public int getColumnCount() {
            int columns = 0;
            for (int i = 0; i <= getLastColumn(); i++) {
                if (columnExists(line,i)) columns++;
            }
            return columns;
    }

    /**
     *
     * @param EventName - Название события
     * @return List<Integer> - список индексов строк с событиями.
     * */
    public List<Integer> findEvent(String EventName) {
        int lastLine = getLine();
        setLine(0);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= getLastLine(); i++) {
            setLine(i);
            if (getEventName() != null) {
                if (EventName.equals(getEventName())) {
                    list.add(i);
                }
            }

        }
        setLine(lastLine);
        return list;
    }

    /**
     * Переводит чтец кода на следующую строку
     */
    public void nextLine() {
        line++;
    }

    /**
     * Получает количество строк кода
     * @return количество линий
     */
    public int getLinesCount() {
        int lines = 0;
        for (int i = 0; i != getLastLine(); i++) {
            if (lineExists(i)) lines++;
        }
        return lines;
    }

    /**
     * Переводит чтец кода на предыдущую строку кода.
     */
    public void previousLine() {
        line--;
    }

    /**
     * Устанавливает чтец кода на определённую строку
     * @param lineNumber - индекс строки
     */
    public void setLine(int lineNumber) {
        line = lineNumber;
    }
    public void nextColumn() {
        column++;
    }
    public int getLastLine() {
        Set<String> keys = code.getKeys(false);
        int lastLine = 0;
        for (String key : keys) {
            if (key.startsWith("line_")) {
                int index = parseInt(key.replace("line_",""));
                if (index > lastLine) lastLine = index;
            }
        }
        return lastLine;
    }
    public int getLastColumn() {
        Set<String> keys = code.getConfigurationSection("line_"+line).getKeys(false);
        int lastLine = 0;
        for (String key : keys) {
            if (key.startsWith("column_")) {
                int index = parseInt(key.replace("column_",""));
                if (index > lastLine) lastLine = index;
            }
        }
        return lastLine;
    }
    public void previousColumn() {
        column--;
    }
    public void setColumn(int columnNumber) {
        column = columnNumber;
    }
    public void setSelector(Selector sel) {
        selector = sel;
    }
    public int getLine() {return line;}
    public int getLevel() {
        return code.getInt(getPath()+".level",0);
    }
    public void setLevel(int level) {
        code.set(getPath()+".level", level);
    }

    /**
     * Получает название события в выбранной строке
     * @return название события в выбранной строке.
     */
    public String getEventName() {
        if (code.get("line_"+line+".event") != null ) {
            return code.get("line_"+line+".event").toString();
        }
        else {
            return null;
        }
    }

    /**
     * Получает выбранное действие в кода
     * @return ActionReader, действие, которое находится в данной точке
     */
    public ActionReader getAction() {
        if (code.get(getPath()+".action") != null) return new ActionReader(code.get(getPath()+".action").toString(), getArgs(), selector);
        else return null;
    }

    /**
     * Устанавливает событие на выбранной позиции
     * @param event Название событие
     */
    public void setEvent(String event) {
        code.set("line_"+line+".event",event);
        try {
            code.save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param action - Название действия
     */
    public void setAction(String action) {
        code.set(getPath()+".action", action);
        try {
            code.save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param id - Индекс аргумента
     * @param value - значение аргумента
     * @throws IOException
     */
    public void setArgument(String id, String value) throws IOException {
            code.set(getPath()+".args."+id, value);
            code.save(getFile());
    }
    public void setArgument(String id, Object value) throws IOException {
        code.set(getPath()+".args."+id, value);
        code.save(getFile());
    }

    /**
     * Удаляет строку на выделенной точке
     */
    public void removeLine() {
        code.set("line_"+line, null);
        try {
            code.save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаляет действие на выделенной точке
     */
    public void removeColumn() {
        code.set(getPath(),null);
        try {
            code.save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getActions() {
        setColumn(0);
        List<String> actionList = new ArrayList<>();
        for (int i = 0; i< getColumnCount(); i++) {
            actionList.add(getAction().action);
        }
        return actionList;
    }
    public List<String> getLines() {
        setLine(0);
        List<String> eventList = new ArrayList<>();
        for (int i = 0; i < getLinesCount(); i++) {
            eventList.add(getEventName());
        }
        return eventList;
    }
    public List<String> getArgsList() {
        List<String> ret = new ArrayList<>();
        for (Object arg : getArgs()) {
           ret.add(arg.toString());
        }
        return ret;
    }
    public boolean lineExists(int line) {
        setLine(line);
        return getEventName() != null;
    }
    public boolean lineExists(CodingLocation location) {
        setLine(location.getLine());
        return getEventName() != null;
    }
    public boolean columnExists(int line, int column) {
        setLine(line);
        setColumn(column);
        return getAction() != null;
    }
    public void setPosition(CodingLocation l) {
        setLine(l.getLine());
        setColumn(l.getColumn());
    }
    public World getWorld() {
        return this.world;
    }


    public static Reader duplicate(Reader r) {
        Reader ret = new Reader(r.world);
        ret.setSelector(r.getAction().getSelector());
        ret.setLine(r.getLine());
        ret.setColumn(r.column);
        return ret;
    }

    Object[] getArgs() {
        ConfigurationSection args = code.getConfigurationSection(getPath()+".args");
        if (args != null) {
            return args.getValues(false).values().toArray();
        }
        else return new Object[0];
    }
    String getPath() {
        return "line_"+line+".column_"+column;
    }

}
