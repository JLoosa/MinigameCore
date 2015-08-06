package me.jrl1004.java.mgcore.world;

public class WorldManager {

	private static WorldManager instance;

	private WorldManager() {

	}

	public static WorldManager getInstance() {
		if (instance == null) instance = new WorldManager();
		return instance;
	}
	
	
}
