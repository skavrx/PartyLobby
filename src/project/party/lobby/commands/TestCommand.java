/*
 * Author: 598Johnn897
 * 
 * Date: Jul 28, 2014
 * Package: project.party.commands
 *
 */
package project.party.lobby.commands;

import project.party.perms.commands.CommandFramework.Command;
import project.party.perms.commands.CommandFramework.CommandArgs;
import project.party.perms.commands.CommandFramework.CommandListener;

/**
 * Created: Jul 28, 2014 <br>
 * Time: 11:11:54 AM <br>
 * Year: 2014 <p>
 *
 * By: 598Johnn897 <p>
 * 
 * Project: PartyLobby <br>
 * File: TestCommand.java <br>
 * Package: project.party.commands <p>
 * 
 * @author 598Johnn897
 */
public class TestCommand implements CommandListener {
	
	@Command(command = "test")
	public void test(CommandArgs args) {
		args.getSender().sendMessage("TEST TEST TEST");
	}

}
