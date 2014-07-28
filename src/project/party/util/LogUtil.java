package project.party.util;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class LogUtil {

	public static void log(String msg) {
		Bukkit.getLogger().log(Level.INFO, ChatColor.stripColor(msg));
	}

	public static void logWarn(String msg) {
		Bukkit.getLogger().log(Level.WARNING, ChatColor.stripColor(msg));
	}

	public static void logError(String msg) {
		Bukkit.getLogger().log(Level.SEVERE, ChatColor.stripColor(msg));
	}
}
