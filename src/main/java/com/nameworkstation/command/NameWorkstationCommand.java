package com.nameworkstation.command;

import com.nameworkstation.NameWorkstationPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NameWorkstationCommand implements CommandExecutor {

    private final NameWorkstationPlugin plugin;

    public NameWorkstationCommand(NameWorkstationPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (!player.hasPermission("nameworkstation.use")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this.");
            return true;
        }

        plugin.getGuiController().openMainGUI(player);
        return true;
    }
}