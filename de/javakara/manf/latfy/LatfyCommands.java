package de.javakara.manf.latfy;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LatfyCommands implements CommandExecutor {
	private HashMap<String, Long> cd = new HashMap<String,Long>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("latfy.use")){
			sender.sendMessage(lang("perm"));
			return true;
		}
		
		if (label.equalsIgnoreCase("ad")) {
			int price = Config.getInt("price");
			System.out.println(price);
			if(args.length >= 1){
				if(EconomyManager.hasMoney(sender.getName(),price)){
					if(!hasCd(sender.getName())){
						broadcast(sender.getName(), args);
						cd.put(sender.getName(), System.currentTimeMillis());
						EconomyManager.removePlayer(sender.getName(), (double) price);
					}else{
						sender.sendMessage(lang("cooldown"));
					}
				}else{
					sender.sendMessage(lang("money"));
				}
			}else{
				sender.sendMessage(lang("noarguments"));
			}
		}
		return false;
	}
	
	private String lang(String string) {
		return ChatColor.translateAlternateColorCodes('&',Config.getString("lang." + string));
	}
	
	private void broadcast(String name, String[] args) {
		StringBuilder sb = new StringBuilder();
		for(String arg:args){
			sb.append(arg.replace("<text>","").replace("<player>", "") + " ");
		}
		for(Player p:Bukkit.getOnlinePlayers()){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&',Config.getString("message").replace("<player>", name).replace("<text>",sb.toString())));
		}
	}
	
	private boolean hasCd(String playerName){
		if(cd.containsKey(playerName)){
			long d = cd.get(playerName);
			if(System.currentTimeMillis() >= ((long) (Config.getInt("cooldown")*1000 + d))){
				cd.remove(playerName);
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
}