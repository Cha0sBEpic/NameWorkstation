package com.nameworkstation.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

    /**
     * Checks if the item stack is valid and has item meta.
     */
    public static boolean hasMeta(ItemStack item) {
        return item != null && item.hasItemMeta();
    }

    /**
     * Safely sets the display name of an item.
     */
    public static void setDisplayName(ItemStack item, String name) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    /**
     * Gets the display name or returns empty string if none.
     */
    public static String getDisplayName(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return "";
        ItemMeta meta = item.getItemMeta();
        return meta.hasDisplayName() ? meta.getDisplayName() : "";
    }
}