package net.github.rpbeee.logindata;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Date;

public final class Logindata extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ログインデータPL開始");
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        File pdir = new File(this.getDataFolder()+"/playerdata/");
        if (!pdir.exists()) {
            pdir.mkdir();
        }
        Bukkit.getPluginCommand("logindata").setExecutor(new CmdExecuter());
    }

    @Override
    public void onDisable() {
        getLogger().info("ログインデータPL終了");
    }
}
