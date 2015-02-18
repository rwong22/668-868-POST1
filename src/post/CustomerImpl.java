package post;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import common.Customer;
import common.ItemTuple;
import payment.PaymentImpl;

/**
 * Object that corresponds to a user of this POST program.
 * Adds items to a list and uses post to process a sale.
 *
 */
public class CustomerImpl implements Customer {

    private String name;
    private ArrayList<ItemTuple> itemContainer = new ArrayList<ItemTuple>();
	private PaymentImpl payment;
    
	public CustomerImpl() {
		this.name = "";
		itemContainer = new ArrayList<ItemTuple>();
		payment = null;
	}
	
	public CustomerImpl(String name, ArrayList<ItemTuple> items, PaymentImpl payment) {
        this.name = name;
        this.itemContainer = items;
        this.payment = payment;
    }
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public void addItem(String upc, int quantity) {
		itemContainer.add(new ItemTuple(upc, quantity));
	}

	public void addItem(ItemTuple item) {
		itemContainer.add(item);
	}
	
	// Not use in this version
	public ItemTuple removeItem(int index) {
	    if (index >= 0 && index < itemContainer.size()) {
	        return itemContainer.remove(index);
	    } else {
	        return null;
	    }
	}

    public ArrayList<ItemTuple> getItems() throws RemoteException {
        return itemContainer;
    }
    
	public void addPayment(PaymentImpl payment) {
		this.payment = payment;
	}

	public PaymentImpl getPayment() {
		return payment;
	}

	/*
	 * For debugging
	 */
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
