package org.limestick.skillissue.listener;

import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.limestick.skillissue.SkillIssue;

public class CreatureSpawnListener implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {	
		Creature c = (Creature) event.getEntity();
		
		int h = (int) Math.floor(c.getHealth() * SkillIssue.diff.mobHealthMultiplier);
		
		// java.lang.IllegalArgumentException: Health must be between 0 and 200
		// 0 health also de-spawns any mob immediately
		if(h < 1) h = 1;
		else if (h > 200) h = 200;
		
		c.setHealth(h);
	}
	
}
