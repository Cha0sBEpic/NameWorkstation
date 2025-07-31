package com.nameworkstation.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class ColorTabPaletteHandler {
    public static final String COLOR_TAB_TITLE = ChatColor.RESET + "Select Color";

    private static final Map<Material, ChatColor> FLAG_COLORS = new LinkedHashMap<>();
    private final Map<UUID, ChatColor> selectedColors = new java.util.HashMap<>();

    static {
        FLAG_COLORS.put(Material.RED_DYE, ChatColor.RED);
        FLAG_COLORS.put(Material.ORANGE_DYE, ChatColor.GOLD);
        FLAG_COLORS.put(Material.YELLOW_DYE, ChatColor.YELLOW);
        FLAG_COLORS.put(Material.LIME_DYE, ChatColor.GREEN);
        FLAG_COLORS.put(Material.BLUE_DYE, ChatColor.BLUE);
        FLAG_COLORS.put(Material.PURPLE_DYE, ChatColor.DARK_PURPLE);
        FLAG_COLORS.put(Material.BLACK_DYE, ChatColor.BLACK);
        FLAG_COLORS.put(Material.WHITE_DYE, ChatColor.WHITE);
    }

    public Inventory createColorTabInventory() {
        Inventory inv = Bukkit.createInventory(null, 27, COLOR_TAB_TITLE);

        int slot = 10;
        for (Material mat : FLAG_COLORS.keySet()) {
            if (slot > 16) break; // Only 7 slots in the center

            ItemStack colorItem = new ItemStack(mat);
            ItemMeta meta = colorItem.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(FLAG_COLORS.get(mat) + "Select this color");
                colorItem.setItemMeta(meta);
            }
            inv.setItem(slot++, colorItem);
        }

        // Add close button
        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeButton.getItemMeta();
        if (closeMeta != null) {
            closeMeta.setDisplayName(ChatColor.RED + "Close");
            closeButton.setItemMeta(closeMeta);
        }
        inv.setItem(22, closeButton);

        return inv;
    }

    public void openColorTab(Player player) {
        Inventory inv = createColorTabInventory();
        player.openInventory(inv);
    }

    public ChatColor handleColorSelection(Player player, Material dyeType) {
        ChatColor color = FLAG_COLORS.get(dyeType);
        if (color != null) {
            selectedColors.put(player.getUniqueId(), color);
            return color;
        }
        return null;
    }

    public ChatColor getSelectedColor(Player player) {
        return selectedColors.getOrDefault(player.getUniqueId(), ChatColor.WHITE);
    }
}