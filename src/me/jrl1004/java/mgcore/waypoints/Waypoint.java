package me.jrl1004.java.mgcore.waypoints;

import me.jrl1004.java.mgcore.arenas.regions.AbstractRegion;
import me.jrl1004.java.mgcore.arenas.regions.CuboidRegion;
import me.jrl1004.java.mgcore.arenas.regions.SphericalRegion;

import org.bukkit.entity.Player;

public class Waypoint {

	private AbstractRegion region;
	private WaypointType type;
	private int id;

	protected Waypoint(WaypointType type) {
		this.type = type;
		setRegionToDefault();
	}

	protected Waypoint(WaypointType type, int id) {
		this(type);
		this.id = id;
	}

	public void setType(WaypointType type) {
		boolean reset = this.type != type;
		this.type = type;
		if (reset)
			setRegionToDefault();
	}

	public void setRegionToDefault() {
		if (type == WaypointType.SPHERICAL) {
			region = new SphericalRegion();
		}
		else
			region = new CuboidRegion();
	}

	public boolean isAtWaypoint(Player player) {
		return region.pointIsInRegion(player.getLocation());
	}

	public boolean hasPassedWaypoit(int currentWaypoint) {
		return currentWaypoint >= id;
	}

	public WaypointType getType() {
		return this.type;
	}

	public AbstractRegion getArea() {
		return region.clone();
	}
}
