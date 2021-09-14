package net.github.rpbeee.logindata;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Date;

public class CmdExecuter implements CommandExecutor {

    String premsg = ChatColor.GREEN+"[Logindata] "+ChatColor.WHITE;

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

        Player player = Bukkit.getServer().getPlayerExact(args[1]);
        Plugin plugin = sender.getServer().getPluginManager().getPlugin("Logindata");
        if (player==null) {
            sender.sendMessage(premsg+"プレイヤー名が間違っています");
            return true;
        }

        CustomYML cyml = new CustomYML(plugin, "/playerdata/"+player.getUniqueId()+".yml");
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
