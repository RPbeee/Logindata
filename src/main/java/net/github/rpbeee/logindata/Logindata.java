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
    // プレイヤー宛メッセージのプレフィックス
    String premsg = ChatColor.GREEN+"[Logindata] "+ChatColor.WHITE;

    @Override
    public void onEnable() {
        getLogger().info("ログインデータPL開始");
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        File pdir = new File(this.getDataFolder()+"/playerdata/");
        if (!pdir.exists()) {
            pdir.mkdir();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("ログインデータPL終了");
    }

    public void argserror(CommandSender sender, boolean ecode) {
        if(ecode){
            sender.sendMessage(premsg+"引数が多いか足りません！");
        } else {
            sender.sendMessage(premsg+"引数が間違っています！");
        }
        sender.sendMessage(premsg+"/logindata firstjoin [プレイヤー名]");
        sender.sendMessage(premsg+"/logindata lastjoin [プレイヤー名]");
        sender.sendMessage(premsg+"/logindata count [プレイヤー名]");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (args.length != 2) {
            // too less or much args.
            argserror(sender, true);
            return true;
        }

        Player player = Bukkit.getServer().getPlayer(args[1]);
        if (player==null || !args[1].equals(player.getName()) || !player.hasPlayedBefore()) {
            sender.sendMessage(premsg+"プレイヤー名が間違っています");
            return true;
        }

        CustomYML cyml = new CustomYML(this, "/playerdata/"+player.getUniqueId()+".yml");
        FileConfiguration lyml = cyml.getConfig();

        if(args[0].equalsIgnoreCase("firstjoin")) {
            Date date = new Date(player.getFirstPlayed());
            sender.sendMessage(premsg+args[1]+" の初ログイン日時は: "+date.toLocaleString()+" です");
        } else if(args[0].equalsIgnoreCase("lastjoin")) {
            Date date = new Date(player.getLastPlayed());
            sender.sendMessage(premsg+args[1]+" の最終ログイン日時は: "+date.toLocaleString()+" です");
        } else if(args[0].equalsIgnoreCase("count")) {
            int count = lyml.getInt("count");
            sender.sendMessage(premsg+args[1]+" の累計ログイン回数は: "+count+" 回です");
        } else {
            argserror(sender, false);
        }
        return true;
    }
}
