package me.jrl1004.java.mgcore.arenas.regions;

import org.bukkit.Location;
import org.bukkit.World;

public abstract class AbstractRegion {

	public abstract boolean pointIsInRegion(Location location);

	public abstract AbstractRegion clone();

	public abstract World getWorld();

}
