package org.limestick.skillissue.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.limestick.skillissue.SkillIssue;

public class EntityDamageListener implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(!(event instanceof EntityDamageByEntityEvent)) return;
		
		EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
		
		if(!(e.getEntity() instanceof Player) || e.getDamager() instanceof Player) return;
		
		int d = (int) Math.floor(e.getDamage() * SkillIssue.diff.damageMultiplier);
		
		if (d < 1 && e.getDamage() >= 1) d = 1;
		
		e.setDamage(d);
	}
	
}
