package me.jrl1004.java.mgcore.arenas.regions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class CuboidRegion extends AbstractRegion {

	private World world;
	private Vector pointOne;
	private Vector pointTwo;
	private Vector minimumVector;
	private Vector maximumVector;

	public CuboidRegion(World world, Vector point1, Vector point2) {
		this.world = world;
		this.pointOne = point1;
		this.pointTwo = point2;
		updateArea();
	}

	public CuboidRegion() {
		this.world = null;
		this.pointOne = this.pointTwo = this.minimumVector = this.maximumVector = null;
	}

	public CuboidRegion setPointOne(Vector point1) {
		this.pointOne = point1;
		updateArea();
		return this;
	}

	public CuboidRegion setPointTwo(Vector point2) {
		this.pointTwo = point2;
		updateArea();
		return this;
	}

	public CuboidRegion setWorld(World world) {
		this.world = world;
		return this;
	}

	private void updateArea() {
		if (pointOne == null) return;
		if (pointTwo == null) return;
		this.minimumVector = new Vector(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()), Math.min(pointOne.getZ(), pointTwo.getZ()));
		this.maximumVector = new Vector(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()), Math.max(pointOne.getZ(), pointTwo.getZ()));
	}

	public boolean pointIsInRegion(Location location) {
		if (world == null) return false;
		if (minimumVector == null) return false;
		if (maximumVector == null) return false;
		if (!pointIsInWorld(location)) return false;
		if (!pointIsWithinYBounds(location)) return false;
		if (!pointIsWithinXBounds(location)) return false;
		if (!pointIsWithinZBounds(location)) return false;
		return true;
	}

	public boolean pointIsInWorld(Location location) {
		if (world == null) return false;
		return location.getWorld().equals(world);
	}

	public boolean pointIsWithinXBounds(Location location) {
		if (!pointIsInWorld(location)) return false;
		if (minimumVector == null) return false;
		if (maximumVector == null) return false;
		if (location.getX() < minimumVector.getX()) return false;
		return location.getX() <= maximumVector.getX();
	}

	public boolean pointIsWithinYBounds(Location location) {
		if (!pointIsInWorld(location)) return false;
		if (minimumVector == null) return false;
		if (maximumVector == null) return false;
		if (location.getY() < minimumVector.getY()) return false;
		return location.getY() <= maximumVector.getY();
	}

	public boolean pointIsWithinZBounds(Location location) {
		if (!pointIsInWorld(location)) return false;
		if (minimumVector == null) return false;
		if (maximumVector == null) return false;
		if (location.getZ() < minimumVector.getZ()) return false;
		return location.getZ() <= maximumVector.getZ();
	}

	public Vector getMinimumPoints() {
		return minimumVector.clone();
	}

	public Vector getMaximumPoints() {
		return maximumVector.clone();
	}

	@Override
	public CuboidRegion clone() {
		return new CuboidRegion(world, minimumVector, maximumVector);
	}
}
