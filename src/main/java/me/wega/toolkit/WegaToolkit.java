package me.wega.toolkit;

import me.wega.toolkit.config.impl.ConfigValue;
import me.wega.toolkit.utils.InitializeStatic;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

public final class WegaToolkit extends JavaPlugin {
    public static WegaToolkit instance;
    public static BukkitAudiences adventure;

    @Override
    public void onLoad() {
        instance = this;
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();
        adventure = BukkitAudiences.create(this);
        InitializeStatic.initializeAll(ConfigValue.class);
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }
}