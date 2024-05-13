package me.wega.toolkit.gui.pane;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.wega.toolkit.config.impl.ConfigValue;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class PageNavPane extends StaticPane {
    private final Gui gui;
    private final PaginatedPane itemsPane;
    private final GuiItem goBackItem;
    private final GuiItem nextPageItem = new GuiItem(ConfigValue.GUI.Items.Navigation.NEXT_PAGE, this::nextPageAction);
    private final Consumer<InventoryClickEvent> goBackAction;
    private final GuiItem previousPageItem = new GuiItem(ConfigValue.GUI.Items.Navigation.PREVIOUS_PAGE, this::previousPageAction);

    /**
     * @implNote The paginatedPane needs to be filled with items before creating this pane.
     */
    public PageNavPane(@NotNull Gui gui, @NotNull PaginatedPane paginatedPane, int x, int y, int length, int height) {
        this(gui, paginatedPane, x, y, length, height, null);
    }

    public PageNavPane(@NotNull Gui gui, @NotNull PaginatedPane paginatedPane, int x, int y, int length, int height, @Nullable Consumer<InventoryClickEvent> goBackAction) {
        super(x, y, length, height);
        this.gui = gui;
        this.itemsPane = paginatedPane;
        this.goBackAction = goBackAction;

        this.goBackItem = new GuiItem((goBackAction != null)
                ? ConfigValue.GUI.Items.Navigation.GO_BACK
                : ConfigValue.GUI.Items.Navigation.CLOSE,
                this::goBackAction
        );

        this.initialize();
    }

    private void initialize() {
        this.addItem(previousPageItem, 3, 0);
        this.addItem(goBackItem, 4, 0);
        this.addItem(nextPageItem, 5, 0);
        this.updateNavigation();
    }

    private void nextPageAction(InventoryClickEvent event) {
        event.setCancelled(true);
        itemsPane.setPage(itemsPane.getPage() + 1);
        this.updateNavigation();
        gui.update();
    }

    private void previousPageAction(InventoryClickEvent event) {
        event.setCancelled(true);
        itemsPane.setPage(itemsPane.getPage() - 1);
        this.updateNavigation();
        gui.update();
    }

    private void goBackAction(InventoryClickEvent event) {
        event.setCancelled(true);
        if (goBackAction != null)
            goBackAction.accept(event);
        else
            event.getWhoClicked().closeInventory();
    }

    private void updateNavigation() {
        int page = itemsPane.getPage();
        int totalPages = itemsPane.getPages();
        if (page == 0)
            previousPageItem.setVisible(false);
        else {
            previousPageItem.getItem().setAmount(page);
            previousPageItem.setVisible(true);
        }
        if (page == totalPages - 1)
            nextPageItem.setVisible(false);
        else {
            nextPageItem.setVisible(true);
            nextPageItem.getItem().setAmount(page + 2);
        }
    }
}
