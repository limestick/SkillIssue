package org.limestick.skillissue.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.limestick.skillissue.Main;

public class EntityDamageListener implements Listener {
	
	Main plugin;
	
	public EntityDamageListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(!(event instanceof EntityDamageByEntityEvent)) return;
		
		EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
		
		if(!(e.getEntity() instanceof Player) || e.getDamager() instanceof Player) return;
		
		int d = (int) Math.floor(e.getDamage() * plugin.diff.damageMultiplier);
		
		if (d < 1 && e.getDamage() >= 1) d = 1;
		
		e.setDamage(d);
	}
	
}
