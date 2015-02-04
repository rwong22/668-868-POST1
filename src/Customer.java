import java.util.ArrayList;

public class Customer extends User {
	private ArrayList<Item> itemContainer = new ArrayList<Item>();
	private ArrayList<Payment> paymentContainer = new ArrayList<Payment>();

	Customer(String name) {
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
