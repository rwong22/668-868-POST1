package store;

import java.util.HashMap;

import utils.FileIO;
import utils.FileIOType;

public class Inventory {

	public static HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	private FileIO inventoryReader;

	public Inventory(String filePath) {
		inventoryReader = new FileIO(filePath, FileIOType.READER);
		items = inventoryReader.getItems();
		inventoryReader = null;
	}

}
