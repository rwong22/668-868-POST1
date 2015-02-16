package post;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import payment.*;

public interface Customer extends Remote {

    public void addItem(String UPC, int quantity) throws RemoteException;

    public void addPayment(Payment payment) throws RemoteException;

    public ArrayList<ItemTuple> getItems() throws RemoteException;

    public Payment getPayment() throws RemoteException;

    public String getName() throws RemoteException;
}
