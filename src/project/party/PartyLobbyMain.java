/*
 * Author: 598Johnn897
 * 
 * Date: Jul 24, 2014
 * Package: project.party
 *
 */
package project.party;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import project.party.lib.References;

/**
 * @author 598Johnn897
 * 
 *         Created: Jul 24, 2014 Time: 4:43:55 PM Year: 2014
 * 
 *         By: 598Johnn897
 * 
 *         Project: PartyLobby File: PartyLobby.java Package: project.party
 */
public class PartyLobbyMain extends JavaPlugin {

	private static PartyLobbyMain plugin;

	public static PartyLobbyMain get() {
		return plugin;
	}

	private int start;
	private int end;

	@Override
	public void onLoad() {
		try {
			plugin = this;
			start = (int) System.currentTimeMillis();
		} catch (Exception e) {
			handleCrash(e);
		}
	}

	@Override
	public void onEnable() {
		try {
			end = (int) (System.currentTimeMillis() - start);
			log(" " + References.NAME + " has enabled [" + end + "ms]");
		} catch (Exception e) {
			handleCrash(e);
		}
	}

	@Override
	public void onDisable() {
		try {
			
		} catch (Exception e) {
			handleCrash(e);
		}
	}

	public void handleCrash(Exception e) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		File theDir = new File(plugin.getDataFolder().getAbsolutePath());
		File theOtherDir = new File(plugin.getDataFolder().getAbsolutePath()
				+ References.CRASH_FOLDER_PATH_EXT);
		if (!theDir.exists()) {
			try {
				theDir.mkdir();
				theOtherDir.mkdir();
			} catch (SecurityException se) {
				se.printStackTrace();
			}
		}
		File file = new File(plugin.getDataFolder()
				+ References.CRASH_FOLDER_PATH_EXT, String.format(
				"crash-%d-%02d-%02d %02d.%02d", year, day, month + 1, hour,
				minute)
				+ References.CRASH_FILE_FORMAT);
		if (!file.exists()) {
			try {
				file.createNewFile();
				writeTo(file,
						String.format("crash-%d-%02d-%02d %02d.%02d \n", year,
								day, month + 1, hour, minute) + e.toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		logError(References.NAME
				+ ": An Error Has Occured During Start Up! Crash Exceptions Can Be Found At: "
				+ plugin.getDataFolder().getAbsolutePath());
		Bukkit.getPluginManager().disablePlugin(this);
	}

	public void log(String msg) {
		Bukkit.getLogger().log(Level.INFO, ChatColor.stripColor(msg));
	}

	public void logWarn(String msg) {
		Bukkit.getLogger().log(Level.WARNING, ChatColor.stripColor(msg));
	}

	public void logError(String msg) {
		Bukkit.getLogger().log(Level.SEVERE, ChatColor.stripColor(msg));
	}

	public static HashMap<File, BufferedWriter> writers = new HashMap<File, BufferedWriter>();

	public BufferedWriter getBufferedWriter(File f) {
		try {
			if (writers.containsKey(f)) {
				return writers.get(f);
			} else {
				BufferedWriter returns = new BufferedWriter(new FileWriter(f,
						true));
				writers.put(f, returns);
				return returns;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeTo(File f, String message) {
		try {
			if (!f.exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter br = getBufferedWriter(f);
			br.write(message);
			br.newLine();
			br.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
