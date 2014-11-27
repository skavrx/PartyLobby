package us.project.party.file;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Custom File Configuration file for Project Party.
 * <p>
 * Created: Aug 17, 2014 <br>
 * Time: 4:50:58 PM <br>
 * Year: 2014
 * <p>
 * Project: ProjectParty <br>
 * File: PartyFile.java <br>
 * Package: us.project.party.file
 * 
 * @since ProjectParty-0.0.1-SNAPSHOT
 * 
 * @author 598Johnn897
 */
public class PartyYaml extends YamlConfiguration 
{

	private final JavaPlugin plugin;
	private final String fileName;
	private final String location;

	/**
	 * Custom YAML configuration for easy creation of files or configs. If the
	 * File does not exist it will create one and if it does it will load the
	 * file for use of it. If the file name already has the extension in it and
	 * the extension is still put it will only put one extension.
	 * <p>
	 * Recommended location for the file would be
	 * {@link JavaPlugin#getDataFolder()#getAbsolutePath()}.
	 * 
	 * @since ProjectParty-0.0.1-SNAPSHOT
	 * 
	 * @author 598Johnn897 <br>
	 *         Dragonphase
	 * 
	 * @see YamlConfiguration
	 * 
	 * @param plugin
	 *            The Plugin that owns this file.
	 * @param location
	 *            The location of the file.
	 * @param fileName
	 *            The files name.
	 * @param format
	 *            The files extension. ex. ".txt"
	 */
	public PartyYaml(JavaPlugin plugin, String location, String fileName,
			String format) 
	{
		this.plugin = plugin;
		this.location = location;
		this.fileName = fileName + (fileName.endsWith(format) ? "" : format);

		createFile();
	}

	/**
	 * Creates the file for thise class instance if it does not exist. If the
	 * file already exist if will load and save the file.
	 * 
	 * @since ProjectParty-0.0.1-SNAPSHOT
	 * 
	 * @author 598Johnn897 <br>
	 *         Dragonphase
	 * 
	 * @see File
	 * @see YamlConfiguration#load(File)
	 * @see YamlConfiguration#save(File)
	 */
	private void createFile() 
	{
		try 
		{
			File file = new File(location, fileName);
			if (!file.exists()) {
				if (plugin.getResource(fileName) != null) {
					plugin.saveResource(fileName, false);
				} else {
					save(file);
				}
			} else {
				load(file);
				save(file);
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Saves the config to the file at the location for this config.
	 * 
	 * @since ProjectParty-0.0.1-SNAPSHOT
	 * 
	 * @author 598Johnn897 <br>
	 *         Dragonphase
	 * 
	 * @see YamlConfiguration#save(File)
	 */
	public void save() 
	{
		try 
		{
			save(new File(location, fileName));
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}