package org.limestick.skillissue.command;

import org.bukkit.entity.Player;

public interface CommandDefinition {
	
	boolean onRun(Player player, String[] args);
	
}
