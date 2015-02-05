package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;

public class Inventory {

	private static HashMap<String, Item> items;
	private static String filePath;
	private static BufferedReader bufferedReader;

	public Inventory(String filePath) {
		Inventory.filePath = filePath;
	}

	public static HashMap<String, Item> getItems() {
		if (items == null) {
			readProducts();
		}
		return items;
	}

	private static boolean readProducts() {
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			items = new HashMap<String, Item>();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			return false;
		}

		String line = " ";
		while (line != null) {
			try {
				line = bufferedReader.readLine();

				if (line != null) {
					String[] tokens = new String[3];
					tokens[0] = line.substring(0, 4);
					tokens[1] = line.substring(9, 29);
					tokens[2] = line.substring(34, 41);

					items.put(tokens[0], new Item(tokens[0], tokens[1], null, new BigDecimal(tokens[2])));
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		return true;
	}
}
