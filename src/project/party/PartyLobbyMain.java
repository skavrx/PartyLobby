/*
 * Author: 598Johnn897
 * 
 * Date: Jul 24, 2014
 * Package: project.party
 *
 */
package project.party;

import static project.party.util.CrashUtil.handleCrash;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import project.party.commands.CommandFramework;
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

	private CommandFramework framework;

	private int start;
	private int end;

	@Override
	public void onLoad() {
		try {
			plugin = this;
			framework = new CommandFramework(this);
			start = (int) System.currentTimeMillis();
		} catch (Exception e) {
			handleCrash(e);
		}
	}

	@Override
	public void onEnable() {
		try {
			framework.registerCommands();
			framework.registerHelp();
		} catch (Exception e) {
			handleCrash(e);
		} finally {
			end = (int) (System.currentTimeMillis() - start);
			log(String.format("%s has enabled. [%d]", References.NAME, end));
		}
	}

	@Override
	public void onDisable() {
		try {

		} catch (Exception e) {
			handleCrash(e);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender,
			org.bukkit.command.Command command, String label, String[] args) {
		return framework.handleCommand(sender, label, command, args);
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
