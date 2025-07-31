package com.nameworkstation.util;

import org.bukkit.ChatColor;

public class ColorUtils {

    /**
     * Converts a hex color string (e.g., "#FF00FF") to a Minecraft §x§R§R§G§G§B§B color code.
     * Returns empty string if invalid.
     */
    public static String hexToMinecraftColorCode(String hex) {
        if (hex == null || !hex.startsWith("#") || hex.length() != 7) {
            return "";
        }
        StringBuilder sb = new StringBuilder("\u00a7x");
        for (int i = 1; i < 7; i++) {
            sb.append('\u00a7').append(hex.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Translates alternate color codes '&' to ChatColor codes '§'.
     */
    public static String translateAlternateColorCodes(String text) {
        if (text == null) return null;
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}