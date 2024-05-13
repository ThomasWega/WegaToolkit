package me.wega.toolkit.gui.impl;

import me.wega.toolkit.utils.ComponentUtils;
import me.wega.toolkit.utils.InvUtils;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public abstract class StorageGUI implements Listener {
    private final Inventory inv;

    public StorageGUI(@Nullable InventoryHolder holder, int size, @NotNull Component title) {
        this.inv = Bukkit.createInventory(holder, size, ComponentUtils.toColoredLegacy(title));
        Bukkit.getPluginManager().registerEvents(this, me.wega.toolkit.WegaToolkit.instance);
    }

    public StorageGUI(int size, @NotNull Component title) {
        this(null, size, title);
    }

    public void show(@NotNull HumanEntity ent) {
        ent.openInventory(inv);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory eventInv = e.getClickedInventory();
        PlayerInventory playerInv = player.getInventory();
        InventoryAction action = e.getAction();

        if (eventInv == null || (!eventInv.equals(inv) && !eventInv.equals(playerInv))) return;

        int slot = e.getSlot();

        ItemStack cursor = e.getCursor();
        ItemStack current = e.getCurrentItem();

        ItemStack[] items = inv.getContents().clone();
        if (eventInv.equals(inv)) {
            switch (action) {
                case MOVE_TO_OTHER_INVENTORY, DROP_ALL_SLOT -> items[slot] = null;
                case PICKUP_HALF -> {
                    ItemStack item = items[slot].clone();
                    item.setAmount(item.getAmount() / 2);
                    items[slot] = item;
                }
                case PICKUP_ONE, DROP_ONE_SLOT -> {
                    ItemStack item = items[slot].clone();
                    item.setAmount(item.getAmount() - 1);
                    items[slot] = item;
                }
                case PLACE_ONE -> {
                    if (items[slot] == null) {
                        ItemStack cursorOne = cursor.clone();
                        cursorOne.setAmount(1);
                        items[slot] = cursorOne;
                    } else {
                        ItemStack item = items[slot].clone();
                        item.setAmount(item.getAmount() + 1);
                        items[slot] = item;
                    }
                }
                case HOTBAR_SWAP -> {
                    ItemStack hotbarItem = playerInv.getItem(e.getHotbarButton());
                    items[slot] = hotbarItem;
                }
                case NOTHING, UNKNOWN, CLONE_STACK -> {
                }
                default -> items[slot] = cursor;
            }
        } else if (eventInv.equals(playerInv)) {
            if (action == InventoryAction.MOVE_TO_OTHER_INVENTORY)
                items = InvUtils.newContents(items, current);
        }

        this.onContentChange(items);
    }

    public abstract void onContentChange(ItemStack[] newItems);
}
