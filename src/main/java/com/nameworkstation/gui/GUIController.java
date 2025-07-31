package com.nameworkstation.gui;

import com.nameworkstation.NameWorkstationPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIController {
    public static final int TAB_BUTTON_SLOT = 45;
    private static final String MAIN_TITLE = ChatColor.DARK_PURPLE + "Name Workstation";
    private static final String COLOR_TAB_TITLE = ChatColor.RESET + "Select Color";

    private final NameWorkstationPlugin plugin;
    private final ColorTabPaletteHandler colorTabHandler;

    public GUIController(NameWorkstationPlugin plugin) {
        this.plugin = plugin;
        this.colorTabHandler = new ColorTabPaletteHandler();
    }

    public void openMainGUI(Player player) {
        // Create the custom anvil GUI
        Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL, MAIN_TITLE);

        // Add the color tab button below the hotbar
        anvil.setItem(TAB_BUTTON_SLOT, createColorTabButton());

        player.openInventory(anvil);
    }

    public void openColorTab(Player player) {
        colorTabHandler.openColorTab(player);
    }

    public void updatePreviewWithColor(Player player, ChatColor color) {
        Inventory openInventory = player.getOpenInventory().getTopInventory();
        if (openInventory.getType() == InventoryType.ANVIL) {
            ItemStack output = openInventory.getItem(2);
            if (output != null) {
                ItemMeta meta = output.getItemMeta();
                if (meta != null) {
                    // Apply selected color to preview
                    String currentName = meta.hasDisplayName() ? meta.getDisplayName() : "";
                    meta.setDisplayName(color + ChatColor.stripColor(currentName));
                    output.setItemMeta(meta);
                }
            }
        }
    }

    private ItemStack createColorTabButton() {
        ItemStack tabButton = new ItemStack(Material.PAINTING);
        ItemMeta meta = tabButton.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.YELLOW + "Color Tab");
            // Set custom model data for resource pack texture
            meta.setCustomModelData(1001);
            tabButton.setItemMeta(meta);
        }
        return tabButton;
    }

    public ColorTabPaletteHandler getColorTabHandler() {
        return colorTabHandler;
    }
}