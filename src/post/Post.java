package post;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import payment.PaymentImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import common.Item;
import common.ItemTuple;
import common.PaymentType;
import common.Constants;
import common.Store;
import payment.CashImpl;
import payment.CheckImpl;
import payment.CreditCardImpl;



/**
 * Point Of Sale Terminal (POST)
 * Simulated cash register to help Customer objects
 * with purchase transactions.
 */
public class Post {

    private Store store;
	private HashMap<String, Item> items;
	private CustomerImpl currentCustomer;
	private BigDecimal total;
	private LinkedList<CustomerImpl> bufferQueue;
	
	public Post(Store store) {
	    this.store = store;
	    currentCustomer = null;
	    total = BigDecimal.ZERO;
	    //screen = new Screen();
	    // Cannot start without catalog.
	    while (!loadCatalog(store)) {
	        
	    }
	    //screen.setVisible(true);
	    helpNextCustomer();
	}
	
	// Load catalog from server. Return success or fail.
	private boolean loadCatalog(Store store) {
	    
	    try {
	        items = store.getInventory();
	    } catch(RemoteException ex) {
	        return false;
	    }
	    return true;
	}
	
	// Here comes a new customer!
    private void helpNextCustomer() {
        currentCustomer = new CustomerImpl();
        total = BigDecimal.ZERO;
    }
	
	// Methods used by GUI
	
	public ArrayList<String> getUPCList() {
	    if (items == null) return new ArrayList<String>();
	    return new ArrayList<String>(items.keySet());
	}
	
	public void setCustomerName(String name) {
	    currentCustomer.setName(name);
	}
	
	public void addItem(String UPC, int quantity) throws RemoteException {
	    currentCustomer.addItem(UPC, quantity);
	    total = total.add(getItem(UPC).getPrice().multiply(new BigDecimal(quantity)));
	}
	
	// Not use in this version
	public void removeItem(int index) throws RemoteException {
	    ItemTuple itemTuple = (ItemTuple) currentCustomer.removeItem(index);
	    total = total.add(getItem(itemTuple.getUPC()).getPrice().multiply(new BigDecimal(itemTuple.getQuantity())));
	}
	   
    public Item getItem(String UPC) {
        Item item = items.get(UPC);
//        if (item == null) {
//            loadCatalog(store);
//            item = items.get(UPC);
//            if (item == null) {
//                // do something
//            }
//        }
        return item;
    }
    
    // Don't use
    public BigDecimal getTotal() {
        return total;
    }
    
    public double getTotalDouble() {
        return total.doubleValue();
    }
    
    // For CASH and CHECK types, use "" or null as cardNumber
    public void addPayment(PaymentType paymentType, double amount, String cardNumber) {
        switch (paymentType) {
        case CASH:
            addPayment(new CashImpl(new BigDecimal(amount)));
            break;
        case CHECK:
            addPayment(new CheckImpl(new BigDecimal(amount)));
            break;
        case CREDIT:
            addPayment(new CreditCardImpl(new BigDecimal(amount), cardNumber));
            break;
        }
    }
    
    // Don't use
    public void addPayment(PaymentImpl payment) {
        currentCustomer.addPayment(payment);
    }
    
    // When click "Submit Order", i.e. when paying
    public void checkOut() {
        // The current CustomerImpl object is first put into a queue. Then
        // Post will try to empty the queue from head to tail.
        bufferQueue.add(currentCustomer);
        // Before trying to connect Server and empty the queue, Post will
        // create a new CustomerImpl object. That's because the connecting
        // may take a while and cashier may start to add items or change
        // customer's name. These operation must be applied to the new customer.
        helpNextCustomer();
        // Try to send the CustomerImpl object to the Server. Only remove
        // the object after Server return a result (no matter true or false).
        // So that if connect fails, the object is still in the queue.
        try {
            while (!bufferQueue.isEmpty()) {
                CustomerImpl customer = bufferQueue.getFirst();
                if (store.helpCustomer(customer)) {
                    printReceipt(customer);
                } else {
                    System.out.println("Payment Rejection!!");
                }
                bufferQueue.removeFirst();
            }
        } catch(RemoteException ex) {
            // do nothing
        }
        
    }
    
    private void printReceipt(CustomerImpl customer) {
        System.out.println("Receipt");
    }

	
	public static void main(String args[]) throws RemoteException {
	    if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry(Constants.REGISTRY_HOST, Constants.REGISTRY_PORT);
            Store store = (Store) registry.lookup(Constants.STORE_ID);
            Post me = new Post(store);
            
        } catch (RemoteException ex) {
            System.out.println("*********** " + ex);
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        }
	    
	}

}
