package me.jrl1004.java.mgcore.scoreboards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ModularScoreboard {

	private Scoreboard scoreboard;
	private List<String> scoreboardText;
	private String title;

	protected ModularScoreboard() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		scoreboardText = new ArrayList<String>();
		title = null;
	}

	public void setTitle(String title) {
		this.title = title.substring(0, Math.min(title.length(), 16));
	}

	public List<String> getScoreboardText() {
		return scoreboardText;
	}

	public void setScoreboardText(List<String> text) {
		this.scoreboardText = text;
	}

	public void setScoreboardText(String... text) {
		setScoreboardText(new ArrayList<String>(Arrays.asList(text)));
	}

	public void applyText() {
		List<String> list = getScoreboardText();
		Collections.reverse(list);
		assignToScoreboard(list);
	}
	
	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}
	
	public void recreateScoreboard() {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		applyText();
	}

	private void assignToScoreboard(List<String> list) {
		clearDisplays();
		cleanObjectives();
		clearScores();
		if (list.isEmpty()) return;
		scoreboard.registerNewObjective("SIDEBAR", "dummy");
		scoreboard.getObjective("SIDEBAR").setDisplayName(title != null ? title : "----------------");
		scoreboard.getObjective("SIDEBAR").setDisplaySlot(DisplaySlot.SIDEBAR);
		int i = 0;
		for (String s : list)
			scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(s).setScore(i++);
	}

	private void cleanObjectives() {
		Set<Objective> objs = scoreboard.getObjectives();
		if (objs.isEmpty()) return;
		for (Objective i : objs) {
			i.unregister();
		}
		objs.clear();
	}

	private void clearScores() {
		Set<String> entries = scoreboard.getEntries();
		if (entries.isEmpty()) return;
		for (String scoreKey : entries) {
			scoreboard.resetScores(scoreKey);
		}
	}

	private void clearDisplays() {
		for (DisplaySlot slot : DisplaySlot.values())
			scoreboard.clearSlot(slot);
	}
}
