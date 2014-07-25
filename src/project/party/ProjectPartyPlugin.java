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
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author 598Johnn897
 * 
 *         Created: Jul 24, 2014 Time: 10:25:25 PM Year: 2014
 * 
 *         By: 598Johnn897
 * 
 *         Project: PartyLobby File: ProjectPartyPlugin.java Package:
 *         project.party
 */
public class ProjectPartyPlugin extends JavaPlugin {

	private String name;
	private static ProjectPartyPlugin plugin;

	@SuppressWarnings("static-access")
	public ProjectPartyPlugin(ProjectPartyPlugin plugin, String name) {
		this.plugin = plugin;
		this.name = name;
	}

	public static ProjectPartyPlugin get() {
		return plugin;
	}

	private int start;

	@Override
	public void onLoad() {
		start = (int) System.currentTimeMillis();
	}

	private int end;

	@Override
	public void onEnable() {
		end = (int) (System.currentTimeMillis() - start);
		log(" " + name + " has enabled [" + end + "ms]");
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

	@SuppressWarnings("deprecation")
	public void handleCrash(Exception e) {
		Date date = new Date();
		File file = new File(plugin.getDataFolder(), "crash-" + date.getDate() + ".txt");
		if(!file.exists()){
			try {
				file.createNewFile();
				writeTo(file, "Date: "+date.getDate()+" "+e.toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		logError(name+": An Error Has Occured During Start Up");
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
