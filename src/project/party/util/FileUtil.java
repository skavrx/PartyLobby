package project.party.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class FileUtil {

	public static HashMap<File, BufferedWriter> writers = new HashMap<File, BufferedWriter>();

	public static BufferedWriter getBufferedWriter(File f) {
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

	public static void writeTo(File f, String message) {
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
