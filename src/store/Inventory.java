package store;

import java.util.HashMap;

import utils.FileIO;

public class Inventory {

	public static HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	private static FileIO inventoryReader;
	private static String filePath;

	public Inventory(String filePath) {
		this.filePath = filePath;
		inventoryReader = new FileIO(filePath);
		items = inventoryReader.getItems();

		inventoryReader = null;
	}

	public static HashMap<Integer, Item> getItems() {
		if (items == null) {
			inventoryReader = new FileIO(filePath);
			items = inventoryReader.getItems();
		}

		return items;
	}
}
