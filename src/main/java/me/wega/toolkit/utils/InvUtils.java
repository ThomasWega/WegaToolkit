package me.wega.toolkit.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@UtilityClass
public class InvUtils {

    /**
     * Checks whether items can fit into the given inventory.
     *
     * @param inv   Inventory to run check for
     * @param items Items that need to fit
     * @return <strong>true</strong> whether all items can fit, otherwise <strong>false</strong>
     * @author <a href="https://github.com/ThomasWega">Tomáš Weglarz</a>
     */
    public static boolean canFit(@NotNull Inventory inv, @NotNull ItemStack... items) {
        final ItemStack[] contents = inv.getStorageContents().clone();
        for (ItemStack itemStack : items) {
            boolean itemInserted = false;

            for (int i = 0; i < contents.length; i++) {
                ItemStack current = contents[i];

                if (current == null) {
                    contents[i] = itemStack;
                    itemInserted = true;
                    break; // Empty slot, item fits
                }

                if (current.isSimilar(itemStack) && current.getAmount() + itemStack.getAmount() <= current.getMaxStackSize()) {
                    ItemStack currentClone = current.clone();
                    currentClone.setAmount(current.getAmount() + itemStack.getAmount());
                    contents[i] = currentClone;
                    itemInserted = true;
                    break; // Item can stack in the existing slot
                }
            }

            if (!itemInserted) {
                return false; // Item doesn't fit in any slot
            }
        }

        return true;
    }

    /**
     * Checks whether items can fit into the given inventory.
     *
     * @param inv   Inventory to run check for
     * @param items Items that need to fit
     * @return <strong>true</strong> whether all items can fit, otherwise <strong>false</strong>
     * @author <a href="https://github.com/ThomasWega">Tomáš Weglarz</a>
     */
    public static boolean canFit(Inventory inv, Collection<ItemStack> items) {
        return canFit(inv, items.toArray(ItemStack[]::new));
    }

    /**
     * Counts the number of empty slots in the given inventory.
     *
     * @param inv the inventory to check
     * @return the number of empty slots in the inventory
     */
    public static int getEmptySlots(Inventory inv) {
        return (int) Arrays.stream(inv.getContents())
                .filter(item -> item == null || item.getType() == Material.AIR)
                .count();
    }

    /**
     * Adds the items to the inventory on the first available slot (merging supported).
     *
     * @param currCont the current inventory contents
     * @param addStacks the items to add
     * @return the new inventory contents
     */
    public static @Nullable ItemStack @NotNull[] newContents(@Nullable ItemStack @NotNull[] currCont, @Nullable ItemStack @NotNull... addStacks) {
        Inventory inventory = Bukkit.createInventory(null, currCont.length);
        inventory.setContents(currCont.clone());
        inventory.addItem(Arrays.stream(addStacks)
                .filter(Objects::nonNull)
                .map(ItemStack::clone)
                .toArray(ItemStack[]::new)
        );
        return inventory.getContents();
    }
}
