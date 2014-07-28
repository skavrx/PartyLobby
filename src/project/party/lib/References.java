/*
 * Author: 598Johnn897
 * 
 * Date: Jul 27, 2014
 * Package: project.party.lib
 *
 */
package project.party.lib;

import project.party.PartyLobbyMain;

/**
 * @author 598Johnn897
 *
 * Created: Jul 27, 2014
 * Time: 10:38:20 PM
 * Year: 2014
 *
 * By: 598Johnn897
 * 
 * Project: PartyLobby
 * File: References.java
 * Package: project.party.lib
 */
public class References {

	private static PartyLobbyMain plugin = PartyLobbyMain.get();
	
	public static final String NAME = plugin.getDescription().getName();
	public static final String VERSION = plugin.getDescription().getVersion();
	public static final String AUTHORS = plugin.getDescription().getAuthors().toString();
	public static final String MAIN = plugin.getDescription().getMain();
	public static final String CRASH_FILE_FORMAT = ".txt";
	public static final String CRASH_FOLDER_PATH_EXT = "/party-crashes";
}
