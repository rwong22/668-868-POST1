package common;

import java.rmi.RemoteException;

/**
 * CreditCard Payment
 */
public interface CreditCard extends Payment {

    /**
     * @return CreditCard Number
     * @throws RemoteException
     */
    public String getCardNumber() throws RemoteException;
}
