package me.jrl1004.java.mgcore.players;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerBackup {

	private Player player;
	private Location location;
	private float fallDistance;
	private int totalExp;
	private int foodLevel;
	private GameMode gameMode;
	private ItemStack[] inventory;
	private ItemStack[] armor;
	private Scoreboard scoreboard;

	public PlayerBackup(Player player) {
		this.player = player;
		writeVars();
	}

	private void writeVars() {
		this.location = player.getLocation();
		this.fallDistance = player.getFallDistance();
		this.totalExp = player.getTotalExperience();
		this.foodLevel = player.getFoodLevel();
		this.gameMode = player.getGameMode();
		this.inventory = player.getInventory().getContents();
		this.armor = player.getInventory().getArmorContents();
		this.scoreboard = player.getScoreboard();
	}

	public void restorePlayer() {
		player.teleport(location);
		player.setFallDistance(fallDistance);
		player.setTotalExperience(totalExp);
		player.setFoodLevel(foodLevel);
		player.setGameMode(gameMode);
		player.getInventory().setArmorContents(armor);
		player.getInventory().setContents(inventory);
		player.setScoreboard(scoreboard);
	}

	public void wipeData() {
		player = null;
		location = null;
		inventory = null;
		armor = null;
		scoreboard = null;
	}
}
