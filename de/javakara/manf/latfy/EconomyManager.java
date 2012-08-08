/**************************************************************************
 * This file is part of MCbb.                                              
 * MCbb is free software: you can redistribute it and/or modify            
 * it under the terms of the GNU General Public License as published by    
 * the Free Software Foundation, either version 3 of the License, or       
 * (at your option) any later version.                                     
 * MCbb is distributed in the hope that it will be useful,                 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           
 * GNU General Public License for more details.                            
 * You should have received a copy of the GNU General Public License       
 * along with MCbb.  If not, see <http://www.gnu.org/licenses/>.           
 *************************************************************************/

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
