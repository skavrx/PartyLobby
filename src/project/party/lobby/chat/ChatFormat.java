/*
 * Author: 598Johnn897
 * 
 * Date: Jul 29, 2014
 * Package: project.party.lobby.chat
 *
 */
package project.party.lobby.chat;

import static project.party.lobby.util.ColorUtil.formatString;
import static project.party.lobby.util.CrashUtil.handleCrash;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import project.party.lobby.PartyLobbyMain;
import project.party.perms.rank.PartyClass;
import project.party.perms.rank.PartyRank;

/**
 * Created: Jul 29, 2014 <br>
 * Time: 1:55:10 PM <br>
 * Year: 2014
 * <p>
 * 
 * By: 598Johnn897
 * <p>
 * 
 * Project: PartyLobby <br>
 * File: ChatFormat.java <br>
 * Package: project.party.lobby.chat
 * <p>
 * 
 * @author 598Johnn897
 */
public class ChatFormat implements Listener {

	private PartyLobbyMain plugin = PartyLobbyMain.get();

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		try {
		Player player = event.getPlayer();
		PartyRank rank = plugin.getStorage().rank.get(player.getUniqueId());
		PartyClass clazz = plugin.getStorage().clazz.get(player.getUniqueId());

		ChatColor color = clazz.getColor();
		String prefix = rank.getChatPrefix();
		String message = event.getMessage();

		event.setFormat(format(player, rank, clazz, prefix, message, color));
		} catch (Exception e) {
			handleCrash(e);
			event.setFormat(event.getPlayer().getName()+": "+event.getMessage());
		}
	}

	public String format(Player player, PartyRank rank, PartyClass clazz,
			String prefix, String message, ChatColor color) {
		String playerName = player.getName();
		String formated;
		String chatColor;
		if(rank.isDefault()) chatColor = "<gray>"; else chatColor = "<white>";
		formated = formatString("%s%s %s%s:%s", color, prefix, playerName, chatColor, message);
		return formated;
	}
}
