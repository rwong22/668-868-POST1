package post;

import java.util.ArrayList;
import java.util.HashMap;

import payment.Payment;
import store.Inventory;
import store.Item;
import utils.Auth;
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

	public boolean checkout(Customer customer) {
		String customerName = customer.getName();
		ArrayList<ItemTuple> itemContainer = customer.getItemContainer();
		Payment payment = customer.getPayment();

		if (Auth.authenticate(payment)) {
			recodSale("MyStore", customerName, itemContainer, payment);
		} else {
			return false;
		}

		return true;
	}

	private boolean recodSale(String storeName, String customerName, ArrayList<ItemTuple> items, Payment payment) {
		SalesLog log = new SalesLog(storeName, customerName, items, payment);

		return log.writeLog();
	}

}
