package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.math.BigDecimal;

public interface Payment extends Remote {

    public BigDecimal getAmount() throws RemoteException;

    public void setAmount(BigDecimal amount) throws RemoteException;

    public PaymentType getType() throws RemoteException;

    public void setType(PaymentType type) throws RemoteException;

}