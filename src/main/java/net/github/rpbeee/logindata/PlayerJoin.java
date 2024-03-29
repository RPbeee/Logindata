package net.github.rpbeee.logindata;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoin implements Listener {
    @EventHandler
    public void pjoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Plugin plugin = player.getServer().getPluginManager().getPlugin("Logindata");
        CustomYML cyml = new CustomYML(plugin, "/playerdata/"+player.getUniqueId()+".yml");
        cyml.getConfig();
        FileConfiguration lyml = cyml.getConfig();
        int count = lyml.getInt("count");
        lyml.set("count", count+1);
        cyml.saveConfig();
        player.getServer().getLogger().info(player.getName()+" のログインを確認しました");
    }
}
