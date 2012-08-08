package de.javakara.manf.latfy;

import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class EconomyManager {
	private static Economy economy;
	
	public static boolean initialise() {
		RegisteredServiceProvider<Economy> provider = ServiceManager.getEconomyProvider();
		if (provider != null) {
			economy = provider.getProvider();
		}
		return (economy != null);
	}

	public static boolean isInitialised() {
		return (economy != null);
	}

	public static void modifyPlayer(String playerName, double amount) {
		if (amount == 0.0) {
			return;
		}

		if (amount > 0.0) {
			economy.depositPlayer(playerName, amount);
		} else {
			economy.withdrawPlayer(playerName, amount);
		}
	}

	public static boolean hasMoney(String playerName,int price) {
		return economy.getBalance(playerName) >= price;
	}

	public static void removePlayer(String name, double price) {
		economy.withdrawPlayer(name, price);
	}
}
