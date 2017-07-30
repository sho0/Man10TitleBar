package red.man10.man10titlebar;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.font.NumericShaper;

public final class Man10TitleBar extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    String prefix = "§e§l[§d§lMan10TitleBar§e§l]§f§l";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("mtitle")){
            if(args.length == 0){
                sender.sendMessage("§e==========" + prefix + "§e==========");
                sender.sendMessage("");
                sender.sendMessage("§d/mtitle <main> | <sub> | <time>");
                sender.sendMessage("");
                sender.sendMessage("§e===================================");
                sender.sendMessage("§d§lCreated By Sho0");
                return false;
            }
            if(!sender.hasPermission("man10.title")){
                sender.sendMessage(prefix + "あなたには権限がありません");
                return false;
            }
            boolean skip = false;
            int time = 50;
            String main = "";
            String mode = "main";
            String sub = "";
            for(int i = 0;i < args.length;i++){
                if(mode.equals("main")) {
                    if(args[i].equals("|") && !skip){
                        mode = "sub";
                        skip = true;
                    }
                    main = main + " " + args[i].replaceAll("&","§").replace("|","");
                }
                if(mode.equals("sub")){
                    if(args[i].equals("|") && !skip){
                        mode = "time";
                        skip = true;
                    }
                    skip = false;
                    sub = sub + " " + args[i].replaceAll("&","§").replace("|","");
                }
                if(mode.equals("time")){
                    if(!skip) {
                        try {
                            time = Integer.parseInt(args[i]) * 20;
                        } catch (NumberFormatException e) {
                        }
                    }
                    skip = false;
                }
            }
            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN,1,1);
                player.sendTitle(main,sub,10, time,10);
            }
        }
        return false;
    }
}
