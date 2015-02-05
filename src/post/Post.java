package post;

import java.util.HashMap;

import store.Inventory;
import store.Item;

public class Post {

	private HashMap<String, Item> items;

	public void init() {
		items = Inventory.getItems();
	}

	public void cleanup() {
		// TODO Auto-generated method stub

	}

	public boolean verifyUPC(String scanned) {
		return items.containsKey(scanned);
	}

	public Item getItem(String scanned) {
		// TODO Auto-generated method stub
		return null;
	}

}
