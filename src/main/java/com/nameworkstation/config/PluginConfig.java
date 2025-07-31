package com.nameworkstation.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PluginConfig {
    private final JavaPlugin plugin;
    private static FileConfiguration config;

    public PluginConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public static void load(JavaPlugin plugin) {
        PluginConfig configInstance = new PluginConfig(plugin);
        configInstance.loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
    }

    public boolean isXpRequired() {
        return config.getBoolean("require-xp", true);
    }

    public boolean isDyeRequired() {
        return config.getBoolean("require-dye-for-color", true);
    }

    public int getXpCost() {
        return config.getInt("xp-cost.rename", 1);
    }

    public List<String> getAllowedColors() {
        return config.getStringList("settings.allowed-colors");
    }

    public List<String> getAllowedFormats() {
        return config.getStringList("settings.allowed-formats");
    }

    public List<String> getEnabledFlagPresets() {
        return config.getStringList("settings.enabled-flag-presets");
    }

    public boolean hasPermissionRestrictedColors() {
        return config.getBoolean("settings.restrict-colors-by-permission", false);
    }

    public boolean hasPermissionRestrictedFlags() {
        return config.getBoolean("settings.restrict-flags-by-permission", false);
    }
}