package common;

import java.rmi.RemoteException;

public interface CreditCard extends Payment {

    public String getCardNumber() throws RemoteException;
}