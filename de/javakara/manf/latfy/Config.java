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
