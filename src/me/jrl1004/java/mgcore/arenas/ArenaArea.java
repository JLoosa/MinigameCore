package me.jrl1004.java.mgcore.arenas;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ArenaArea {
	private World world;
	private Vector pointOne;
	private Vector pointTwo;
	private Vector minimumVector;
	private Vector maximumVector;

	public ArenaArea(World world, Vector point1, Vector point2) {
		this.world = world;
		this.pointOne = point1;
		this.pointTwo = point2;
		updateArea();
	}

	public ArenaArea setPointOne(Vector point1) {
		this.pointOne = point1;
		updateArea();
		return this;
	}

	public ArenaArea setPointTwo(Vector point2) {
		this.pointTwo = point2;
		updateArea();
		return this;
	}

	public ArenaArea setWorld(World world) {
		this.world = world;
		return this;
	}

	private void updateArea() {
		if (pointOne == null) return;
		if (pointTwo == null) return;
		this.minimumVector = new Vector(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()), Math.min(pointOne.getZ(), pointTwo.getZ()));
		this.maximumVector = new Vector(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()), Math.max(pointOne.getZ(), pointTwo.getZ()));
	}

	public boolean pointIsInArea(Location location) {
		if (!pointIsInWorld(location)) return false;
		if (!pointIsWithinYBounds(location)) return false;
		if (!pointIsWithinXBounds(location)) return false;
		if (!pointIsWithinZBounds(location)) return false;
		return true;
	}

	public boolean pointIsInWorld(Location location) {
		return location.getWorld().equals(world);
	}

	public boolean pointIsWithinXBounds(Location location) {
		if (location.getX() < minimumVector.getX()) return false;
		return location.getX() <= maximumVector.getX();
	}

	public boolean pointIsWithinYBounds(Location location) {
		if (location.getY() < minimumVector.getY()) return false;
		return location.getY() <= maximumVector.getY();
	}

	public boolean pointIsWithinZBounds(Location location) {
		if (location.getZ() < minimumVector.getZ()) return false;
		return location.getZ() <= maximumVector.getZ();
	}

	public Vector getMinimumPoints() {
		return minimumVector.clone();
	}

	public Vector getMaximumPoints() {
		return maximumVector.clone();
	}
}
