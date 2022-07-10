package net.capbear.announcement;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public final class Announcement extends JavaPlugin implements Listener {
    static ArrayList<Player> sendList;
    ArrayList<String> announcementList;
    int i = 0;

    @Override
    public void onEnable() {
        // Plugin startup logic
        FileConfiguration config = this.getConfig(); // create config var

        String[] defaultAnnouncements = { "§3Run /help for help", "§3Use /suicide if you're stuck and need an easy escape" }; // create default list of announcements
        ArrayList<String> msgs = new ArrayList<String>(Arrays.asList(defaultAnnouncements)); // convert to list
        config.addDefault("announcements", msgs); // define default announcementlist
        config.addDefault("ticks-per-announcement", 4200); // define default wait period

        config.options().setHeader(Collections.singletonList("Use '§' not '&' when defining colors and whatnot"));

        config.options().copyDefaults(true);
        saveConfig(); // create default config

        this.getCommand("announcement").setExecutor(new CommandAnnouncement()); // add reload command
        this.getCommand("toggleannouncements").setExecutor(new CommandToggleAnnouncements()); // add reload command

        sendList = new ArrayList<Player>(); // init sendList
        announcementList = (ArrayList<String>) config.getStringList("announcements");
        getServer().getPluginManager().registerEvents(this, this); // register plugin
        getLogger().info("Announcement enabled"); // confirm plugin activity

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (i >= announcementList.size()) { i = 0; }
                for (Player player : sendList) {
                    player.sendMessage(announcementList.get(i));
                }
                i++;
            }
        }, config.getInt("ticks-per-announcement"), config.getInt("ticks-per-announcement"));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        sendList.add(event.getPlayer());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
