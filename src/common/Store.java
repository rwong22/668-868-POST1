package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface Store extends Remote {

    public boolean helpCustomer(Customer customer) throws RemoteException;

    public HashMap<String, Item> getInventory() throws RemoteException;
}