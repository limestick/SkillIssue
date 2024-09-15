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

public class Main extends JavaPlugin {
	
	public String name = "SkillIssue";
	public Difficulty diff = new Difficulty();
	
	public Logger logger = Bukkit.getServer().getLogger();
	public Configuration config;

	@Override
	public void onEnable() {
		config = getConfiguration();
		
		if(config.getKeys().isEmpty()) initConfig(config);
		
		initDiff(diff, config.getString("difficulty", "normal"));
	    
		PluginManager pm = Bukkit.getServer().getPluginManager();
		EntityDamageListener listener = new EntityDamageListener(this);
		pm.registerEvents(listener, this);
		
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
					cd = new DifficultyCommandDefinition(this);
					break;
			}
					
			if(cmd.testPermission(player)) {
				return cd.onRun(player, args);
			}
		}
		
		return true;
	}
	
	public void initConfig(Configuration config) {
		config.setProperty("difficulty", "normal");
		config.setProperty("custom.damageMultiplier", 1);
	    config.save();
	}
	
	public String initDiff(Difficulty d, String n) {
		String r;
		
		switch(n) {
			case "peaceful":
				d.damageMultiplier = 0;
				r = "peaceful";
				break;
			case "easy":
				d.damageMultiplier = 0.5;
				r = "easy";
				break;
			case "normal":
				d.damageMultiplier = 1;
				r = "normal";
				break;
			case "hard":
				d.damageMultiplier = 1.5;
				r = "hard";
				break;
			case "custom":
				d.damageMultiplier = config.getDouble("custom.damageMultiplier", 1);
				r = "custom";
				break;
			default:
				logger.info("["+ name +"] Invalid difficulty name. Defaulting to Normal.");
				config.setProperty("difficulty", "normal");
				config.save();
				r = "normal";
				break;
		}
		
		return r;
	}

}
