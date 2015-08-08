package me.jrl1004.java.mgcore.waypoints;

import java.util.HashSet;
import java.util.Set;

public class Waypoints {

	private WaypointOrder type;
	private Set<Waypoint> waypointList;

	protected Waypoints(WaypointOrder type) {
		this.type = type;
		this.waypointList = new HashSet<Waypoint>();
	}

	public WaypointOrder getOrder() {
		return this.type;
	}

	/**
	 * Convenience method. Same as the other but sets the id to 0 for you
	 * 
	 * @param type
	 * @return
	 */
	public Waypoint getNewWaypoint(WaypointType type) {
		return getNewWaypoint(type, 0);
	}

	public Waypoint getNewWaypoint(WaypointType type, int id) {
		return new Waypoint(type, id);
	}

	public boolean addWaypoint(Waypoint waypoint) {
		return waypointList.add(waypoint);
	}

	public Set<Waypoint> getAllWaypoints() {
		return waypointList;
	}
}
