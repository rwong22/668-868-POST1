package common;

/**
 * Holds an Item UPC and quantity for that Item. b/c we are using a singleton in
 * the Inventory class, we only need to really pass around ItemTuple arrays to
 * have all Item information, and customer cart info.
 */
public class ItemTuple {

    private String UPC;
    private int quantity;

    /**
     * Constructor to set the Item UPC and Item quantity for the ArrayList 
     * @param UPC of each Item scanned from the customer cart
     * @param quantity per Item from the customer cart
     */
    public ItemTuple(String UPC, int quantity) {
        this.UPC = UPC;
        this.quantity = quantity;
    }

    /**
     * @return the UPC of each Item scanned from Customer cart.
     */
    public String getUPC() {
        return UPC;
    }

    /**
     * @return the number of each quantity from the Customer cart
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param UPC scanned for the item from the Customer cart
     */
    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    /**
     * @param quantity of the each item in the customer cart
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
