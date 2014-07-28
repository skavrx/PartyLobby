package project.party.lobby.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import project.party.perms.inventory.DClassInventory;
import project.party.perms.inventory.PartyInventory;

public class PartyInvHandler {
	
	public static HashMap<Player, PartyInventory> playerInventory = new HashMap<Player, PartyInventory>();
	public static List<PartyInventory> inventoryList = new ArrayList<PartyInventory>();

	public PartyInvHandler(){
		inventoryList.add(new DClassInventory());
	}
	
	public static void setInventory(Player player, PartyInventory inv)
	{
		if (playerInventory.containsKey(player)) 
			playerInventory.remove(player);
		playerInventory.put(player, inv);
		PartyInventory pt = playerInventory.get(player);
		pt.addItemsToPlayer(player);
	}

	public static PartyInventory getInventory(Player player)
	{
		if (playerInventory.containsKey(player)) 
			return playerInventory.get(player);
		for (PartyInventory inv : inventoryList)
		{
			if (inv.isDefault()) 
				return inv;
		}
		return null;
	}
	public static PartyInventory findInventory(String name)
	{
		for (PartyInventory inv : inventoryList)
		{
			if (inv.getName().equalsIgnoreCase(name)) 
				return inv;
		}
		return null;
	}
}
