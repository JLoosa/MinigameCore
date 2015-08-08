package me.jrl1004.java.mgcore.arenas;

import java.util.HashSet;
import java.util.Set;

import me.jrl1004.java.mgcore.MinigamesAPI;

import org.bukkit.scheduler.BukkitRunnable;

public class ArenaManager {
	private static ArenaManager instance;
	private Set<AbstractArena> arenas;

	private Set<AbstractArena> removals;
	private boolean disableRunnable = false;

	private ArenaManager() {
		arenas = new HashSet<AbstractArena>();
		removals = new HashSet<AbstractArena>();
		startTimings();
	}

	public static ArenaManager getInstance() {
		if (instance == null) instance = new ArenaManager();
		return instance;
	}

	/**
	 * Register your arena with this arena manager. If the arena is
	 * serializable, it will automatically be saved when the manager is unloaded
	 * 
	 * @param arena the arena you would like to register with this manager
	 * @return if you arena was successfully registered
	 */
	public boolean registerArena(AbstractArena arena) {
		return arenas.add(arena);
	}

	public boolean unregisterArena(AbstractArena arena) {
		return arenas.remove(arena);
	}

	public void removeAllArenasOfType(Class<? extends AbstractArena> clazz) {
		if (arenas.isEmpty()) return;
		for (AbstractArena arena : arenas)
			if (arena.getClass().isInstance(clazz))
				removals.add(arena);
	}

	private void startTimings() {
		new BukkitRunnable() {
			private int tick = 0;
			private int second = 0;
			private boolean tickSecond = false;
			private boolean tickMinute = false;

			public void run() {
				if (disableRunnable) {
					cancel();
					return;
				}
				tickSecond = false;
				tickMinute = false;
				++tick;
				if (tick >= 20) {
					tick %= 20;
					++second;
					tickSecond = true;
				}
				if (second >= 60) {
					second %= 60;
					tickMinute = true;
				}
				if (!removals.isEmpty()) {
					arenas.removeAll(removals);
					removals.clear();
				}
				if (arenas.isEmpty()) return;
				for (AbstractArena arena : arenas) {
					arena.onTick();
					if (tickSecond)
						arena.onSecond();
					if (tickMinute)
						arena.onMinute();
				}
			}
		}.runTaskTimer(MinigamesAPI.getMinigames(), 0, 1);
	}

	public void onDisable() {
		disableRunnable = true;
		if (arenas.isEmpty()) return;
		for (AbstractArena arena : arenas) {
			arena.onEnd();
			if (arena instanceof SerializableArena) {
				SerializableArena sa = (SerializableArena) arena;
				if (sa.canSave())
					try {
						sa.save();
					} catch (Exception e) {
						MinigamesAPI.getMinigames().getLogger().warning("Failed to save SerializableArena " + sa.getIdentifier() + " for plugin " + sa.getPluginName());
					}
			}
		}
		arenas.clear();
	}
}
