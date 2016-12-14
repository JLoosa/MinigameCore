package me.jrl1004.java.mgcore.world;

import java.util.List;
import java.util.Stack;

import org.bukkit.block.BlockState;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;

public class WorldBackup {

	private Stack<BlockState> states;
	private boolean extensive;

	protected WorldBackup() {
		states = new Stack<BlockState>();
		extensive = false;
	}

	/**
	 * Ask the WorldBackup to store a change in the world. Will always support
	 * the BlockPlaceEvent and BlockBreakEvent.
	 * 
	 * @param event The BlockEvent that you want the WorldBackup to try and
	 *        store for you. Set extensiveLogging to true if you would like to
	 *        store more than the BlockBreak and BlockPlace events
	 * @param extensiveLogging Should we store this block event extensively?
	 *        (Set to true to store BlockBurn, BlockFade, BlockPhysics,
	 *        BlockExplode, and other events
	 */

	public void saveChange(BlockEvent event, boolean extensiveLogging) {
		if (event instanceof BlockPlaceEvent)
			storeBlockPlaceEvent((BlockPlaceEvent) event);
		if (event instanceof BlockBreakEvent)
			storeBlockBreakEvent((BlockBreakEvent) event);
		if (!extensiveLogging) return; // Only extensive logging is done below this point
		if (event instanceof BlockBurnEvent)
			storeBlockBurnEvent((BlockBurnEvent) event);
		if (event instanceof BlockFadeEvent)
			storeBlockFadeEvent((BlockFadeEvent) event);
		if (event instanceof BlockPhysicsEvent)
			storeBlockPhysicsEvent((BlockPhysicsEvent) event);
		if (event instanceof BlockFromToEvent)
			storeBlockFromToEvent((BlockFromToEvent) event);
		if (event instanceof BlockFormEvent)
			storeBlockFormEvent((BlockFormEvent) event);
		if (event instanceof BlockGrowEvent)
			storeBlockGrowEvent((BlockGrowEvent) event);
		if (event instanceof BlockMultiPlaceEvent)
			storeBlockMultiPlaceEvent((BlockMultiPlaceEvent) event);
		if (event instanceof BlockSpreadEvent)
			storeBlockSpreadEvent((BlockSpreadEvent) event);
		if (event instanceof LeavesDecayEvent)
			storeLeavesDecayEvent((LeavesDecayEvent) event);
		if (event instanceof EntityBlockFormEvent)
			storeEntityBlockFormEvent((EntityBlockFormEvent) event);
	}

	/**
	 * Return how many block changes this WorldBackup is currently keeping track
	 * of
	 */
	public int getStoredChangeCount() {
		return states.size();
	}

	/**
	 * Fast access storing method. Auto-sets extensiveLogging to the value in
	 * setExtensiveLogging() [default false]
	 * 
	 * @param event The event you wish to store
	 */
	public void saveChange(BlockEvent event) {
		saveChange(event, extensive);
	}

	/**
	 * Set if this WorldBackup should extensively store BlockEvents when the
	 * fast-access saveChange() method is called
	 * 
	 * @param value
	 */
	public void setExtensiveLogging(boolean value) {
		this.extensive = value;
	}

	/**
	 * Roll back recent block changes and clear them from the backup.
	 * 
	 * @param amount How many changes you would like to roll back. us
	 *        rollbackAllChanges() to completely restore all logged changes.
	 */
	public void rollbackChanges(int amount) {
		int count = 0;
		while (count <= amount) {
			rollbackLastChange();
			++count;
		}
	}

	/**
	 * Roll back the most recent block change
	 */
	@SuppressWarnings("deprecation")
	public void rollbackLastChange() {
		if (states.isEmpty()) return;
		BlockState state = states.pop();
		state.getBlock().setTypeIdAndData(state.getTypeId(), state.getRawData(), false); // Use false so we don't immediately re-log BlockPhysics events
	}

	/**
	 * Roll back all currently saved block changes in reverse order
	 */
	public void rollbackAllChanges() {
		rollbackChanges(states.size());
	}

	// Private storing methods

	private void storeBlockPlaceEvent(BlockPlaceEvent event) {
		states.push(event.getBlockReplacedState());
	}

	private void storeBlockBreakEvent(BlockBreakEvent event) {
		states.push(event.getBlock().getState());
	}

	private void storeBlockBurnEvent(BlockBurnEvent event) {
		states.push(event.getBlock().getState());
	}

	private void storeBlockFadeEvent(BlockFadeEvent event) {
		states.push(event.getNewState());
	}

	private void storeBlockPhysicsEvent(BlockPhysicsEvent event) {
		states.push(event.getBlock().getState());
	}

	private void storeBlockFromToEvent(BlockFromToEvent event) {
		states.push(event.getBlock().getState());
	}

	private void storeBlockFormEvent(BlockFormEvent event) {
		states.push(event.getNewState());
	}

	private void storeBlockGrowEvent(BlockGrowEvent event) {
		states.push(event.getNewState());
	}

	private void storeBlockMultiPlaceEvent(BlockMultiPlaceEvent event) {
		event.getBlockReplacedState();
		List<BlockState> changes = event.getReplacedBlockStates();
		if (!changes.isEmpty()) {
			for (BlockState state : changes) {
				states.push(state);
			}
		}
	}

	private void storeBlockSpreadEvent(BlockSpreadEvent event) {
		states.push(event.getNewState());
	}

	private void storeLeavesDecayEvent(LeavesDecayEvent event) {
		states.push(event.getBlock().getState());
	}

	private void storeEntityBlockFormEvent(EntityBlockFormEvent event) {
		states.push(event.getNewState());
	}
}
