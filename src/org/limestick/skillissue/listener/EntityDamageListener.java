package org.limestick.skillissue.listener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.limestick.skillissue.Main;

public class EntityDamageListener implements Listener {
	
	public static Set<DamageCause> causes = new HashSet<DamageCause>(Arrays.asList(
		     new DamageCause[] { DamageCause.ENTITY_ATTACK, DamageCause.PROJECTILE, DamageCause.ENTITY_EXPLOSION } 
	));
	
	Main plugin;
	
	public EntityDamageListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(!causes.contains(e.getCause())) return;
		
		int damage = (int) Math.floor(e.getDamage() * plugin.diff.damageMultiplier);
		
		if (damage < 1 && e.getDamage() >= 1) { damage = 1; }
		
		e.setDamage(damage);
	}
	
}
