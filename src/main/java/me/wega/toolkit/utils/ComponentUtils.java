package me.wega.toolkit.utils;

import com.google.gson.JsonElement;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Handles the conversion from Components to other Types
 */
@UtilityClass
public final class ComponentUtils {

    /**
     * Converts the Component to String.
     * Will preserve only color codes
     *
     * @param component Component to convert
     * @return String from Component with only unformatted color codes
     */
    public static @NotNull String toLegacy(@NotNull Component component) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(component);
    }

    /**
     * Converts the Component to String.
     * Will apply colors by replacing ampersand with section char
     *
     * @param component Component to convert
     * @return String from Component with only formatted color codes
     */
    public static @NotNull String toColoredLegacy(@NotNull Component component) {
        return LegacyComponentSerializer.legacySection().serialize(component);
    }

    /**
     * Converts the list of Components to list of Strings.
     * Will apply colors by replacing ampersand with section char
     *
     * @param component List of Components to convert
     * @return List of Strings from Components with only formatted color codes
     */
    public static @NotNull List<String> toColoredLegacy(@NotNull Collection<Component> component) {
        return component.stream()
                .map(ComponentUtils::toColoredLegacy)
                .toList();
    }


        /**
         * Convert component to JSON
         * Will preserve everything (colors, events, ...)
         *
         * @param component Component to convert
         * @return JSONElement from Component
         */
    public static @NotNull JsonElement toJson(@NotNull Component component) {
        return GsonComponentSerializer.gson().serializeToTree(component);
    }

    /**
     * Convert component to String of JSON
     * Will preserve everything (colors, events, ...)
     *
     * @param component Component to convert
     * @return String of JSON from Component
     */
    public static @NotNull String toJsonString(@NotNull Component component) {
        return GsonComponentSerializer.gson().serialize(component);
    }

    /**
     * Convert list of components to JSON string
     * @param components List of components to convert
     * @return JSON string from list of components
     */
    public static @NotNull String toJsonString(@NotNull List<Component> components) {
        return GsonComponentSerializer.gson().serialize(Component.join(JoinConfiguration.newlines(), components));
    }

    /**
     * Convert JSON string to Component
     * @param jsonString JSON string to convert
     * @return Component from JSON string
     */
    public static @NotNull Component fromJsonString(@NotNull String jsonString) {
        return GsonComponentSerializer.gson().deserialize(jsonString);
    }

    /**
     * Convert Component to MiniMessage string
     * @param component Component to convert
     * @return MiniMessage string from Component
     */
    public static @NotNull String toMiniMessage(@NotNull Component component) {
        return MiniMessage.miniMessage().serialize(component);
    }

    /**
     * Split the component by newlines
     * @param component Component to split
     * @return List of components split by newlines
     */
    public static @NotNull List<Component> splitNewLines(@NotNull Component component) {
        return Arrays.stream(toMiniMessage(component).split("<newline>"))
                .map(ColorUtils::color)
                .toList();
    }
}