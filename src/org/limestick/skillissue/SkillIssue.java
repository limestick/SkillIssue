package org.limestick.skillissue;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.limestick.skillissue.command.CommandDefinition;
import org.limestick.skillissue.command.DifficultyCommandDefinition;
import org.limestick.skillissue.listener.EntityDamageListener;

public class SkillIssue extends JavaPlugin {
	
	public static String name = "SkillIssue";
	public static Difficulty diff = new Difficulty();
	
	public static Logger logger = Bukkit.getServer().getLogger();
	public static Configuration config;

	@Override
	public void onEnable() {
		config = getConfiguration();
		
		if(config.getKeys().isEmpty()) initConfig(config);
		
		initDiff(config.getString("difficulty", "normal"));
	    
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new EntityDamageListener(), this);
		
		logger.info("["+ name +"] Enabled.");
	}
	
	@Override
	public void onDisable() {
		logger.info("["+ name +"] Disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			String cmdName = cmd.getName();
			
			CommandDefinition cd = null;
			
			switch(cmdName) {
				case "difficulty":
					cd = new DifficultyCommandDefinition();
					break;
			}
					
			if(cmd.testPermission(player)) {
				return cd.onRun(player, args);
			}
		}
		
		return true;
	}
	
	public static void initConfig(Configuration config) {
		config.setProperty("difficulty", diff.name);
		config.setProperty("custom.damageMultiplier", diff.damageMultiplier);
	    config.save();
	}
	
	public static String initDiff(String n) {
		switch(n) {
			case "peaceful":
				diff.name = "peaceful";
				diff.damageMultiplier = 0;
				break;
			case "easy":
				diff.name = "easy";
				diff.damageMultiplier = 0.5;
				break;
			case "normal":
				break;
			case "hard":
				diff.name = "hard";
				diff.damageMultiplier = 1.5;
				break;
			case "custom":
				diff.name = "custom";
				diff.damageMultiplier = config.getDouble("custom.damageMultiplier", 1);
				break;
			default:
				logger.info("["+ name +"] Invalid difficulty name. Defaulting to Normal.");
				config.setProperty("difficulty", "normal");
				config.save();
				break;
		}
		
		return diff.name;
	}

}
