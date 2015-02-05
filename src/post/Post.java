package post;

import java.util.ArrayList;
import java.util.HashMap;

import payment.Payment;
import store.Inventory;
import store.Item;
import utils.ItemTuple;
import utils.SalesLog;

public class Post {

	private HashMap<String, Item> items;

	public void init() {
		items = Inventory.getItems();
	}

	public void cleanup() {
		items = null;
	}

	public boolean verifyUPC(String scanned) {
		return items.containsKey(scanned);
	}

	public String checkout(Customer customer) {
		String customerName = customer.getName();
		ArrayList<ItemTuple> itemContainer = customer.getItemContainer();
		Payment payment = customer.getPayment();

		return null;
	}

	private boolean recodSale(String storeName, String customerName, ArrayList<ItemTuple> items, Payment payment) {
		SalesLog log = new SalesLog(storeName, customerName, items, payment);

		return log.writeLog();
	}

}
