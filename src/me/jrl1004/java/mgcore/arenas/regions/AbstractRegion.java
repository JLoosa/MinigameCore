package me.jrl1004.java.mgcore.arenas.regions;

import org.bukkit.Location;

public abstract class AbstractRegion {

	public abstract boolean pointIsInRegion(Location location);

	public abstract AbstractRegion clone();

}
