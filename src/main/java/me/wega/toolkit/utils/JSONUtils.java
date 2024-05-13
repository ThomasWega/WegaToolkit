package me.wega.toolkit.utils;

import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

/**
 * Handles the conversion from JSON to other Types
 */
@UtilityClass
public class JSONUtils {

    /**
     * All data will be preserved (colors, events, ...)
     *
     * @param json Json to convert
     * @return Converted JSON to Component
     */
    public static Component toComponent(@NotNull JsonObject json) {
        return GsonComponentSerializer.gson().deserialize(json.toString());
    }
}
