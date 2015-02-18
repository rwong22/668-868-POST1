package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.math.BigDecimal;


public interface Item extends Remote {
  
  public BigDecimal getPrice() throws RemoteException;
  
  public String getDescription() throws RemoteException;
  
  public String getUPC() throws RemoteException;

}