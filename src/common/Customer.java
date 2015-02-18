package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import payment.*;

public interface Customer extends Remote {

    public void addItem(String UPC, int quantity) throws RemoteException;

    public void addPayment(PaymentImpl payment) throws RemoteException;

    public ArrayList<ItemTuple> getItems() throws RemoteException;

    public PaymentImpl getPayment() throws RemoteException;

    public String getName() throws RemoteException;
}
