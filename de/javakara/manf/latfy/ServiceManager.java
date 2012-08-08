package de.javakara.manf.latfy;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class ServiceManager {
	public static RegisteredServiceProvider<Economy> getEconomyProvider() {
		return Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	}
}
