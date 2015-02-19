package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.math.BigDecimal;

/**
 * Abstract class to standardize all payment types
 */
public interface Payment extends Remote {

    /**
     * @return The total amount of the payment
     * @throws RemoteException
     */
    public BigDecimal getAmount() throws RemoteException;

    /**
     * @param amount the amount of the payment
     * @throws RemoteException
     */
    public void setAmount(BigDecimal amount) throws RemoteException;

    /**
     * Three payment type customer can choose to pay, either Cash, 
     * CreditCard, or Check
     * @return the type of payment the Customer choose from PaymentType 
     * @throws RemoteException
     */
    public PaymentType getType() throws RemoteException;

    /**
     * @param type of Payment type the Customer choose to pay for his item
     * @throws RemoteException
     */
    public void setType(PaymentType type) throws RemoteException;

}