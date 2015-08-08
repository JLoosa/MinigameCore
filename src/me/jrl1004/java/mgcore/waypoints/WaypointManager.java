package me.jrl1004.java.mgcore.waypoints;

public class WaypointManager {

	private static WaypointManager instance;

	private WaypointManager() {
	}

	public static WaypointManager getInstance() {
		if (instance == null) instance = new WaypointManager();
		return instance;
	}

	public Waypoints getWaypoints(WaypointOrder type) {
		return new Waypoints(type);
	}

}
