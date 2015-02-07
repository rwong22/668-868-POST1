package post;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

	public boolean helpCustomer(Customer customer) {
		Customer currentCustomer = customer;
		BigDecimal total = new BigDecimal(0);
		ArrayList<ItemTuple> itemListCopy = new ArrayList<ItemTuple>();
		
		
		for (Iterator<ItemTuple> iterator = currentCustomer.getItemContainer().iterator(); iterator.hasNext();) {
			ItemTuple currentItem = (ItemTuple) iterator.next();

			if (verifyUPC(currentItem.getUPC())) {
				total = total.add(items.get(currentItem.getUPC()).getPrice());
			} else {
				System.out.println("UPC: " + currentItem.getUPC() + " not found, item not added.");
				iterator.remove();
			}
		}

		checkout(currentCustomer);

		return true;
	}

	private boolean verifyUPC(String scanned) {
		return items.containsKey(scanned);
	}

	private boolean checkout(Customer customer) {
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
