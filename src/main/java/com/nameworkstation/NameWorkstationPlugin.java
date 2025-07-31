package com.nameworkstation;

import com.nameworkstation.command.NameWorkstationCommand;
import com.nameworkstation.config.PluginConfig;
import com.nameworkstation.editor.EditModeManager;
import com.nameworkstation.gui.AnvilGUIHandler;
import com.nameworkstation.gui.GUIController;
import org.bukkit.plugin.java.JavaPlugin;

public final class NameWorkstationPlugin extends JavaPlugin {

    private static NameWorkstationPlugin instance;

    private GUIController guiController;
    private PluginConfig pluginConfig;

    @Override
    public void onEnable() {
        instance = this;

        // Save default config if not exists
        saveDefaultConfig();

        // Load plugin config
        pluginConfig = new PluginConfig(this);

        // Initialize GUI controller
        guiController = new GUIController(this);

        // Initialize edit mode manager
        EditModeManager.init();

        // Register command executor
        if (getCommand("nameworkstation") != null) {
            getCommand("nameworkstation").setExecutor(new NameWorkstationCommand(this));
        }

        // Register event listeners
        getServer().getPluginManager().registerEvents(new AnvilGUIHandler(), this);
    }

    @Override
    public void onDisable() {
        // Clear edit modes on disable
        EditModeManager.clearAll();
    }

    public static NameWorkstationPlugin getInstance() {
        return instance;
    }

    public GUIController getGuiController() {
        return guiController;
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }
}