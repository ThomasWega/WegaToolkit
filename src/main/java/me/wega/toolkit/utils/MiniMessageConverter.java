package me.wega.toolkit.utils;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

/**
 * Mapping of Minecraft color codes to MiniMessage tags
 */
@UtilityClass
@SuppressWarnings("UnnecessaryUnicodeEscape")
public class MiniMessageConverter {

    public static Component getFromLegacy(@NotNull String legacyString, @NotNull TagResolver... resolvers) {
        return MiniMessage.miniMessage().deserialize(
                MiniMessage.miniMessage().serialize(
                                LegacyComponentSerializer.legacyAmpersand().deserialize(legacyString))
                        .replaceAll("(?<!\\\\)\\\\(?!\\\\)", ""),
                resolvers
        );
    }
}
