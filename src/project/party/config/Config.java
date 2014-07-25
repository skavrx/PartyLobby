package project.party.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Config {

	private Map<String, Map<String, String>> values;

	public Config(File file) {
		values = new HashMap<String, Map<String, String>>();

		if (file.exists()) {
			try {
				String section = "";
				Scanner scanner = new Scanner(file);
				String line, key, val;
				String[] data;
				while (scanner.hasNext()) {
					line = scanner.nextLine().trim();

					if (line.startsWith("#") || line.startsWith("//")) {
						// ignore
						continue;
					} else if (line.startsWith("=")) {
						section = line.replace("=", "").trim().toLowerCase();
						continue;
					} else if (line.contains(":")) {
						data = line.split(":", 2);
						key = data[0].trim();
						val = data[1].trim();
					} else if (line.contains("=")) {
						data = line.split("=", 2);
						key = data[0].trim();
						val = data[1].trim();
					} else {
						// no key/value pair, so ignoring line
						continue;
					}
					if ((val.startsWith("\"") && val.endsWith("\""))
							|| (val.startsWith("'") && val.endsWith("'"))) {
						val = val.substring(1, val.length() - 1);
					}
					Map<String, String> secdata = values.get(section);
					if (secdata == null) {
						secdata = new HashMap<String, String>();
						values.put(section, secdata);
					}
					secdata.put(key.toLowerCase(), val);
				}
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String getValue(String path) {
		path = path.toLowerCase();
		if (path.contains(".")) {
			String[] pathdata = path.split("\\.");
			Map<String, String> secdata = values.get(pathdata[0]);
			if (secdata == null) {
				return null;
			} else {
				return secdata.get(pathdata[1]);
			}
		} else {
			Map<String, String> secdata = values.get("");
			if (secdata == null) {
				return null;
			} else {
				return secdata.get(path);
			}
		}
	}

	public String getString(String path) {
		return getValue(path);
	}

	public int getint(String path) {
		String val = getValue(path);
		if (val == null) {
			return 0;
		} else {
			int i = 0;
			try {
				i = Integer.parseInt(val);
			} catch (NumberFormatException e) {
			}
			return i;
		}
	}

	public Integer getInteger(String path) {
		String val = getValue(path);
		if (val == null) {
			return null;
		} else {
			try {
				return Integer.valueOf(val);
			} catch (NumberFormatException e) {
				return null;
			}
		}
	}

	public double getdouble(String path) {
		String val = getValue(path);
		if (val == null) {
			return 0;
		} else {
			double i = 0;
			try {
				i = Double.parseDouble(val);
			} catch (NumberFormatException e) {
			}
			return i;
		}
	}

	public Double getDouble(String path) {
		String val = getValue(path);
		if (val == null) {
			return null;
		} else {
			try {
				return Double.valueOf(val);
			} catch (NumberFormatException e) {
				return null;
			}
		}
	}

	public boolean getboolean(String path) {
		String val = getValue(path);
		if (val == null) {
			return false;
		} else {
			val = val.toLowerCase();
			if (val.equalsIgnoreCase("true") || val.startsWith("y")
					|| val.equalsIgnoreCase("on")) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Boolean getBoolean(String path) {
		String val = getValue(path);
		if (val == null) {
			return null;
		} else {
			val = val.toLowerCase();
			if (val.equalsIgnoreCase("true") || val.startsWith("y")
					|| val.equalsIgnoreCase("on")) {
				return true;
			} else if (val.equalsIgnoreCase("false") || val.startsWith("n")
					|| val.equalsIgnoreCase("off")) {
				return false;
			} else {
				return null;
			}
		}
	}

	public List<String> getStringList(String path) {
		String val = getValue(path);
		if (val == null) {
			return null;
		} else {
			List<String> list = new ArrayList<String>();
			String[] values = val.split(",");
			for (String v : values) {
				list.add(v.trim());
			}
			return list;
		}
	}

	public List<Integer> getIntegerList(String path) {
		String val = getValue(path);
		if (val == null) {
			return null;
		} else {
			List<Integer> list = new ArrayList<Integer>();
			String[] values = val.split(",");
			for (String v : values) {
				try {
					list.add(Integer.parseInt(v.trim()));
				} catch (NumberFormatException e) {
					return null;
				}
			}
			return list;
		}
	}

	public List<Double> getDoubleList(String path) {
		String val = getValue(path);
		if (val == null) {
			return null;
		} else {
			List<Double> list = new ArrayList<Double>();
			String[] values = val.split(",");
			for (String v : values) {
				try {
					list.add(Double.parseDouble(v.trim()));
				} catch (NumberFormatException e) {
					return null;
				}
			}
			return list;
		}
	}

	public Set<String> getKeys(String section) {
		Map<String, String> vals = values.get(section);
		if (vals == null) {
			return null;
		} else {
			return vals.keySet();
		}
	}

}