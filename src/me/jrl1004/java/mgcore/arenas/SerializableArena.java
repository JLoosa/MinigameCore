package me.jrl1004.java.mgcore.arenas;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import me.jrl1004.java.mgcore.MinigamesAPI;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SerializableArena extends AbstractArena implements ConfigurationSerializable
{
	private boolean canSave = true;
	private String pluginName;
	private File saveFolder;
	private UUID arenaID;

	/**
	 * This is a subclass of AbstractArena that has a required serialize() and
	 * deserialize function These functions allow the user to use the built-in
	 * save() method to store all the arena's information to a file Both the
	 * serialize() and deserialize() methods must be coded in the class
	 * extending this one and should not return null If serialize() is null, the
	 * save() method will fail if deserialize() is null, loading the class from
	 * file will not work
	 */
	public SerializableArena(JavaPlugin plugin) {
		if (plugin == null || plugin instanceof MinigamesAPI) {
			canSave = false;
			saveFolder = null;
			pluginName = null;
			return;
		}
		pluginName = plugin.getDescription().getName();
	}

	/**
	 * Return if this plugin is able to be saved to file DO NOT OVERRIDE THIS
	 * METHOD, IT IS USED BY THE ARENA MANAGER
	 * 
	 * @return is this class is able to be saved to file
	 */
	public boolean canSave() {
		return this.canSave;
	}

	public File getSaveFolder() {
		return saveFolder;
	}

	public String getPluginName() {
		return pluginName;
	}

	@Override
	public abstract Map<String, Object> serialize();

	public abstract SerializableArena deserialize(Map<String, Object> map);

	/**
	 * Return the identity of this arena. It is recommended that you override
	 * this method to return something sensible
	 * 
	 * @return arena-specific UUID as string without override
	 */
	public String getIdentifier() {
		if (arenaID == null) arenaID = UUID.randomUUID();
		return arenaID.toString().replaceAll("-", "");
	}

	public void save() throws Exception {
		if (saveFolder == null) return;
		File saveFile = new File(saveFolder, getIdentifier() + ",yml");
		if (saveFile.exists()) saveFile.delete();
		saveFile.createNewFile();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(saveFile);
		Map<String, Object> saveData = serialize();
		for (String s : saveData.keySet())
			config.set(s, saveData.get(s));
		config.save(saveFile);
	}

}
