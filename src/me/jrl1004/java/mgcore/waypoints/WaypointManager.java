package me.jrl1004.java.mgcore.waypoints;

public class WaypointManager {

	private static WaypointManager instance;
	
	private WaypointManager() {}
	
	public static enum WaypointType {
		ORDERED, UNORDERED;
	}
	
	public static WaypointManager getInstance() {
		if(instance == null) instance  = new WaypointManager();
		return instance;
	}
	
}
