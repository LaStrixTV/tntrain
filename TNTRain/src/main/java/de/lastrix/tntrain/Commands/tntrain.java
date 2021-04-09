package de.lastrix.tntrain.Commands;

import de.lastrix.tntrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.TNT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.lang.management.PlatformLoggingMXBean;

public class tntrain implements CommandExecutor {
    int taskID;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(p.isOp()){
            if(args.length == 0){
                p.sendMessage("§cUse: /tntrain <start, stop> or /tntrain seconds <seconds>");
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("start")){
                    taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
                            }
                        }
                    }, 10*20, Main.getPlugin().getConfig().getInt("seconds") * 20);
                    Bukkit.broadcastMessage("§aTNTRain started");
                }else if(args[0].equalsIgnoreCase("stop")){
                    if(Bukkit.getScheduler().isCurrentlyRunning(taskID)){
                        Bukkit.getScheduler().cancelTask(taskID);
                        Bukkit.broadcastMessage("§cTNTRain stopped");
                    }else{
                        p.sendMessage("§cTNTRain is currently not active. Use /tntrain start");
                    }
                }
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("seconds")){
                    int seconds = Integer.parseInt(args[1]);
                    Main.plugin.getConfig().set("seconds", seconds);
                    Main.plugin.saveConfig();
                    p.sendMessage("§aYou changed the Seconds to: "+seconds);
                }else p.sendMessage("§cWrong Syntax!");
            }else p.sendMessage("§cWrong Syntax!");
        }else p.sendMessage("§cYou dont have Permissions to execute this Command.");

        return false;
    }

    public static void spawnTNT(Player p){
        p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
    }
}
