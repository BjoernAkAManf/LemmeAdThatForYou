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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

public final class Config {
	private static FileConfiguration config;
	private static File configFile;
	
	public static boolean initialise(FileConfiguration config,File dataFolder) throws IOException{
		Config.config = config;
		Config.configFile = new File(dataFolder + File.separator + "config.yml");
		if (!config.isSet("latfy.version")){
			config.set("price",10);
			config.set("message", "[Information] <text> (<player>)");
			config.set("cooldown",10);
			config.set("lang.cooldown", "Cooldown active");
			config.set("lang.money", "Not enough Money");
			config.set("lang.perm", "&3Permission Denied");
			config.set("lang.noarguments", "Format: /ad [Text]");
			save();
		}
		return true;		
	}
	
	public static int getInt(String node){
		return config.getInt(node);
	}
	
	public static String getString(String node){
		if(!config.isSet(node)){
			config.set(node, "asdf");
			try {
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config.getString(node);
	}

	public static boolean getBoolean(String node) {
		return config.getBoolean(node);
	}
	
	public static List<String> getStringList(String node){
		return config.getStringList(node);
	}
			
	public static void load() throws FileNotFoundException, IOException, InvalidConfigurationException {
		config.load(configFile);
	}
	
	public static void save() throws IOException {
		config.set("latfy.version", "1");
		config.save(configFile);
	}
}
