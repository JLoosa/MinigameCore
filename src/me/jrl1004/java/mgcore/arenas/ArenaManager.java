package me.jrl1004.java.mgcore.arenas;

import java.util.HashSet;
import java.util.Set;

import me.jrl1004.java.mgcore.MinigamesAPI;

import org.bukkit.scheduler.BukkitRunnable;

public class ArenaManager {
	private static ArenaManager instance;
	private Set<AbstractArena> arenas;
	Set<AbstractArena> removals;
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
		}
		arenas.clear();
	}
}
