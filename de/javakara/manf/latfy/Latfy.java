package de.javakara.manf.latfy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

public class Latfy extends JavaPlugin {
	private LatfyCommands LatfyCMD;
	@Override
	public void onEnable() {
		if (getServer().getPluginManager().getPlugin("Vault") != null) {
			if (EconomyManager.initialise()) {
				System.out.println("Economy Support Activated!");
			} else {
				getPluginLoader().disablePlugin(this);
			}
		}
		try {
			Config.initialise(getConfig(), getDataFolder());
			Config.load();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		registerCommands();
	}

	private void registerCommands() {
		LatfyCMD = new LatfyCommands();
		getCommand("ad").setExecutor(LatfyCMD);	
	}
}
