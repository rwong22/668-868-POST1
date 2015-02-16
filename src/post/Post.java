package post;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import payment.Payment;
import store.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;



/**
 * Point Of Sale Terminal (POST)
 * Simulated cash register to help Customer objects
 * with purchase transactions.
 */
public class Post {

    private Store store;
	private HashMap<String, Item> items;
	private CustomerImpl currentCustomer;
	private double total;
	private LinkedList<CustomerImpl> bufferQueue;
	
	public Post(Store store) {
	    this.store = store;
	    currentCustomer = null;
	    total = 0.0;
	    // Cannot start without catalog.
	    while (!loadCatalog(store)) {
	        
	    }
	    helpNextCustomer();
	}
	
	// Load catalog from server. Return success or fail.
	private boolean loadCatalog(Store store) {
	    
//	    try {
//	        // Get HashMap items from Inventory.
//          // Need reference from Store object.
//	    } catch(RemoteException ex) {
//	        return false;
//	    }
	    return true;
	}
	
	// Here comes a new customer!
    private void helpNextCustomer() {
        currentCustomer = new CustomerImpl();
        total = 0.0;
    }
	
	// Methods used by GUI
	
	public ArrayList<String> getUPCList() {
	    if (items == null) return new ArrayList<String>();
	    return new ArrayList<String>(items.keySet());
	}
	
	public void setCustomerName(String name) {
	    currentCustomer.setName(name);
	}
	
	public void addItem(String upc, int quantity) {
	    currentCustomer.addItem(upc, quantity);
	    total += getItem(upc).getPrice() * quantity;
	}
	
	// Not use in this version
	public void removeItem(int index) {
	    ItemTuple itemTuple = (ItemTuple) currentCustomer.removeItem(index);
	    total -= getItem(itemTuple.getUPC()).getPrice() * itemTuple.getQuantity();
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
    
    public double getTotal() {
        return total;
    }
    
    public void addPayment(Payment payment) {
        currentCustomer.addPayment(payment);
    }
    
    public void checkOut() {
        bufferQueue.add(currentCustomer);
//        try {
//            while (!bufferQueue.isEmpty()) {
//                // Send CustomerImpl objects in bufferQueue to the server.
//                // Should use getFirst() and removeFirst().
//            }
//        } catch(RemoteException ex) {
//            
//        }
        helpNextCustomer();
    }
    
//
//	public void init() {
//		items = Inventory.getItems();
//	    
//	}
//
//	public void cleanup() {
//		items = null;
//	}
//
//	/*
//	 * Processes Customer objects
//	 * and passes customer item list to checkout.
//	 */
//	public boolean helpCustomer(Customer customer) {
//		Customer currentCustomer = customer;
//		BigDecimal total = new BigDecimal(0);
//
//		for (Iterator<ItemTuple> iterator = currentCustomer.getItemContainer().iterator(); iterator.hasNext();) {
//			ItemTuple currentItem = (ItemTuple) iterator.next();
//			total = total.add(items.get(currentItem.getUPC()).getPrice());
//
//		}
//
//		checkout(currentCustomer);
//
//		return true;
//	}
//
//	/*
//	 * private boolean verifyUPC(String scanned) {
//	 * return items.containsKey(scanned);
//	 * }
//	 * <<<<<<< HEAD
//	 * /*
//	 * After customer object payment authentication, records sale
//	 * @return true Payment authentication succeeded
//	 * @return false Payment authentication failed
//	 */
//	private boolean checkout(Customer customer) {
//		String customerName = customer.getName();
//		ArrayList<ItemTuple> itemContainer = customer.getItemContainer();
//		Payment payment = customer.getPayment();
//
//		if (Auth.authenticate(payment)) {
//			recordSale("MyStore", customerName, itemContainer, payment);
//		} else {
//			return false;
//		}
//
//		return true;
//	}
//
//	/*
//	 * Records sales using SalesLog
//	 * @return true log written
//	 * @return false log failed to write
//	 */
//	private boolean recordSale(String storeName, String customerName, ArrayList<ItemTuple> items, Payment payment) {
//		SalesLog log = new SalesLog(storeName, customerName, items, payment);
//
//		return log.writeLog();
//	}
	
	public static void main(String args[]) throws RemoteException {
	    if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry rmtReg = LocateRegistry.getRegistry();
            //Assume the Store class will implements Remote Interface
            //Assume the string "store" will be rebind to the store object
            Store store = (Store)rmtReg.lookup("store");
            Post me = new Post(store);
            
        } catch (RemoteException ex) {
            System.out.println("*********** " + ex);
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        }
    
	}

}
