package post;

import java.util.ArrayList;

import payment.Payment;
import store.Item;

public class Customer extends User {
	private ArrayList<Item> itemContainer = new ArrayList<Item>();
	private ArrayList<Payment> paymentContainer = new ArrayList<Payment>();

	public Customer(String name) {
		super(name);
	}

	public void addItem(Item item) {
		itemContainer.add(item);
	}

	public void addPayment(Payment payment) {
		paymentContainer.add(payment);
	}

	public ArrayList<Item> getItemContainer() {
		return itemContainer;
	}

	public ArrayList<Payment> getPaymentContainer() {
		return paymentContainer;
	}

}
