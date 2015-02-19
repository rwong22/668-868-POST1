package post;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import common.Customer;
import common.ItemTuple;
import common.Payment;

/**
 * Object that corresponds to a user of this POST program.
 * Adds items to a list and uses post to process a sale.
 *
 */
public class CustomerImpl extends UnicastRemoteObject implements Customer {

    /**
     * default serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private String name;
    private ArrayList<ItemTuple> itemContainer = new ArrayList<ItemTuple>();
	private Payment payment;
	private String purchaseTime;
    
	public CustomerImpl() throws RemoteException {
	    super();
		this.name = "";
		itemContainer = new ArrayList<ItemTuple>();
		payment = null;
		purchaseTime = "";
	}
	
	// Don't use
	public CustomerImpl(String name, ArrayList<ItemTuple> items, Payment payment) throws RemoteException {
        super();
	    this.name = name;
        this.itemContainer = items;
        this.payment = payment;
        purchaseTime = "";
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
    
	public void addPayment(Payment payment) {
		this.payment = payment;
	}

	public Payment getPayment() {
		return payment;
	}
	
	public String getPurchaseTime() {
	    return purchaseTime;
	}
	
	public void setPurchaseTime(String dateTime) {
        purchaseTime = dateTime;
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

		try {
            customer += payment.getType().toString() + '\t' + payment.getAmount().toString() + '\n';
        } catch (RemoteException e) {
            e.printStackTrace();
        }

		return customer;
	}

    
}
