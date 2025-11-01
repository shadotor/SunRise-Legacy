package com.nightshadow.sunrisecreative.indev.api.database;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Experimental
public interface Database {
    /**
     * Получает значение поля в базе данных
     * @param field идентификатор поля
     */
    Object getField(String field);

    /**
     * Получает сериализуемое значение
     * @param field идентификатор поля
     */
    Serializable getSerializable(String field);

    /**
     * Устанавливает значение в поле базы данных
     * @param field поле
     * @param value значение
     */
    void setField(String field, Serializable value);
    /**
     * Устанавливает значение в поле базы данных
     * @param field поле
     * @param value значение
     */
    void setField(String field, Object value);
}
