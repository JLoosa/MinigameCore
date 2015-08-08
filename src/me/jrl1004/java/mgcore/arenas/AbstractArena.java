package me.jrl1004.java.mgcore.arenas;

import me.jrl1004.java.mgcore.arenas.regions.CuboidRegion;

public abstract class AbstractArena {

	private CuboidRegion area;

	public AbstractArena() {
	}
	
	public void setGameArea(CuboidRegion area) {
		this.area = area;
	}
	
	public CuboidRegion getArea() {
		return area.clone();
	}

	/**
	 * Called once every server tick
	 */
	public void onTick() {
	}

	/**
	 * Called once every time 20 ticks pass (each second)
	 */
	public void onSecond() {
	}

	/**
	 * Called once every 60 seconds or 1200 ticks (each minute)
	 */
	public void onMinute() {
	}

	/**
	 * Called once every time the game is started
	 */
	public void onGameStart() {
	}

	/**
	 * Called when the game ends
	 */
	public void onEnd() {
	}

	/**
	 * Called when the arena is told to disable itself
	 */
	public void onDisable() {
	}

}
