package org.limestick.skillissue.command;

import org.bukkit.entity.Player;
import org.limestick.skillissue.SkillIssue;

public class DifficultyCommandDefinition implements CommandDefinition {
	
	@Override
	public boolean onRun(Player player, String[] args) {
		if(args.length == 0) return false;
		
		if(args.length == 1) {
			String d = args[0];
			SkillIssue.config.setProperty("difficulty", d);
			SkillIssue.config.save();
			d = SkillIssue.initDiff(d);
			player.sendMessage("Set difficulty to " + d);
			return true;
		}
		
		if(args[0].equals("set")) {
			if(args.length != 3) return false;
			
			if(SkillIssue.config.getKeys("custom").contains(args[1])) {
				
				try {
					double test = Double.valueOf(args[2]);
					SkillIssue.config.setProperty("custom." + args[1], test);
				}
				catch (Exception e) {
					SkillIssue.config.setProperty("custom." + args[1], args[2]);
				}
				
				SkillIssue.config.save();
				
				player.sendMessage("Set custom modifier " + args[1] + " to " + args[2]);
				
				if(SkillIssue.diff.name.equals("custom")) {
					SkillIssue.initDiff("custom");
					player.sendMessage("Reloaded custom difficulty.");
				}
				
				return true;
			}
			
			return false;
		}
		
		return false;
	}

}
