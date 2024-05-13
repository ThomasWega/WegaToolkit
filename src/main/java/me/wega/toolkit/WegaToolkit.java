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
        adventure = BukkitAudiences.create(this);
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                .shouldHookPaperReload(true)
        );
    }

    @Override
    public void onEnable() {
        InitializeStatic.initializeAll(ConfigValue.class);
        CommandAPI.onEnable();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }
}