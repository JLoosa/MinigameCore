package me.jrl1004.java.mgcore.world;

public class WorldManager {

	private static WorldManager instance;

	private WorldManager() {

	}

	public static WorldManager getInstance() {
		if (instance == null) instance = new WorldManager();
		return instance;
	}

	/**
	 * Get a new WorldBackup instance for use <b> DOES NOT ACTUALLY CREATE A
	 * BACKUP OF THE WORLD </b>
	 * 
	 * @return a new block restoration class
	 * 
	 */
	public WorldBackup getNewBackup() {
		return new WorldBackup();
	}
}
