package com.nightshadow.sunrisecreative.creative.world;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.sendConsole;

/* Необходимо переделать механизм получения переменных */
public class WorldVariableData {
    private final World world;

    /**
     * @param world мир с переменными
     */
    public WorldVariableData(World world) {
        this.world = world;
    }

    /**
     * Очищает кэш (данные локальных переменных)
     * Кэш автоматически чистится при перезагрузке сервера.
     */
    public void clearCash() {
        boolean cleared = WorldConfig.getFile(this.world, "localData").delete();
        if (cleared) sendConsole("Cleared cash of world " + world.getID() );
    }

    /**
     * Сохраняет переменную в кэш
     * @param key ключ сохранения(название переменной)
     * @param value значение переменной
     */
    public void saveVariable(String key, Object value) {
        WorldConfig.setKey(this.world, "localData", key, value);
    }

    /**
     * Получает переменную
     * @param key ключ сохранения(название переменной)
     * @return значение переменной
     */
    public Object getVariable(String key) {
        return WorldConfig.getKey(this.world, "localData", key);
    }
    public void clearData() {
        boolean cleared = WorldConfig.getFile(this.world, "savedData").delete();
        if (cleared) sendConsole("Cleared data of world " + world.getID());
    }
    public void saveData(String key, Object data) {
        WorldConfig.setKey(this.world, "savedData", key, data);
    }
    public Object getData(String key) {
        return WorldConfig.getKey(this.world, "savedData", key);
    }
}
