/*
 * Author: 598Johnn897
 * 
 * Date: Jul 28, 2014
 * Package: project.party.handlers
 *
 */
package project.party.lobby.handlers;

import static project.party.perms.util.CrashUtil.handleCrash;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import project.party.lobby.PartyLobbyMain;
import project.party.lobby.storage.Storage;

/**
 * Created: Jul 28, 2014 <br>
 * Time: 6:18:29 PM <br>
 * Year: 2014
 * <p>
 * 
 * By: 598Johnn897
 * <p>
 * 
 * Project: PartyPerms <br>
 * File: PlayerJoinHandler.java <br>
 * Package: project.party.handlers
 * <p>
 * 
 * @author 598Johnn897
 */
public class PlayerJoinHandler implements Listener {

	private PartyLobbyMain plugin = PartyLobbyMain.get();
	private Storage storage = plugin.getStorage();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		try {
			Player player = event.getPlayer();
			String playerName = player.getName();
			String playerDisplayName = player.getDisplayName();
			UUID playerUUID = player.getUniqueId();
			
			storage.onlineStrings.add(playerName);
			storage.onlineUUIDs.add(playerUUID);
			
		} catch (Exception e) {
			handleCrash(e);
		}
	}

}
