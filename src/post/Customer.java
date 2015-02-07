package post;

import java.util.ArrayList;
import java.util.Iterator;

import payment.Payment;
import utils.ItemTuple;

public class Customer extends User {

	private ArrayList<ItemTuple> itemContainer = new ArrayList<ItemTuple>();
	private Payment payment;

	public Customer(String name) {
		super(name);
	}

	public Customer(String name, ArrayList<ItemTuple> items, Payment payment) {
		super(name);
		this.itemContainer = items;
		this.payment = payment;
	}

	public void addItem(String upc, int quantity) {
		itemContainer.add(new ItemTuple(upc, quantity));
	}

	public void addItem(ItemTuple item) {
		itemContainer.add(item);
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

	@Override
	public String toString() {
		String customer = getName() + '\n';

		for (Iterator<ItemTuple> iterator = itemContainer.iterator(); iterator.hasNext();) {
			ItemTuple itemTuple = (ItemTuple) iterator.next();
			customer += itemTuple.getUPC() + '\t' + itemTuple.getQuantity() + '\n';
		}

		customer += payment.getType().toString() + '\t' + payment.getAmount().toString() + '\n';

		return customer;
	}
}
