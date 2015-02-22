package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Process Customer objects for checkout .
 */
public interface Store extends Remote {

    /**
     * Processes Customer objects and passes customer item list to checkout.
     * 
     * @param customer
     *            customer which is ready for checkout
     * @return a String formatted as a Sales Receipt
     * @throws RemoteException
     */
    public String recordSale(Customer customer) throws RemoteException;

    /**
     * @return HashMap of Item UPCs and Items
     * @throws RemoteException
     */
    public HashMap<String, Item> getCatalog() throws RemoteException;
}
