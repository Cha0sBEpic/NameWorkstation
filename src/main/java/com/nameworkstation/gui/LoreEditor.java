package com.nameworkstation.gui;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class LoreEditor {

    public static void setLoreLine(ItemStack item, int lineIndex, String newLine) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new java.util.ArrayList<>();

        while (lore.size() <= lineIndex) {
            lore.add("");
        }

        lore.set(lineIndex, ChatColor.translateAlternateColorCodes('&', newLine));
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static void addLoreLine(ItemStack item, String line) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new java.util.ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', line));
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static void removeLoreLine(ItemStack item, int lineIndex) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) return;

        List<String> lore = meta.getLore();
        if (lineIndex >= 0 && lineIndex < lore.size()) {
            lore.remove(lineIndex);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    public static void clearLore(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        meta.setLore(null);
        item.setItemMeta(meta);
    }

    public static void previewLore(Player player, ItemStack item) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : java.util.Collections.emptyList();

        player.sendMessage(ChatColor.GRAY + "--- Lore Preview ---");
        for (int i = 0; i < lore.size(); i++) {
            player.sendMessage(ChatColor.YELLOW + "[" + i + "]: " + ChatColor.RESET + lore.get(i));
        }
    }
}