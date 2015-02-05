package post;

import java.util.ArrayList;

import payment.Payment;
import utils.ItemTuple;
import utils.Transaction;

public class Customer extends User {

	private ArrayList<ItemTuple> itemContainer = new ArrayList<ItemTuple>();
	private Payment payment;

	public Customer(String name) {
		super(name);
	}

	public Customer(Transaction transaction) {
		super(transaction.getCustomerName());
		this.itemContainer = transaction.getItems();
	}

	public void addItem(String upc, int quantity) {
		itemContainer.add(new ItemTuple(upc, quantity));
	}

	public void addPayment(Payment payment) {
		this.payment = payment;
	}

	public ArrayList<ItemTuple> getItemContainer() {
		return itemContainer;
	}

	public Payment getPayment() {
		return payment;
	}

}
