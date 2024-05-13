package me.wega.toolkit.config.impl;

import me.wega.toolkit.builder.ConfigItemBuilder;
import me.wega.toolkit.config.ConfigHandler;
import me.wega.toolkit.config.ConfigProperty;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class ConfigValue {

    public static class GUI {

        public static class Items {
            public static final YamlConfiguration ITEMS_FILE;

            static {
                ConfigHandler itemsFile = new ConfigHandler(me.wega.toolkit.WegaToolkit.instance, "gui" + File.separator, "items.yml");
                itemsFile.saveDefaultConfig();
                ITEMS_FILE = itemsFile.getConfig();
            }


            public static class Navigation {
                public static final ItemStack NEXT_PAGE = new ConfigItemBuilder(new ConfigProperty<ConfigurationSection>(ITEMS_FILE, "navigation.next-page").getValue())
                        .build();

                public static final ItemStack PREVIOUS_PAGE = new ConfigItemBuilder(new ConfigProperty<ConfigurationSection>(ITEMS_FILE, "navigation.previous-page").getValue())
                        .builder()
                        .hideFlags()
                        .build();
                public static final ItemStack GO_BACK = new ConfigItemBuilder(new ConfigProperty<ConfigurationSection>(ITEMS_FILE, "navigation.go-back").getValue())
                        .builder()
                        .hideFlags()
                        .build();
                public static final ItemStack CLOSE = new ConfigItemBuilder(new ConfigProperty<ConfigurationSection>(ITEMS_FILE, "navigation.close").getValue())
                        .builder()
                        .hideFlags()
                        .build();
            }
        }
    }

    public static class Tasks {
        public static final YamlConfiguration TASKS_FILE;

        static {
            ConfigHandler itemsFile = new ConfigHandler(me.wega.toolkit.WegaToolkit.instance, "", "tasks.yml");
            itemsFile.saveDefaultConfig();
            TASKS_FILE = itemsFile.getConfig();
        }

        public static class SEC_COUNTDOWN {
            public static final ConfigProperty<String> MSG = new ConfigProperty<>(TASKS_FILE, "sec-countdown.message");
        }
    }

}
