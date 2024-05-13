package me.wega.toolkit.builder;

import me.wega.toolkit.utils.ColorUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class ConfigItemBuilder {
    private final ConfigurationSection section;
    private @Nullable String material;
    private @Nullable String display;
    private @NotNull List<String> lore;
    private int customModel;
    private @Nullable String base64;

    public ConfigItemBuilder(ConfigurationSection section) {
        this.section = section;
        this.initialize();
    }

    private void initialize() {
        material = section.getString("material");
        display = section.getString("display");
        lore = section.getStringList("lore");
        customModel = section.getInt("customModel");
        base64 = section.getString("base64");
    }

    public ItemBuilder builder() {
        ItemBuilder builder;
        if (base64 != null)
            builder = new ItemBuilder(SkullCreator.itemFromBase64(base64));
        else {
            Objects.requireNonNull(material, "Material is required component of item");
            builder = new ItemBuilder(Material.getMaterial(material));
        }


        if (display != null)
            builder.displayName(ColorUtils.color(display));

        builder.lore(ColorUtils.color(lore));

        if (customModel > 0)
            builder.customModel(customModel);

        return builder;
    }

    public ItemStack build() {
        return builder().build();
    }
}
