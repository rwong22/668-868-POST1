package post;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import payment.PaymentImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import GUI.PostGUI;
import common.Customer;
import common.Item;
import common.ItemTuple;
import common.PaymentType;
import common.Constants;
import common.Store;
import payment.CashImpl;
import payment.CheckImpl;
import payment.CreditCardImpl;
import services.AuthenticateService;



/**
 * Point Of Sale Terminal (POST)
 * Simulated cash register to help Customer objects
 * with purchase transactions.
 */
public class Post {
    
    public static void main(String args[]) throws RemoteException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry(Constants.REGISTRY_HOST, Constants.REGISTRY_PORT);
            Store store = (Store) registry.lookup(Constants.STORE_ID);
//            Store store = new TestStore(); // For test only.
            Post me = new Post(store);
            
        } catch (RemoteException e) {
            System.out.println("Fail to find store " + e);
            e.printStackTrace();
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        }
        
    }

    private Store store;
    private PostGUI GUI;
    private HashMap<String, Item> items;
    private CustomerImpl currentCustomer;
    private BigDecimal total;
    private LinkedList<CustomerImpl> bufferQueue;
    
    public Post(Store store) throws RemoteException {
        this.store = store;
        currentCustomer = new CustomerImpl();
        total = BigDecimal.ZERO;
        bufferQueue = new LinkedList<CustomerImpl>();
        // Can Post start without complete catalog?
        while (!loadCatalog(store)) {
            break; // For test only!!
        }
        GUI = new PostGUI(this);
        GUI.open();
    }
    
    /*
     * Load catalog from server.
     * Return true when the whole catalog was loaded or false when not.
     * Note: running this method will clear the items HashMap first!
     */
    private boolean loadCatalog(Store store) {
        items = new HashMap<String, Item>();
        try {
//            items = store.getInventory();
            HashMap<String, Item> catalog = (HashMap<String, Item>) store.getCatalog().clone();
            for (Item item : catalog.values()) {
                String UPC = item.getUPC();
                items.put(UPC, new ItemImpl(UPC, item.getDescription(), item.getPrice()));
            }
        } catch(RemoteException e) {
            System.out.println("Fail to load catalog " + e);
            return false;
        }
        return true;
    }
    
    // ----------Methods used by GUI---------
    
    /*
     * Return a ArrayList<String> of all the UPC of items in catalog.
     */
    public ArrayList<String> getUPCList() {
        ArrayList<String> list = new ArrayList<String>(items.keySet());
        Collections.sort(list);
        return list;
    }
    
    /*
     * Change current customer's name to the string of parameter.
     */
    public void setCustomerName(String name) {
        currentCustomer.setName(name);
    }
    
    /*
     * Add a pair of UPC and quantity for current customer.
     * If can't find UPC in catalog, report in console then do nothing.
     */
    public void addItem(String UPC, int quantity) {
        Item item = getItem(UPC);
        if (item != null) {
            total = total.add(((ItemImpl) item).getPrice().multiply(new BigDecimal(quantity)));
            currentCustomer.addItem(UPC, quantity);
        } else {
            System.out.println("Can't find " + UPC + " in catalog");
        }
    }
    
    @Deprecated // Not use in this version
    public void removeItem(int index) {
        ItemTuple itemTuple = (ItemTuple) currentCustomer.removeItem(index);
        try {
            total = total.add(getItem(itemTuple.getUPC()).getPrice().multiply(new BigDecimal(itemTuple.getQuantity())));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Return a Item object from catalog which matches the UPC.
     * If can't find in local catalog while can't find in/can't connect to
     * the remote inventory, return null;
     */
    public Item getItem(String UPC) {
        Item item = items.get(UPC);
        // If can't find item, try search in the store inventory.
        if (item == null) {
            try {
                Item rmiItem = store.getCatalog().get(UPC);
                if (rmiItem != null) {
                    item = new ItemImpl(UPC, rmiItem.getDescription(), rmiItem.getPrice());
                    items.put(UPC, item);
                }
            } catch (RemoteException e) {
                System.out.println("Fail to connect to store " + e);
            }
            // Store inventory doesn't have neither or connect fails.
//            if (item == null) {}
        }
        return item;
    }
    
    @Deprecated
    public BigDecimal getTotal() {
        return total;
    }
    
    /*
     * Return total payment amount of current customer.
     */
    public double getTotalDouble() {
        return total.doubleValue();
    }
    
    /*
     * Change the purchase time of current customer.
     */
    public void setPurchaseTime(String dateTime) {
        currentCustomer.setPurchaseTime(dateTime);
    }
    
    // For CASH and CHECK types, use "" or null as cardNumber
    @Deprecated
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
    
    /*
     * Add a cash payment to current customer.
     */
    public void addCashPayment(double amount) {
        addPayment(new CashImpl(new BigDecimal(amount)));
    }
    
    /*
     * Add a check payment to current customer.
     */
    public void addCheckPayment(double amount) {
        addPayment(new CheckImpl(new BigDecimal(amount)));
    }
    
    /*
     * Add a credit card payment to current customer.
     */
    public void addCreditPayment(double amount, String cardNumber) {
        addPayment(new CreditCardImpl(new BigDecimal(amount), cardNumber));
    }
    
    private void addPayment(PaymentImpl payment) {
        currentCustomer.addPayment(payment);
    }
    
    /*
     * Authenticate the payment of current customer.
     */
    public boolean authenticate() {
        if (currentCustomer.getPayment() == null) return false;
        return AuthenticateService.authenticate(currentCustomer.getPayment());
    }
    
    /*
     * End service for current customer. Send Customer object to the Server.
     * And start service for next customer.
     */
    public void checkOut() {
        // The current CustomerImpl object is first put into a queue. Then
        // Post will try to empty the queue from head to tail.
        bufferQueue.add(currentCustomer);
        // Before trying to connect Server and empty the queue, Post will
        // create a new CustomerImpl object. That's because the connecting
        // may take a while and cashier may start to add items or change
        // customer's name. These operation must be applied to the new customer.
        try {
            currentCustomer = new CustomerImpl();
            total = BigDecimal.ZERO;
        } catch (RemoteException e) {
            System.out.println("Fail to make new customer " + e);
            e.printStackTrace();
        }
        // Try to send the CustomerImpl object to the Server. Only remove
        // the object after Server return a result (no matter true or false).
        // So that if connect fails, the object is still in the queue.
        try {
            while (!bufferQueue.isEmpty()) {
                CustomerImpl customer = bufferQueue.getFirst();
                String receipt = store.recordSale(customer);
                printReceipt(receipt);
                bufferQueue.removeFirst();
            }
        } catch(RemoteException ex) {
            System.out.println("Fail to send sale log to Server " + ex);
        }
        
    }
    
    private void printReceipt(String receipt) {
        System.out.println(receipt);
    }

}

// These two classes will be remove after test
class TestStore implements Store {

    @Override
    public String recordSale(Customer customer) throws RemoteException {
        return "Receipt";
    }

    @Override
    public HashMap<String, Item> getCatalog() throws RemoteException {
        HashMap<String, Item> fakeCatalog = new HashMap<String, Item>();
        for (int i = 0; i < 10; i++)
        fakeCatalog.put("000" + i, new TestItem("000" + i));
        return fakeCatalog;
    }
    
}

class TestItem implements Item {
    
    private String UPC;
    
    public TestItem(String UPC) {
        this.UPC = UPC;
    }

    @Override
    public BigDecimal getPrice() throws RemoteException {
        return new BigDecimal(123.4567);
    }

    @Override
    public String getDescription() throws RemoteException {
        return "A Description of " + UPC;
    }

    @Override
    public String getUPC() throws RemoteException {
        return UPC;
    }
    
}
