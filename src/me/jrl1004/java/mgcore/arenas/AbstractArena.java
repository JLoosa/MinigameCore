package me.jrl1004.java.mgcore.arenas;

public abstract class AbstractArena {

	private ArenaArea area;

	public AbstractArena() {
	}
	
	public void setGameArea(ArenaArea area) {
		this.area = area;
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
