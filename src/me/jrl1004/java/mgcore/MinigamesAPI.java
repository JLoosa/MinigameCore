package me.jrl1004.java.mgcore;

import me.jrl1004.java.mgcore.arenas.ArenaManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MinigamesAPI extends JavaPlugin {

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
	}

	@Override
	public void onEnable() {
		// Call all getInstance() methods to initialize classes
		ArenaManager.getInstance();
		super.onEnable();
	}

	@Override
	public void onDisable() {
		getArenaManager().onDisable();
		super.onDisable();
	}

	public static MinigamesAPI getMinigames() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("MinigamesAPI");
		if (plugin == null || !(plugin instanceof MinigamesAPI) || !plugin.isEnabled()) return null;
		return (MinigamesAPI) plugin;
	}
	
	public ArenaManager getArenaManager() {
		return ArenaManager.getInstance();
	}

}
