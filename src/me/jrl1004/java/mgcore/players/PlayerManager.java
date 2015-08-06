package me.jrl1004.java.mgcore.players;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	private static PlayerManager instance;
	
	private PlayerManager() {}
	
	public static PlayerManager getInstance() {
		if(instance == null) instance = new PlayerManager();
		return instance;
	}
	
	public PlayerBackup getNewPlayerBackup(Player player) {
		PlayerBackup backup = new PlayerBackup(player);
		return backup;
	}
}
