package net.github.rpbeee.logindata;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class SaveScheduler extends BukkitRunnable {

    FileConfiguration config;
    Plugin plugin;
    File configFile;

    public SaveScheduler(FileConfiguration conf, Plugin pl, File confF) {
        config=conf;
        plugin=pl;
        configFile=confF;
    }

    @Override
    public void run() {
        if(config == null) {
            return;
        }
        try {
            config.save(configFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
        }
    }
}
