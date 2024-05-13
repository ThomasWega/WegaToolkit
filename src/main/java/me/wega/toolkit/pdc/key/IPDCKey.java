package me.wega.toolkit.pdc.key;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IPDCKey {
    /***
     * @implSpec Create a static field and then just public getter for it!
     */
    @NotNull JavaPlugin getInstance();

    @NotNull String getKey();

    @NotNull PersistentDataType getType();

    default String getString(@NotNull PersistentDataHolder holder) {
        return get(holder).toString();
    }

    default Integer getInt(@NotNull PersistentDataHolder holder) {
        return (Integer) get(holder);
    }

    default Long getLong(@NotNull PersistentDataHolder holder) {
        return (Long) get(holder);
    }

    default Double getDouble(@NotNull PersistentDataHolder holder) {
        return (Double) get(holder);
    }

    default Boolean getBoolean(@NotNull PersistentDataHolder holder) {
        return (Boolean) get(holder);
    }

    default void set(@NotNull PersistentDataHolder holder, Object value) {
        holder.getPersistentDataContainer().set(getNamespace(), getType(), value);
    }

    default void remove(@NotNull PersistentDataHolder holder) {
        holder.getPersistentDataContainer().remove(getNamespace());
    }

    default @Nullable Object get(@NotNull PersistentDataHolder holder) {
        if (holder instanceof ItemStack && !((ItemStack) holder).hasItemMeta()) return null;
        return holder.getPersistentDataContainer().get(getNamespace(), getType());
    }

    default boolean has(@NotNull PersistentDataHolder holder) {
        if (holder instanceof ItemStack && !((ItemStack) holder).hasItemMeta()) return false;
        return holder.getPersistentDataContainer().has(getNamespace(), getType());
    }

    default @NotNull NamespacedKey getNamespace() {
        return new NamespacedKey(getInstance(), getKey());
    }
}
