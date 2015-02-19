package post;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import common.Item;


public class ItemImpl implements Item {
    
    private String UPC;
    private String description;
    private BigDecimal price;
    
    public ItemImpl(String UPC, String description, BigDecimal price) {
        this.UPC = UPC;
        this.description = description;
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public String getUPC() {
        return UPC;
    }
    
}
