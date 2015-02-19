package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.math.BigDecimal;

/**
 * Retrieves Item information from the Inventory for the POST
 */
public interface Item extends Remote {
  
    /**
     * Gets the price of the item
     * @return price of the Item
     * @throws RemoteException
     */
    public BigDecimal getPrice() throws RemoteException;
  
    /**
     * Gets the description of the Item
     * @return Text of Item product description
     * @throws RemoteException
     */
    public String getDescription() throws RemoteException;
  
    /**
     * Gets the UPC of the Item
     * @return UPC of Item
     * @throws RemoteException
     */
    public String getUPC() throws RemoteException;

}
