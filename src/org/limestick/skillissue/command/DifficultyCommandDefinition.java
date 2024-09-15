package org.limestick.skillissue.command;

import org.bukkit.entity.Player;
import org.limestick.skillissue.Main;

public class DifficultyCommandDefinition implements CommandDefinition {
	
	Main plugin;
	
	public DifficultyCommandDefinition(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onRun(Player player, String[] args) {
		if(args.length == 0) return false;
		
		String d = args[0];
		plugin.config.setProperty("difficulty", d);
		plugin.config.save();
		d = plugin.initDiff(plugin.diff, d);
		player.sendMessage("Set difficulty to " + d);
		return true;
	}

}
