package com.nightshadow.sunrisecreative.creative.world;

import com.nightshadow.sunrisecreative.creative.world.exception.WorldNotFoundException;
import com.nightshadow.sunrisecreative.creative.world.generator.Generator;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

@ApiStatus.Experimental
public class Manager implements WorldManagerInterface{

    /**
     * Создаёт мир
     * @param name название мира
     * @param generator генератор мира
     * @param player игрок
     */
    @Override
    public void createWorld(String name, Generator generator, Player player) {
    }

    /**
     * Удаляет мир
     * @param ID ID мира
     */
    @Override
    public void deleteWorld(int ID) {
        try {
            getWorldByID(ID).delete();
        }
        catch (NullPointerException ex) {
            throw new WorldNotFoundException(ID);
        }


    }

    /**
     * Удаляет мир
     * @param world мир для удаления
     */
    @Override
    public void deleteWorld(World world) {

    }

    /**
     * Отгружает мир
     * @param world мир для отгрузки
     */
    @Override
    public void unloadWorld(World world) {

    }

    /**
     * Загружает мир
     * @param world мир для загрузки
     */
    @Override
    public void loadWorld(World world) {

    }

    /**
     * Инициализирует миры
     * @param directory директория с мирами(не с данными миров!)
     */
    @Override
    public void initializeWorlds(File directory) {

    }

    /**
     * Получает имена существующих миров
     * @return имена миров
     */
    @Override
    public String[] getWorldNames() {
        return new String[0];
    }

    /**
     * Получает мир в котором находится игрок
     * @param p игрок
     * @return Мир, в котором находится игрок (Или spawn, если игрок в мире кодинга или на спавне)
     */
    @Override
    public World getWorldWhere(Player p) {
        return null;
    }

    /**
     * Получает мир в котором находится игрок
     * @param p игрок
     * @return Мир, в котором(или в кодинге которого) находится игрок
     */
    @Override
    public World getWorldWhereIncludingDevWorld(Player p) {
        return null;
    }

    /**
     * Получает мир по локации
     * @param loc локация в мире
     * @return мир, в котором находится данная локация
     */
    @Override
    public World getWorldByLocation(Location loc) {
        return null;
    }

    /**
     * Получает миры, созданные игроком
     * @param p игрок
     * @return Миры, созданные игроком p
     */
    @Override
    public List<World> getWorldsByPlayer(Player p) {
        return List.of();
    }

    /**
     * Получает лимит миров игрока(по умолчанию 3)
     * @param p игрок
     * @return Число миров, которое может создать игрок
     */
    @Override
    public int getPlayerWorldLimit(Player p) {
        return 0;
    }

    /**
     * Устанавливает лимит миров игрока
     * @param p Игрок для установки лимитов
     * @param limit новый лимит миров
     */
    @Override
    public void setPlayerWorldLimit(Player p, int limit) {
    }

    /**
     * Получает миры по идентификатору
     * @param ID идентификатор мира
     * @return мир с существующим идентификатором
     */
    @Override
    public @Nullable World getWorldByID(int ID) {
        return null;
    }

    /**
     * Определяет, находится ли игрок в мире разработки или нет
     * @param p игрок для проверки
     * @return true, если игрок в мире разработки, иначе false
     */
    @Override
    public boolean inDevelopmentWorld(Player p) {
        return false;
    }
}
