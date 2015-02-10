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

/**
 * Point Of Sale Terminal (POST)
 * Simulated cash register to help Customer objects
 * with purchase transactions.
 */
public class Post {

	private HashMap<String, Item> items;

	public void init() {
		items = Inventory.getItems();
	}

	public void cleanup() {
		items = null;
	}

	/*
	 * Processes Customer objects
	 * and passes customer item list to checkout.
	 */
	public boolean helpCustomer(Customer customer) {
		Customer currentCustomer = customer;
		BigDecimal total = new BigDecimal(0);

		for (Iterator<ItemTuple> iterator = currentCustomer.getItemContainer().iterator(); iterator.hasNext();) {
			ItemTuple currentItem = (ItemTuple) iterator.next();

			if (verifyUPC(currentItem.getUPC())) {
				total = total.add(items.get(currentItem.getUPC()).getPrice());
			} else {
				System.out.println("UPC: " + currentItem.getUPC() + " not found");
				currentCustomer.getItemContainer().remove(currentItem);
			}
		}

		checkout(currentCustomer);

		return true;
	}

	private boolean verifyUPC(String scanned) {
		return items.containsKey(scanned);
	}

	/*
	 * After customer object payment authentication, records sale
	 * @return	true	Payment authentication succeeded
	 * @return	false	Payment authentication failed
	 */
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

	/*
	 * Records sales using SalesLog
	 * @return	true	log written
	 * @return	false	log failed to write
	 */
	private boolean recodSale(String storeName, String customerName, ArrayList<ItemTuple> items, Payment payment) {
		SalesLog log = new SalesLog(storeName, customerName, items, payment);

		return log.writeLog();
	}

}
