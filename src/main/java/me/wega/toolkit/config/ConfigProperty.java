package me.wega.toolkit.config;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ConfigProperty<T> {
    private final FileConfiguration config;
    private final @NotNull String path;

    public T getValue() {
        return (T) config.get(path);
    }
}
