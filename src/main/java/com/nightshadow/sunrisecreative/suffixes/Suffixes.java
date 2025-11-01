package com.nightshadow.sunrisecreative.suffixes;

import net.luckperms.api.node.types.SuffixNode;
import org.jetbrains.annotations.ApiStatus;


/**
 * Экспериментальное, не войдёт в релиз
 */
@Deprecated(since = "0.2", forRemoval = true)
@ApiStatus.Experimental
public enum Suffixes {
    FIRE("&c🔥"),STARLIGHT("&e⭐ Starlight"),PENCIL("&#c864ff[✎]"),SUNRISE("&#ffffaa ☀ SunRise"),MOON("&#aaaaff🌙");

    Suffixes(String suffix) {
        this.suffix = " " + suffix;
    }

    public String getSuffixText() {
        return suffix;
    }
    public SuffixNode getSuffix() {
        return SuffixNode.builder(this.suffix,10).build();
    }

    private String suffix;
}
