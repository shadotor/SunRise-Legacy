package com.nightshadow.sunrisecreative.creative.coding.handler;

public enum CodingBlockCategories {
    ACTION("block-category.action","&e"),
    EVENT("block-category.event","&b"),
    CONDITION("block-category.condition","&a"),
    OTHER("block-category.other","&c");
    final String translationKey;
    final String color;
    CodingBlockCategories(String translationKey, String color) {
        this.translationKey = translationKey;
        this.color = color;
    }
}
