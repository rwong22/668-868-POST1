package post;

import java.util.ArrayList;

import payment.Payment;
import utils.ItemTuple;

public class Customer extends User {

	private ArrayList<ItemTuple> itemContainer = new ArrayList<ItemTuple>();
	private ArrayList<Payment> paymentContainer = new ArrayList<Payment>();

	public Customer(String name) {
		super(name);
	}

	public void addItem(String upc) {
		itemContainer.add(new ItemTuple(upc, 1));
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
