package org.limestick.skillissue.command;

import org.bukkit.entity.Player;
import org.limestick.skillissue.SkillIssue;

public class DifficultyCommandDefinition implements CommandDefinition {
	
	@Override
	public boolean onRun(Player player, String[] args) {
		if(args.length == 0) return false;
		
		String d = args[0];
		SkillIssue.config.setProperty("difficulty", d);
		SkillIssue.config.save();
		d = SkillIssue.initDiff(d);
		player.sendMessage("Set difficulty to " + d);
		return true;
	}

}
