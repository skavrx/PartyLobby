/*
 * Author: 598Johnn897
 * 
 * Date: Jul 24, 2014
 * Package: project.party
 *
 */
package project.party.lobby;

import static project.party.perms.util.CrashUtil.handleCrash;
import static project.party.perms.util.LogUtil.log;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import project.party.perms.commands.CommandFramework;
import project.party.perms.commands.CommandFramework.ClassEnumerator;
import project.party.perms.lib.References;
import project.party.lobby.storage.Storage;
/**
 * @author 598Johnn897
 * 
 *         Created: Jul 24, 2014 Time: 4:43:55 PM Year: 2014
 * 
 *         By: 598Johnn897
 * 
 *         Project: PartyLobby File: PartyLobby.java Package: project.party
 */
public class PartyLobbyMain extends JavaPlugin implements Listener {

	private static PartyLobbyMain plugin;
	public static PartyLobbyMain get() {
		return plugin;
	}

	private CommandFramework framework;

	private Storage storage;
	public Storage getStorage() {
		return storage;
	}
	
	private int start;
	private int end;

	@Override
	public void onLoad() {
		try {
			plugin = this;
			framework = new CommandFramework(this);
			storage = new Storage();
			start = (int) System.currentTimeMillis();
		} catch (Exception e) {
			handleCrash(e);
		}
	}

	@Override
	public void onEnable() {
		try {
			this.registerEvents();
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
	
	/**
	 * Dynamically registers all listeners/events in project.
	 */
	public void registerEvents() {
		Class<?>[] classes = ClassEnumerator.getInstance()
				.getClassesFromThisJar(plugin);
		if (classes == null || classes.length == 0) {
			return;
		}
		for (Class<?> c : classes) {
			try {
				if (Listener.class.isAssignableFrom(c)
						&& !c.isInterface() && !c.isEnum() && !c.isAnnotation()) {
					if (JavaPlugin.class.isAssignableFrom(c)) {
						if (plugin.getClass().equals(c)) {
							plugin.getLogger().log(Level.INFO,
									"Searching class: " + c.getSimpleName());
							Bukkit.getPluginManager().registerEvents(plugin, plugin);
						}
					} else {
						plugin.getLogger().log(Level.INFO,
								"Searching class: " + c.getSimpleName());
						Bukkit.getPluginManager().registerEvents((Listener) c.newInstance(), plugin);;
					}
				}
			} catch (IllegalAccessException | InstantiationException e) {
				plugin.getLogger().log(
						Level.INFO,
						c.getSimpleName()
								+ " does not use the default constructor");
				e.printStackTrace();
			}
		}
	}
}
