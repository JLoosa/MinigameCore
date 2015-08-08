package me.jrl1004.java.mgcore.arenas.regions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class SphericalRegion extends AbstractRegion {

	private World world;
	private Vector center;
	private double radius;

	public SphericalRegion(World world, Vector center, double radius) {
		this.world = world;
		this.center = center;
		this.radius = radius;
	}

	public SphericalRegion(Location center, double radius) {
		this(center.getWorld(), center.toVector(), radius);
	}

	public SphericalRegion(Location center) {
		this(center, 0);
	}

	public SphericalRegion(World world, Vector center) {
		this(world, center, 0);
	}

	public SphericalRegion() {
		this.world = null;
		this.center = null;
		this.radius = 0;
	}

	@Override
	public boolean pointIsInRegion(Location location) {
		if (world == null) return false;
		if (center == null) return false;
		if (!location.getWorld().equals(world)) return false;
		if (radius <= 0) return false;
		return location.toVector().distance(center) <= radius;
	}

	@Override
	public SphericalRegion clone() {
		return new SphericalRegion(world, center, radius);
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setCenter(Location location) {
		this.world = location.getWorld();
		this.center = location.toVector();
	}

	public void setCenter(World world, Vector center) {
		this.world = world;
		this.center = center;
	}

	public void setCenter(Vector center) {
		this.center = center;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
