package com.nameworkstation.gui;

import com.nameworkstation.NameWorkstationPlugin;
import com.nameworkstation.editor.EditModeManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class AnvilGUIHandler implements Listener {
    private static final String MAIN_TITLE = ChatColor.DARK_PURPLE + "Name Workstation";

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        InventoryView view = event.getView();
        String title = view.getTitle();
        int slot = event.getRawSlot();

        // Handle main anvil GUI interactions
        if (title.equals(MAIN_TITLE)) {
            handleMainGUIInteractions(player, event, view, slot);
        }
        // Handle color tab GUI interactions
        else if (title.equals(ColorTabPaletteHandler.COLOR_TAB_TITLE)) {
            handleColorTabInteractions(player, event, slot);
        }
    }

    private void handleMainGUIInteractions(Player player, InventoryClickEvent event, InventoryView view, int slot) {
            // Check if color tab button was clicked (slot 45)
            if (slot == GUIController.TAB_BUTTON_SLOT) {
                event.setCancelled(true);
                NameWorkstationPlugin.getInstance().getGuiController().openColorTab(player);
                return;
            }

        // Check if clicked in output slot
        if (slot == 2) {
            event.setCancelled(true);
            applyChanges(player, view.getTopInventory());
        }
    }

    private void handleColorTabInteractions(Player player, InventoryClickEvent event, int slot) {
        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        // Check if color selection item was clicked
        ChatColor selectedColor = NameWorkstationPlugin.getInstance()
                .getGuiController()
                .getColorTabHandler()
                .handleColorSelection(player, clicked.getType());

        if (selectedColor != null) {
            // Update preview with selected color
            NameWorkstationPlugin.getInstance()
                    .getGuiController()
                    .updatePreviewWithColor(player, selectedColor);

            // Return to main GUI
            player.closeInventory();
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;

        // Clear editing mode when closing GUI
        EditModeManager.clear(player.getUniqueId());
    }

    private void applyChanges(Player player, Inventory anvil) {
        ItemStack output = anvil.getItem(2);
        if (output == null || output.getType().isAir()) {
            player.sendMessage(ChatColor.RED + "No item to rename!");
            return;
        }

        // Check XP cost
        if (!hasRequiredXP(player)) {
            player.sendMessage(ChatColor.RED + "You need more experience!");
            return;
        }

        // Apply changes and give item to player
        player.getInventory().addItem(output.clone());

        // Clear anvil slots
        anvil.setItem(0, null);
        anvil.setItem(2, null);

        // Consume XP
        consumeXP(player);

        player.sendMessage(ChatColor.GREEN + "Item renamed successfully!");
    }

    private boolean hasRequiredXP(Player player) {
        int requiredXp = NameWorkstationPlugin.getInstance().getPluginConfig().isXpRequired()
                ? NameWorkstationPlugin.getInstance().getPluginConfig().getXpCost()
                : 0;
        return player.getTotalExperience() >= requiredXp;
    }

    private void consumeXP(Player player) {
        int requiredXp = NameWorkstationPlugin.getInstance().getPluginConfig().isXpRequired()
                ? NameWorkstationPlugin.getInstance().getPluginConfig().getXpCost()
                : 0;
        player.giveExp(-requiredXp);
    }
}