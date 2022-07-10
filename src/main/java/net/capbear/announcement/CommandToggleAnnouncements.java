package net.capbear.announcement;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class CommandToggleAnnouncements implements CommandExecutor {
    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Announcement");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Announcement.sendList.contains((Player) sender)) {
            Announcement.sendList.remove((Player) sender);
            sender.sendMessage("§3§l[Announcer] §r§3No longer receiving announcements");
        } else {
            Announcement.sendList.add((Player) sender);
            sender.sendMessage("§3§l[Announcer] §r§3Receiving announcements");
        }
        return true;
    }
}
