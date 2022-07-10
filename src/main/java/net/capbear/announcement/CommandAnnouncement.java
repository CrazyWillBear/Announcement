package net.capbear.announcement;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class CommandAnnouncement implements CommandExecutor {
    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Announcement");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage("[Announcement] Reloaded config!");
            return true;
        } else { return false; }
    }
}
