package post;

import java.util.ArrayList;

import payment.Payment;
import utils.ItemTuple;
import utils.Transaction;

public class Customer extends User {

	private ArrayList<ItemTuple> itemContainer = new ArrayList<ItemTuple>();
	private ArrayList<Payment> paymentContainer = new ArrayList<Payment>();

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
		paymentContainer.add(payment);
	}

	public ArrayList<ItemTuple> getItemContainer() {
		return itemContainer;
	}

	public ArrayList<Payment> getPaymentContainer() {
		return paymentContainer;
	}

}
